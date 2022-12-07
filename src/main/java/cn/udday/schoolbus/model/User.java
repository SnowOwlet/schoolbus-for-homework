package cn.udday.schoolbus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "user")
public class User {
    //用户ID
    @TableId(type = IdType.AUTO)
    private Integer userId;
    //用户姓名
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

    public User(String userName, String password, String avatar, String phone, boolean isSuper, String comment) {
        this.userName = userName;
        this.password = password;
        this.avatar = avatar;
        this.phone = phone;
        this.isSuper = isSuper;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", isSuper=" + isSuper +
                ", comment='" + comment + '\'' +
                '}';
    }

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
}
