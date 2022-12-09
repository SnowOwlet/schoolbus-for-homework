package cn.udday.schoolbus.service;

public interface OrderService {

    Object pay(String token,int orderId);
    Object add(String token,int scheduleId);
    Object delete(String token,int orderId);
    Object all(String token,int pageNum,int pageSize);

}
