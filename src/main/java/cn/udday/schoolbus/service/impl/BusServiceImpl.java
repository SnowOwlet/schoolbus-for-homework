package cn.udday.schoolbus.service.impl;

import cn.udday.schoolbus.mapper.BusMapper;
import cn.udday.schoolbus.model.Bus;
import cn.udday.schoolbus.model.PageResponse;
import cn.udday.schoolbus.model.Response;
import cn.udday.schoolbus.service.BusService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("bus")
public class BusServiceImpl implements BusService {

    @Resource
    BusMapper busMapper;

    @Override
    public Object addBus(String busName, int busAllNum) {
        Bus bus = new Bus();
        bus.setBusName(busName);
        bus.setBusState("未满");
        bus.setBusAllNum(busAllNum);
        bus.setBusNum(0);
        busMapper.insert(bus);
        return Response.ok("添加成功");
    }

    @Override
    public Object deleteBus(int busId) {
        QueryWrapper<Bus> qw = new QueryWrapper();
        qw.eq("bus_id", busId);
        if (busMapper.selectOne(qw) == null) {
            return Response.error("不存在该车");
        } else {
            busMapper.delete(qw);
            return Response.ok("删除成功");
        }
    }

    @Override
    public Object changeBus(int busId, String busName, String busState, int busAllNum, int busNum) {
        QueryWrapper<Bus> qw = new QueryWrapper<>();
        qw.eq("bus_id", busId);
        Bus bus = busMapper.selectOne(qw);
        if (bus == null) return Response.error("不存在该车");
        bus.setBusName(busName);
        bus.setBusState(busState);
        bus.setBusAllNum(busAllNum);
        bus.setBusNum(busNum);
        busMapper.update(bus, qw);
        return Response.ok("修改成功");
    }

    @Override
    public Object getAllBus(int pageNum, int pageSize, String busName) {
        Page<Bus> page = new Page<>(pageNum, pageSize);
        if (busName == null || busName.equals("")) {
            busMapper.selectPage(page, null);
        } else {
            QueryWrapper<Bus> busQW = new QueryWrapper();
            busQW.like("bus_name", busName);
            busMapper.selectPage(page, busQW);
        }
        return new PageResponse<>(page.getRecords(), (int) page.getPages());
    }

    @Override
    public Object getOneBus(int busId) {
        QueryWrapper<Bus> qw = new QueryWrapper();
        qw.eq("bus_id", busId);
        if (busMapper.selectOne(qw) == null) {
            return Response.error("不存在该车");
        }
        return new Response(busMapper.selectOne(qw), "查询成功");
    }
}
