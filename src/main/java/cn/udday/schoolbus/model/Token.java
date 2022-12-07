package cn.udday.schoolbus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

@TableName(value = "token")
public class Token {
    @TableId(type = IdType.AUTO)
    private int tokenId;
    private int userId;
    private Long buildTime;
    private String token;

    @Override
    public String toString() {
        return "Token{" +
                "tokenId=" + tokenId +
                ", userId=" + userId +
                ", buildTime=" + buildTime +
                ", token='" + token + '\'' +
                '}';
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Long buildTime) {
        this.buildTime = buildTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
