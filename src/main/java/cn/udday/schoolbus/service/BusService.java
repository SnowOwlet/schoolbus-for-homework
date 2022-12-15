package cn.udday.schoolbus.service;

public interface BusService {
    Object addBus(String busName,
                  int busAllNum);

    Object deleteBus(int busId);

    Object changeBus(int busId,
                     String busName,
                     String busState,
                     int busAllNum,
                     int busNum);

    Object getAllBus(int page, int pageSize, String busName);

    Object getOneBus(int busId);
}
