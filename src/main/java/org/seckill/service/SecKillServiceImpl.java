package org.seckill.service;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/25.
 */
@Service
public class SecKillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    private final String salt = "a87d8a789f&36^3&&^3437^^#234hae~!389";

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) throws SeckillException{
        Seckill seckill = seckillDao.queryById(seckillId);
        if(seckill == null){
            throw new SeckillException(SeckillStatEnum.NO_SeckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date curTime = new Date();
        if(curTime.getTime() < startTime.getTime() || curTime.getTime() > endTime.getTime()){
            return new Exposer(false, seckillId, curTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId, curTime.getTime(), startTime.getTime(), endTime.getTime());
    }

    private String getMd5(long seckillId){
        String base = seckillId+"/"+salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


    /**
     * 使用注解控制事务方法的优点
     * 1.开发团队达成一致约定，明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能端，不要穿插其他网络操作
     * 3.不是所有的方法都需要事务，如果只有一条修改操作，只读操作不需要事务控制
     */
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException{
        if(md5 == null || !md5.equals(getMd5(seckillId))){
            throw new SeckillException(SeckillStatEnum.DATA_REWRITE);
        }
        int updateCount = seckillDao.reduceNumber(seckillId, new Date());
        int insertCount = successKilledDao.insertSuccessKilled(seckillId,userPhone);
        if(updateCount <=0){throw new SeckillException(SeckillStatEnum.CLOSE);}
        if(insertCount <=0){throw new SeckillException(SeckillStatEnum.REPEAT_KILL);}
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
        return new SeckillExecution(seckillId, successKilled);

//        catch (SeckillException e){
//            throw e;
//        }
//        catch(Exception e){
//            logger.error("秒杀Service层系统错误!!!{}", e);
//            //编译器系统异常装换为运行期异常
//            throw new SeckillException(-1, e.getMessage());
//        }
    }
}
