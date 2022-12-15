package cn.udday.schoolbus.mapper;

import cn.udday.schoolbus.model.Order;
import cn.udday.schoolbus.model.OrderVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select o.*, u.*, s.*, r.*, b.* " +
            "from `order` o, user u, schedule s, route r, bus b " +
            "where o.user_id=u.user_id and o.schedule_id=s.schedule_id " +
            "and s.route_id=r.route_id and s.bus_id=b.bus_id")
    List<OrderVo> getListAssociated();

    @Select("select o.*, u.*, s.*, r.*, b.* " +
            "from `order` o, school_bus_db.user u, schedule s, route r, bus b " +
            "where o.user_id=u.user_id and o.schedule_id=s.schedule_id " +
            "and s.route_id=r.route_id and s.bus_id=b.bus_id and ${ew.sqlSegment} order by o.order_id ASC")
    IPage<OrderVo> queryAssociated(IPage<OrderVo> page, @Param("ew") Wrapper<OrderVo> wrapper);
}
