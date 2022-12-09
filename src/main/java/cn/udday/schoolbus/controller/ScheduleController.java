package cn.udday.schoolbus.controller;

import cn.udday.schoolbus.model.Response;
import cn.udday.schoolbus.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/API/schedule/")
public class ScheduleController {
    @Resource(name = "schedule")
    private ScheduleService scheduleService;

    @PostMapping("/add")
    public Object addSchedule(@RequestBody Map<String, String> data) {
        String beginSite = data.get("begin_site");
        if (beginSite == null || beginSite.equals("")) return Response.error("开始站点不能为空");
        String endSite = data.get("end_site");
        if (endSite == null || endSite.equals("")) return Response.error("结束站点不能为空");
        int routeId = Integer.parseInt(data.get("route_id"));
        String beginTime = data.get("begin_time");
        if (beginTime == null || beginTime.equals("")) return Response.error("开始时间不能为空");
        int busId = Integer.parseInt(data.get("bus_id"));
        Object res = scheduleService.addSchedule(beginSite,endSite,beginTime,routeId,busId);
        return res;
    }

    @PostMapping("/change")
    public Object changeSchedule(@RequestBody Map<String, String> data) {
        int scheduleId = Integer.parseInt(data.get("schedule_id"));
        String beginSite = data.get("begin_site");
        if (beginSite == null || beginSite.equals("")) return Response.error("开始站点不能为空");
        String endSite = data.get("end_site");
        if (endSite == null || endSite.equals("")) return Response.error("结束站点不能为空");
        int routeId = Integer.parseInt(data.get("route_id"));
        String beginTime = data.get("begin_time");
        if (beginTime == null || beginTime.equals("")) return Response.error("开始时间不能为空");
        int busId = Integer.parseInt(data.get("bus_id"));
        Object res = scheduleService.changeSchedule(scheduleId,beginSite,endSite,beginTime,routeId,busId);
        return res;
    }

    @GetMapping("/all")
    public Object allSchedule(@RequestBody Map<String, String> data) {
        int pageNum = 1;
        int pageSize = 20;
        pageNum = Integer.parseInt(data.get("page_num"));
        pageSize = Integer.parseInt(data.get("page_size"));
        String beginSite = data.get("start_site");
        String endSite = data.get("end_site");
        String busName = data.get("bus_name");
        Object res = scheduleService.allSchedule(pageNum,pageSize,beginSite,endSite,busName);
        return res;
    }

    @PostMapping("/delete")
    public Object deleteSchedule(@RequestBody Map<String,String> data){
        int ScheduleId = Integer.parseInt(data.get("schedule_id"));
        Object res = scheduleService.deleteSchedule(ScheduleId);
        return res;
    }
}
