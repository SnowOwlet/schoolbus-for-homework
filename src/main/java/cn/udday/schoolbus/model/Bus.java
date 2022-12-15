package cn.udday.schoolbus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

public class Bus {
    //校车id
    @TableId(type = IdType.AUTO)
    private Integer busId;
    //校车名称
    private String busName;

    //满员
    private String busState;

    //人数
    private Integer busAllNum;
    //人数
    private Integer busNum;

    @Override
    public String toString() {
        return "Bus{" +
                "busId=" + busId +
                ", busName='" + busName + '\'' +
                ", busState='" + busState + '\'' +
                ", busAllNum=" + busAllNum +
                ", busNum=" + busNum +
                '}';
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

}
