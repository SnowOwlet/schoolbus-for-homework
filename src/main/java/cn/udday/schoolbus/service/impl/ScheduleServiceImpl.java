package cn.udday.schoolbus.service.impl;

import cn.udday.schoolbus.mapper.BusMapper;
import cn.udday.schoolbus.mapper.RouteMapper;
import cn.udday.schoolbus.mapper.ScheduleMapper;
import cn.udday.schoolbus.model.*;
import cn.udday.schoolbus.service.ScheduleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("schedule")
public class ScheduleServiceImpl implements ScheduleService {
    @Resource
    private ScheduleMapper scheduleMapper;
    @Resource
    private BusMapper busMapper;

    @Resource
    private RouteMapper routeMapper;
    @Override
    public Object addSchedule(String beginSite, String endSite, String beginTime,int routeId, int busId) {
        Schedule schedule = new Schedule();
        schedule.setBeginSite(beginSite);
        schedule.setEndSite(endSite);
        schedule.setBeginTime(beginTime);
        QueryWrapper<Bus> busQw = new QueryWrapper<>();
        busQw.eq("bus_id",busId);
        if (busMapper.selectOne(busQw) == null) {
            return Response.error("校车不存在");
        }
        schedule.setBusId(busId);
        QueryWrapper<Route> routeQW = new QueryWrapper<>();
        routeQW.eq("route_id",routeId);
        if (routeMapper.selectOne(routeQW) == null) {
            return Response.error("线路不存在");
        }
        schedule.setRouteId(routeId);

        scheduleMapper.insert(schedule);
        return Response.ok("添加成功");
    }

    @Override
    public Object changeSchedule(int scheduleId, String beginSite, String endSite, String beginTime,int routeId, int busId) {
        QueryWrapper<Schedule> scheduleQw = new QueryWrapper<>();
        scheduleQw.eq("schedule_id",scheduleId);
        if (scheduleMapper.selectOne(scheduleQw) == null) {
            return Response.error("该班车");
        }
        Schedule schedule = new Schedule();
        schedule.setScheduleId(scheduleId);
        schedule.setBeginSite(beginSite);
        schedule.setEndSite(endSite);
        schedule.setBeginTime(beginTime);
        QueryWrapper<Bus> busQw = new QueryWrapper<>();
        busQw.eq("bus_id",busId);
        if (busMapper.selectOne(busQw) == null) {
            return Response.error("校车不存在");
        }
        schedule.setBusId(busId);
        QueryWrapper<Route> routeQW = new QueryWrapper<>();
        routeQW.eq("route_id",routeId);
        if (routeMapper.selectOne(routeQW) == null) {
            return Response.error("线路不存在");
        }
        schedule.setRouteId(routeId);
        scheduleMapper.update(schedule,scheduleQw);
        return Response.ok("更改成功");
    }

    @Override
    public Object allSchedule(int pageNum, int pageSize, String beginSite, String endSite, String busName) {
        Page<ScheduleVo> page = new Page<> (pageNum,pageSize);
        Page<ScheduleVo> res;
        QueryWrapper<Bus> busQw = new QueryWrapper<>();
        busQw.eq("bus_name",busName);
        Bus bus = busMapper.selectOne(busQw);

            QueryWrapper<ScheduleVo> qw = new QueryWrapper();
            qw
                    .like("begin_site",beginSite)
                    .or()
                    .like("end_site",endSite);
            if (bus!=null){
                qw.or().like("bus_id",bus.getBusId());
            }
            scheduleMapper.queryAssociated(page,qw);
            List<ScheduleVo> schedules = page.getRecords();
            int pageTotal = (int) page.getPages();
        return new PageResponse<>(schedules,pageTotal);
    }

    @Override
    public Object deleteSchedule(int scheduleId) {
        QueryWrapper<Schedule> qw = new QueryWrapper();
        qw.eq("schedule_id",scheduleId);
        if (scheduleMapper.selectOne(qw) == null) {
            return Response.error("不存在该班车");
        }
        scheduleMapper.delete(qw);
        return Response.ok("删除成功");
    }
}
