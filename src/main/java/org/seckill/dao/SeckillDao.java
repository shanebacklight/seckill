package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/24.
 */
public interface SeckillDao {
    /**根据Id，查询秒杀对象
     *减库存
     * @param:
     * @return:
     */

    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    Seckill queryById(long seckillId);

    /**
     *根据偏移量，查询秒杀商品列表
     * @param:
     * @return:
     */
    //java的语言特性，形参名字无法保存，arg0, arg1..这样造成Mybatis的SQL配置文件找不到该变量，所以使用@Param注解
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

}
