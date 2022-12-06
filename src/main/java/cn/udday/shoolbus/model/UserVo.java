package cn.udday.shoolbus.model;

public class UserVo {
    //用户ID
    private Integer userId;
    //用户姓名
    private String userName;
    //性别
    private String avatar;
    //电话
    private String phone;

    //备注
    private String comment;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
