package cn.udday.schoolbus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "route")
public class Route {
    @TableId(type = IdType.AUTO)
    private Integer routeId;
    private String rBeginSite;
    private String rEndSite;
    private String rPathwaySite;

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", rBeginSite='" + rBeginSite + '\'' +
                ", rEndSite='" + rEndSite + '\'' +
                ", rPathwaySite='" + rPathwaySite + '\'' +
                '}';
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getrBeginSite() {
        return rBeginSite;
    }

    public void setrBeginSite(String rBeginSite) {
        this.rBeginSite = rBeginSite;
    }

    public String getrEndSite() {
        return rEndSite;
    }

    public void setrEndSite(String rEndSite) {
        this.rEndSite = rEndSite;
    }

    public String getrPathwaySite() {
        return rPathwaySite;
    }

    public void setrPathwaySite(String rPathwaySite) {
        this.rPathwaySite = rPathwaySite;
    }
}
