package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * Created by Administrator on 2018/2/24.
 */
public interface SuccessKilledDao {
    /**
     * 插入购买明细，可过滤重复
     * @param:
     * @return:
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据id在success_killed表中查询,并返回seckill表中的实体
     * @param:
     * @return:
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
