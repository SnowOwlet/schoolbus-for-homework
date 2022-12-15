package cn.udday.schoolbus.controller;


import cn.udday.schoolbus.model.Response;
import cn.udday.schoolbus.service.BusService;
import cn.udday.schoolbus.service.RouteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/API/route/")
public class RouteController {

    @Resource(name = "route")
    private RouteService routeService;

    @PostMapping("/add")
    public Object addRoute(@RequestBody Map<String, String> data) {
        String beginSite = data.get("begin_site");
        if (beginSite == null || beginSite.equals("")) return Response.error("开始站点不能为空");
        String endSite = data.get("end_site");
        if (endSite == null || endSite.equals("")) return Response.error("结束站点不能为空");
        String pathwaySite = data.get("pathway_site");
        Object res = routeService.addRoute(beginSite,endSite,pathwaySite);
        return res;
    }

    @PostMapping("/change")
    public Object changeRoute(@RequestBody Map<String, String> data) {
        int routeId = Integer.parseInt(data.get("route_id"));
        String beginSite = data.get("begin_site");
        if (beginSite == null || beginSite.equals("")) return Response.error("开始站点不能为空");
        String endSite = data.get("end_site");
        if (endSite == null || endSite.equals("")) return Response.error("结束站点不能为空");
        String pathwaySite = data.get("pathway_site");
        Object res = routeService.changeRoute(routeId,beginSite,endSite,pathwaySite);
        return res;
    }

    @PostMapping("/all")
    public Object allRoute(@RequestBody Map<String, String> data) {
        int pageNum = 1;
        int pageSize = 20;
        pageNum = Integer.parseInt(data.get("page_num"));
        pageSize = Integer.parseInt(data.get("page_size"));
        String beginSite = data.get("begin_site");
        String endSite = data.get("end_site");
        Object res = routeService.allRoute(pageNum,pageSize,beginSite,endSite);
        return res;
    }

    @PostMapping("/delete")
    public Object deleteRoute(@RequestBody Map<String,String> data){
        int routeId = Integer.parseInt(data.get("route_id"));
        Object res = routeService.deleteRoute(routeId);
        return res;
    }
}
