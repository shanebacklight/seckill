package org.seckill.enums;

/**
 * Created by Administrator on 2018/2/25.
 */
public enum SeckillStatEnum {
    SUCCESS(0, "秒杀成功"),
    CLOSE(1, "秒杀关闭"),
    REPEAT_KILL(2,"重复秒杀"),
    DATA_REWRITE(3, "数据篡改"),
    PHONE_UNREGISTER(4, "手机未注册"),
    NO_SeckillId(5, "商品不存在"),
    INNER_ERROR(-1, "系统错误")
    ;

    private int code;
    private String msg;

    SeckillStatEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static SeckillStatEnum stateOf(int index){
        for(SeckillStatEnum x : values()){
            if(x.getCode() == index){return x;}
        }
        return null;
    }
}
