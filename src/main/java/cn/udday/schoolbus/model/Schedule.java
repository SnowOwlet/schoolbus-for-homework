package cn.udday.schoolbus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "schedule")
public class Schedule {
    @TableId(type = IdType.AUTO)
    private Integer scheduleId;
    private String beginSite;
    private String endSite;
    private Integer routeId;
    private String beginTime;
    private Integer busId;
    private Float schedulePrice;

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", beginSite='" + beginSite + '\'' +
                ", endSite='" + endSite + '\'' +
                ", routeId=" + routeId +
                ", beginTime='" + beginTime + '\'' +
                ", busId=" + busId +
                ", schedulePrice=" + schedulePrice +
                '}';
    }

    public Float getSchedulePrice() {
        return schedulePrice;
    }

    public void setSchedulePrice(Float schedulePrice) {
        this.schedulePrice = schedulePrice;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getBeginSite() {
        return beginSite;
    }

    public void setBeginSite(String beginSite) {
        this.beginSite = beginSite;
    }

    public String getEndSite() {
        return endSite;
    }

    public void setEndSite(String endSite) {
        this.endSite = endSite;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }
}
