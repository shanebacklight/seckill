package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * spring和junit整合，junit启动时加载SpringIOC容器
 * Created by Administrator on 2018/2/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉juint spring配置文件
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testReduceNumber() throws Exception {
        Date killTime = new Date();
        System.out.println(seckillDao.reduceNumber(1007, killTime));
    }

    @Test
    public void testQueryById() throws Exception {
        long id =1007;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for(Seckill seckill : seckills){
            System.out.println(seckill.getName());
            System.out.println(seckill);
        }
    }
}