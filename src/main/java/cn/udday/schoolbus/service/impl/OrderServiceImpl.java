package cn.udday.schoolbus.service.impl;

import cn.udday.schoolbus.SchoolBusUtils;
import cn.udday.schoolbus.mapper.BusMapper;
import cn.udday.schoolbus.mapper.OrderMapper;
import cn.udday.schoolbus.mapper.ScheduleMapper;
import cn.udday.schoolbus.mapper.UserMapper;
import cn.udday.schoolbus.model.*;
import cn.udday.schoolbus.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("order")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private ScheduleMapper scheduleMapper;
    @Resource
    private BusMapper busMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public Object pay(String token, int orderId) {
        User user = userMapper.getUserById(SchoolBusUtils.token2UserId(token));

        QueryWrapper<Order> orderQW = new QueryWrapper<>();
        orderQW.eq("order_id", orderId);
        Order order = orderMapper.selectOne(orderQW);
        if (order == null) {
            return Response.error("不存在该订单");
        }
        if (order.getUserId() != user.getUserId()) {
            return Response.error("非本人订单");
        }
        order.setOrderStatus("已支付");
        orderMapper.updateById(order);
        return Response.ok("支付成功");
    }

    @Override
    public Object add(String token, int scheduleId) {
        User user = userMapper.getUserById(SchoolBusUtils.token2UserId(token));
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setOrderStatus("未支付");
        Date date = new Date();
        order.setOrderTime(date.getTime());
        Schedule schedule = scheduleMapper.selectById(scheduleId);
        if (schedule == null) {
            return Response.error("发车线路不存在");
        }
        order.setScheduleId(scheduleId);
        Bus bus = busMapper.selectById(schedule.getBusId());
        int busNum = bus.getBusNum() + 1;
        if (busNum > bus.getBusAllNum()) {
            return Response.error("校车已满");
        }
        bus.setBusNum(busNum);
        busMapper.updateById(bus);

        orderMapper.insert(order);
        return Response.ok("预定成功");
    }


    @Override
    public Object delete(String token, int orderId) {
        User user = userMapper.getUserById(SchoolBusUtils.token2UserId(token));
        QueryWrapper<Order> orderQW = new QueryWrapper<>();
        orderQW.eq("order_id", orderId);
        Order order = orderMapper.selectOne(orderQW);
        if (order == null) {
            return Response.error("没有该预定信息");
        }
        if (user.isSuper()) {
            orderMapper.delete(orderQW);
            Bus bus = busMapper.selectById(scheduleMapper.selectById(order.getScheduleId()).getBusId());
            int busNum = bus.getBusNum() - 1;
            if (busNum < 0) busNum = 0;
            bus.setBusNum(busNum);
            busMapper.updateById(bus);
            return Response.ok("删除成功");
        } else {
            if (order.getUserId() == user.getUserId()) {
                orderMapper.delete(orderQW);
                return Response.ok("删除成功");
            } else {
                return Response.error("非本人预定信息，删除失败");
            }
        }
    }

    @Override
    public Object all(String token, int pageNum, int pageSize) {
        User user = userMapper.getUserById(SchoolBusUtils.token2UserId(token));
        IPage<OrderVo> page = new Page<>(pageNum, pageSize);
        if (user.isSuper()) {
            QueryWrapper<OrderVo> orderQW = new QueryWrapper<>();
            orderQW.eq("1",1);
            orderMapper.queryAssociated(page,orderQW);
        } else {
            QueryWrapper<OrderVo> orderQW = new QueryWrapper<>();
            //这里是因为数据表冲突
            orderQW.eq("u.user_id", user.getUserId());
            orderMapper.queryAssociated(page, orderQW);
        }
        return new PageResponse<>(page.getRecords(), (int) page.getPages());
    }
}
