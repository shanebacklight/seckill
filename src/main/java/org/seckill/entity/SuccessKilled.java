package org.seckill.entity;

import java.util.Date;

/**
 * 秒杀明细表，联合主键seckillId和userPhone
 * Created by Administrator on 2018/2/24.
 */
public class SuccessKilled {
    private long seckillId;
    private long userPhone;
    private short state;
    private Date creatTime;
    //变通: 一个秒杀商品对应多个成功记录，是复合属性
    private Seckill seckill;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", creatTime=" + creatTime +
                ", seckill=" + seckill +
                '}';
    }
}
