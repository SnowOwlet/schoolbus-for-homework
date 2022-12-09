package cn.udday.schoolbus.model;

public class ScheduleVo {
    private Integer scheduleId;
    private String beginSite;
    private String endSite;
    private Integer routeId;
    private String rBeginSite;
    private String rEndSite;
    private String rPathwaySite;
    private String beginTime;
    private Integer busId;

    private String busName;
    private String busState;
    private Float busPrice;
    private Integer busAllNum;
    private Integer busNum;

    @Override
    public String toString() {
        return "ScheduleVo{" +
                "scheduleId=" + scheduleId +
                ", beginSite='" + beginSite + '\'' +
                ", endSite='" + endSite + '\'' +
                ", routeId=" + routeId +
                ", rBeginSite='" + rBeginSite + '\'' +
                ", rEndSite='" + rEndSite + '\'' +
                ", rPathwaySite='" + rPathwaySite + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", busId=" + busId +
                ", busName='" + busName + '\'' +
                ", busState='" + busState + '\'' +
                ", busPrice=" + busPrice +
                ", busAllNum=" + busAllNum +
                ", busNum=" + busNum +
                '}';
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

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusState() {
        return busState;
    }

    public void setBusState(String busState) {
        this.busState = busState;
    }

    public Float getBusPrice() {
        return busPrice;
    }

    public void setBusPrice(Float busPrice) {
        this.busPrice = busPrice;
    }

    public Integer getBusAllNum() {
        return busAllNum;
    }

    public void setBusAllNum(Integer busAllNum) {
        this.busAllNum = busAllNum;
    }

    public Integer getBusNum() {
        return busNum;
    }

    public void setBusNum(Integer busNum) {
        this.busNum = busNum;
    }
}
