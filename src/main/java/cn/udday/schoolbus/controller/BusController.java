package cn.udday.schoolbus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/API/bus/")
public class BusController {

    @GetMapping("/test")
    public Object getAllBus(){
        return "1234565413";
    }
}
