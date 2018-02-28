package org.seckill.exception;

import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStatEnum;

/**
 * Created by Administrator on 2018/2/25.
 */
public class SeckillException extends RuntimeException {
    protected int code;
    public SeckillException(SeckillStatEnum x){
        super(x.getMsg());
        this.code = x.getCode();
    }
    public SeckillException(int code, String msg){
        super(msg);
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
