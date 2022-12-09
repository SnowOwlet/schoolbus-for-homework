package cn.udday.schoolbus.service;

public interface RouteService {
    Object addRoute(String beginSite,String endSite,String pathwaySite);
    Object changeRoute(int routeId,String beginSite,String endSite,String pathwaySite);
    Object allRoute(int pageNum,int pageSize,String beginSite,String endSite);
    Object deleteRoute(int routeId);
}
