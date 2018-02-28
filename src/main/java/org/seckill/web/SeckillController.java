package org.seckill.web;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.seckill.utils.SeckillResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        //获取列表页
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        //list.jsp+model=ModelAndView
        return "list";
    }

    @RequestMapping(value="/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if(seckillId == null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if(seckill == null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer",
            method=RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    //告诉浏览器context类型
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            return SeckillResultUtil.success(exposer);
        }
        catch (SeckillException e){
            return SeckillResultUtil.error(e.getCode(),e.getMessage());
        }
    }

    @RequestMapping(value="/{seckillId}/{md5}/execution", method = RequestMethod.POST,
    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value="killPhone", required = false) Long phone) {
        try{
            if(phone==null){
                SeckillStatEnum x = SeckillStatEnum.PHONE_UNREGISTER;
                return SeckillResultUtil.error(x.getCode(), x.getMsg());
            }
            SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
            return SeckillResultUtil.success(execution);
        }
        catch(SeckillException e) {
            return SeckillResultUtil.error(e.getCode(), e.getMessage(), new SeckillExecution(seckillId, e.getCode(), e.getMessage()));
        }
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        return SeckillResultUtil.success(new Date());
    }
}
