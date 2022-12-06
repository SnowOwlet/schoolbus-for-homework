package cn.udday.schoolbus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Bus {
    //校车id
    @TableId(type = IdType.AUTO)
    private Integer busId;
    //校车名称
    private String busName;
    //出发站点
    private String beginSite;
    //结束站点
    private String endSite;
    //途径站点
    private String pathwaySite;
    //满员
    private String busState;
    //价格
    private Float busPrice;
    //人数
    private Integer busNum;
    //开始时间
    private Long beginTime;
}
