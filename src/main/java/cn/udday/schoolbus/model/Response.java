package cn.udday.schoolbus.model;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

@Data
public class Response<T> {
    private int code;
    private String msg;
    private T data;

    /**
     * 若没有数据返回，默认状态码为0，提示信息为：操作成功！
     *
     * @param i
     * @param s
     * @param text
     */
    public Response(int i, String s, String text) {

        this.code = 0;
        this.msg = "操作成功！";
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     *
     * @param code
     * @param msg
     **/
    public Response(int code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    /**
     * 有数据返回时，状态码为0，默认提示信息为：操作成功！
     *
     * @param data
     **/
    public Response(T data) {

        this.code = 0;
        this.msg = "操作成功！";
        this.data = data;
    }

    /**
     * 有数据返回，状态码为0，人为指定提示信息
     *
     * @param data
     * @param msg
     **/
    public Response(T data, String msg) {

        this.code = 0;
        this.msg = msg;
        this.data = data;
    }
    public static Response error(String msg){
        return new Response(-1,msg);
    }
    public static Response error(int code,String msg){
        return new Response(code,msg);
    }
    public String toJson(){
        return JSON.toJSONString(this);
    }
    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}