package cn.udday.schoolbus.controller;

import cn.udday.schoolbus.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

import static cn.udday.schoolbus.config.Config.TOKEN;

@RestController
@RequestMapping("/API/order/")
public class OrderController {

    @Resource(name = "order")
    private OrderService orderService;

    //支付
    @PostMapping("/pay")
    public Object pay(@RequestHeader(TOKEN) String token, @RequestBody Map<String,String> data){
        int orderId = Integer.parseInt(data.get("order_id"));
        Object res = orderService.pay(token,orderId);
        return res;
    }

    //预定
    @PostMapping("/add")
    public Object add(@RequestHeader(TOKEN) String token, @RequestBody Map<String,String> data){
       int scheduleId = Integer.parseInt(data.get("schedule_id"));
        Object res = orderService.add(token,scheduleId);
        return res;
    }

    @PostMapping("/delete")
    public Object delete(@RequestHeader(TOKEN) String token, @RequestBody Map<String,String> data){
        int orderId = Integer.parseInt(data.get("order_id"));
        Object res = orderService.delete(token,orderId);
        return res;
    }

    @GetMapping("/all")
    public Object all(@RequestHeader(TOKEN) String token, @RequestBody Map<String,String> data){
        int pageNum = 1;
        int pageSize = 20;
        pageNum = Integer.parseInt(data.get("page_num"));
        pageSize = Integer.parseInt(data.get("page_size"));
        Object res = orderService.all(token,pageNum,pageSize);
        return res;

    }
}
