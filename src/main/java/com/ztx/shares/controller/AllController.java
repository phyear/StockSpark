package com.ztx.shares.controller;

import com.github.pagehelper.PageInfo;
import com.ztx.shares.mapper.StockMapper;
import com.ztx.shares.pojo.Stock;
import com.ztx.shares.service.StockInfoService;
import com.ztx.shares.service.StockService;
import com.ztx.shares.util.ResultMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class AllController {
    @Resource
    StockService stockService;
    @Resource
    StockInfoService stockInfoService;

    @RequestMapping("/")
    public String index(ModelAndView modelAndView){
     return "stockMain";
    }
    @RequestMapping("/getStock")
    @ResponseBody
    public ResultMap<List<Stock>> getStock(@RequestParam(defaultValue = "") String  shareName, int page, int limit){
        PageInfo<Stock> pageInfo=stockService.selectPage(shareName,page,limit);
        return new ResultMap<List<Stock>>("",pageInfo.getList(),0,(int)pageInfo.getTotal());
    }

    @RequestMapping("/stockInfo")
    public ModelAndView stockInfo(String stockId, ModelAndView modelAndView){
        modelAndView.setViewName("stockInfo");
        modelAndView.addObject("stock",stockService.findById(stockId));
        return modelAndView;
    }

    @GetMapping("/stockInfoDate/{id}")
    @ResponseBody
    public Map<String,List<String>> stockInfoDate(@PathVariable("id") String stockId,String startTime,String endtime){
        Map<String,List<String>> map=stockInfoService.getById(stockId,startTime,endtime);
        return map;
    }

    @GetMapping("/stockIncrease")
    @ResponseBody
    public Map<String,List<String>> stockIncrease(){
        Map<String,List<String>> map=stockInfoService.statisticalIncrease();
        return map;
    }

    @GetMapping("/toStockIncrease")
    public String toStockIncrease(){
        return "increase";
    }
}
