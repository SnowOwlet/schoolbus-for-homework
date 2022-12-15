package cn.udday.schoolbus.model;

public class OrderVo {

    private int orderId;
    //用户
    private int userId;

    private String userName;

    private String password;
    //性别
    private String avatar;
    //电话
    private String phone;

    //是否是管理员
    private boolean isSuper;

    //备注
    private String comment;
    //选择的详细
    private int scheduleId;
    private Float schedulePrice;

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
    private Integer busAllNum;
    private Integer busNum;
    //预定时间
    private Long orderTime;
    //是否支付
    private String orderStatus;

    @Override
    public String toString() {
        return "OrderVo{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", isSuper=" + isSuper +
                ", comment='" + comment + '\'' +
                ", scheduleId=" + scheduleId +
                ", schedulePrice=" + schedulePrice +
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
                ", busAllNum=" + busAllNum +
                ", busNum=" + busNum +
                ", orderTime=" + orderTime +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSuper() {
        return isSuper;
    }

    public void setSuper(boolean aSuper) {
        isSuper = aSuper;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Float getSchedulePrice() {
        return schedulePrice;
    }

    public void setSchedulePrice(Float schedulePrice) {
        this.schedulePrice = schedulePrice;
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

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
