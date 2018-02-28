package org.seckill.handle;

import org.seckill.dto.SeckillResult;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.utils.SeckillResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/2/27.
 */
@ControllerAdvice
public class ExcepitonHandle {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public SeckillResult handle(Exception e){
            logger.error("系统错误={}",e);
            SeckillStatEnum x = SeckillStatEnum.INNER_ERROR;
            return SeckillResultUtil.error(x.getCode(),x.getMsg());
    }
}
