package cn.udday.schoolbus.service;

public interface ScheduleService {
    Object addSchedule(String beginSite, String endSite, String beginTime, int routeId, int busId,Float schedulePrice);

    Object changeSchedule(int scheduleId, String beginSite, String endSite, String beginTime, int routeId, int busId,Float schedulePrice);

    Object allSchedule(int pageNum, int pageSize, String beginSite, String endSite, String busName);

    Object deleteSchedule(int scheduleId);
}
