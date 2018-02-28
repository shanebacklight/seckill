package org.seckill.utils;

import org.seckill.dto.SeckillResult;
import org.seckill.enums.SeckillStatEnum;

/**
 * Created by Administrator on 2018/2/26.
 */
public class SeckillResultUtil {
    public static SeckillResult success(){
        return success(null);
    }
    public static SeckillResult success(Object data){
        SeckillResult seckillResult = new SeckillResult();
        seckillResult.setCode(0);
        seckillResult.setMessage("OK");
        seckillResult.setData(data);
        return seckillResult;
    }

    public static SeckillResult error(int code, String msg){
        return error(code, msg, null);
    }

    public static SeckillResult error(int code, String msg, Object data){
        SeckillResult seckillResult = new SeckillResult();
        seckillResult.setCode(code);
        seckillResult.setMessage(msg);
        seckillResult.setData(data);
        return seckillResult;
    }
}
