package top.lzzzs.common.lang;


import lombok.Data;

import java.io.Serializable;

@Data
public class R implements Serializable {

    private int code;
    private String msg;
    private Object data;

    public static R s(Object data) {
        return R.s(200, "success", data);
    }

    public static R s(int code, String msg, Object data) {
        R r = new R();

        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);

        return r;
    }


    public static R e(int code, String msg, Object data) {
        R r = new R();

        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);

        return r;
    }

}
