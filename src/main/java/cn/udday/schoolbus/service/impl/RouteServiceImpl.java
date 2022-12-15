package cn.udday.schoolbus.service.impl;

import cn.udday.schoolbus.mapper.RouteMapper;
import cn.udday.schoolbus.model.PageResponse;
import cn.udday.schoolbus.model.Response;
import cn.udday.schoolbus.model.Route;
import cn.udday.schoolbus.service.RouteService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("route")
public class RouteServiceImpl implements RouteService {

    @Resource
    private RouteMapper routeMapper;

    @Override
    public Object addRoute(String beginSite, String endSite, String pathwaySite) {
        Route route = new Route();
        route.setrBeginSite(beginSite);
        route.setrEndSite(endSite);
        route.setrPathwaySite(pathwaySite);
        routeMapper.insert(route);
        return Response.ok("添加成功");
    }

    @Override
    public Object changeRoute(int routeId, String beginSite, String endSite, String pathwaySite) {
        QueryWrapper<Route> qw = new QueryWrapper<>();
        qw.eq("route_id", routeId);
        Route route = routeMapper.selectOne(qw);
        if (route == null) {
            return Response.error("不存在此站点");
        }
        route.setrBeginSite(beginSite);
        route.setrEndSite(endSite);
        route.setrPathwaySite(pathwaySite);
        routeMapper.update(route, qw);
        return Response.ok("修改成功");
    }

    @Override
    public Object allRoute(int pageNum, int pageSize, String beginSite, String endSite) {
        Page<Route> page = new Page<>(pageNum, pageSize);
        if ((beginSite == null || beginSite.equals("")) && (endSite == null || endSite.equals(""))) {
            routeMapper.selectPage(page, null);
        } else if (beginSite == null || beginSite.equals("")) {
            QueryWrapper<Route> qw = new QueryWrapper<>();
            qw.like("r_end_site", endSite);
            routeMapper.selectPage(page, qw);
        } else if (endSite == null || endSite.equals("")) {
            QueryWrapper<Route> qw = new QueryWrapper<>();
            qw.like("r_begin_site", beginSite);
            routeMapper.selectPage(page, qw);
        } else {
            QueryWrapper<Route> qw = new QueryWrapper<>();
            qw
                .like("r_begin_site", beginSite)
                .or()
                .like("r_end_site", endSite);
            routeMapper.selectPage(page, qw);
        }
//        if (!res.hasNext()){
//            return Response.error("不存在该路线");
//        }
        return new PageResponse<>(page.getRecords(), (int) page.getPages());
    }

    @Override
    public Object deleteRoute(int routeId) {
        QueryWrapper<Route> qw = new QueryWrapper();
        qw.eq("route_id", routeId);
        if (routeMapper.selectOne(qw) == null) {
            return Response.error("不存在该路线");
        }
        routeMapper.delete(qw);
        return Response.ok("删除成功");
    }
}
