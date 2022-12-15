package cn.udday.schoolbus.mapper;

import cn.udday.schoolbus.model.Schedule;
import cn.udday.schoolbus.model.ScheduleVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {
    @Select("select s.*, r.*, b.* " +
            "from schedule s, route r, bus b " +
            "where s.route_id=r.route_id and s.bus_id=b.bus_id")
    List<ScheduleVo> getListAssociated();

    @Select("select s.*, r.*, b.* " +
            "from schedule s, route r, bus b " +
            "where s.route_id=r.route_id and s.bus_id=b.bus_id and ${ew.sqlSegment} order by s.schedule_id ASC")
    IPage<ScheduleVo> queryAssociated(IPage<ScheduleVo> page, @Param("ew") Wrapper<ScheduleVo> wrapper);

}
