package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> seckills= seckillService.getSeckillList();
        logger.info("list={}", seckills);
    }

    @Test
    public void testGetById() throws Exception {
        long id=1007L;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testExportSeckillUrl() throws Exception {
        long id = 1007L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            String md5= exposer.getMd5();
            long phone = 15700077500L;
            try{
                SeckillExecution seckillExecution= seckillService.executeSeckill(id, phone, md5);
                logger.info("seckillExecution={}", seckillExecution);
            }
            catch (SeckillException e) {
                logger.warn("SeckillException={}", e.getMessage());
            }
        }
        else{
            logger.warn("秒杀未开启");
        }
    }

    @Test
    public void testExecuteSeckillProcedure() throws Exception{
        long id = 1007L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            String md5= exposer.getMd5();
            long phone = 15700077500L;
            try{
                SeckillExecution seckillExecution= seckillService.executeSeckillProcedure(id, phone, md5);
                logger.info("seckillExecution={}", seckillExecution);
            }
            catch (SeckillException e) {
                logger.warn("SeckillException={}", e.getMessage());
            }
        }
        else{
            logger.warn("秒杀未开启");
        }
    }
}