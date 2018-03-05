package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口：站在使用者角度
 * 三个方面：方法定义粒度，参数，返回类型{return 类型/异常}
 * Created by Administrator on 2018/2/24.
 */

public interface SeckillService {
    List<Seckill> getSeckillList();

    Seckill getById(long seckillId);

    /**
     *秒杀开启时，输出秒杀接口地址；否则输出系统时间和秒杀时间
     * @param:
     * @return:
     */
    Exposer exportSeckillUrl(long seckillId);

    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException;

    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5)
            throws SeckillException;
}
