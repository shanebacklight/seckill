package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;

/**
 * 封装秒杀执行结果
 * Created by Administrator on 2018/2/25.
 */
public class SeckillExecution {
    //id
    private long seckillId;

    private int state;

    private String msg;

    //秒杀成功对象
    private SuccessKilled successKilled;

    //秒杀成功时的执行结果
    public SeckillExecution(long seckillId, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.successKilled = successKilled;
        this.state = 0;
        this.msg = "OK";
    }

    //秒杀失败时的执行结果
    public SeckillExecution(long seckillId, int state, String msg) {
        this.seckillId = seckillId;
        this.state = state;
        this.msg = msg;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
