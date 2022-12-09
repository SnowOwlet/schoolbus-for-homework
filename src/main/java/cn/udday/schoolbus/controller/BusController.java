package cn.udday.schoolbus.controller;

import cn.udday.schoolbus.model.Response;
import cn.udday.schoolbus.service.BusService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/API/bus/")
public class BusController {
    @Resource(name = "bus")
    private BusService busService;
    @PostMapping("/add")
    public Object addBus(@RequestBody Map<String, String> data) {
        String busName = data.get("bus_name");
        if (busName == null || busName.equals("")) {
            return Response.error("校车名不能为空");
        }
        Float busPrice = Float.valueOf(data.get("bus_price"));
        if (busPrice == null || busPrice.equals("")) {
            return Response.error("价钱不能为空");
        }

        int busAllNum = Integer.valueOf(data.get("bus_all_num"));
        if (busAllNum <= 0) {
            return Response.error("总人数不能为0");
        }
        Object res = busService.addBus(busName, busPrice, busAllNum);
        return res;
    }
    @PostMapping("/change")
    public Object changeBus(@RequestBody Map<String, String> data) {
        int busId = Integer.valueOf(data.get("bus_id"));
        if (busId <= 0) {
            return Response.error("校车id不正确");
        }
        String busName = data.get("bus_name");
        if (busName == null || busName.equals("")) {
            return Response.error("校车名不能为空");
        }

        String busState = data.get("bus_state");

        Float busPrice = Float.valueOf(data.get("bus_price"));
        if (busPrice == null || busPrice.equals("")) {
            return Response.error("价钱不能为空");
        }

        int busAllNum = Integer.valueOf(data.get("bus_all_num"));

        if (busAllNum <= 0) {
            return Response.error("总人数不能为0");
        }

        int busNum = Integer.valueOf(data.get("bus_num"));
        if (busNum < 0) {
            return Response.error("人数违规");
        }
        Object res = busService.changeBus(busId, busName, busState, busPrice, busAllNum, busNum);
        return res;
    }
    @GetMapping("/all")
    public Object allBus(@RequestBody Map<String, String> data) {
        int pageNum = 1;
        int pageSize = 20;
        pageNum = Integer.parseInt(data.get("page_num"));
        pageSize = Integer.parseInt(data.get("page_size"));
        String busName = data.get("bus_name");
        Object res = busService.getAllBus(pageNum, pageSize, busName);
        return res;
    }
    @GetMapping("/{bus_id}")
    public Object oneBus(@PathVariable("bus_id") int busId) {
        Object res = busService.getOneBus(busId);
        return res;
    }
    @PostMapping("/delete")
    public Object deleteBus(@RequestBody Map<String,String> data){
        int busId = Integer.parseInt(data.get("bus_id"));
        Object res = busService.deleteBus(busId);
        return res;
    }
}
