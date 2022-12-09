package cn.udday.schoolbus.service;

public interface BusService {
    Object addBus(String busName,
                  Float busPrice,
                  int busAllNum);

    Object deleteBus(int busId);

    Object changeBus(int busId,
                     String busName,
                     String busState,
                     Float busPrice,
                     int busAllNum,
                     int busNum);

    Object getAllBus(int page, int pageSize, String busName);

    Object getOneBus(int busId);
}
