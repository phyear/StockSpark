package com.ztx.shares.service.impl;

import com.ztx.shares.mapper.StockInfoMapper;
import com.ztx.shares.mapper.StockMapper;
import com.ztx.shares.pojo.Stock;
import com.ztx.shares.pojo.StockInfo;
import com.ztx.shares.service.StockInfoService;
import com.ztx.shares.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StockInfoServiceImpl  implements StockInfoService {
    @Resource
    StockInfoMapper stockInfoMapper;
    @Resource
    StockMapper stockMapper;
    @Override
    public Map<String,List<String>> getById(String stockId,String startTime,String endtime) {
        List<Map<String,Object>> list=stockInfoMapper.getById(stockId,startTime,endtime);
        List<String> days=new ArrayList<>();
        List<String> opens=new ArrayList<>();
        List<String> highs=new ArrayList<>();
        List<String> closes=new ArrayList<>();
        List<String> lows=new ArrayList<>();
        for (Map<String,Object> map:list) {
            days.add(DateUtil.dateToStr(DateUtil.strToDate(map.get("day").toString())));
            opens.add(map.get("open").toString());
            highs.add(map.get("high").toString());
            closes.add(map.get("close").toString());
            lows.add(map.get("low").toString());
         }
        Map<String,List<String>> maps=new HashMap<>();
        maps.put("days",days);
        maps.put("opens",opens);
        maps.put("highs",highs);
        maps.put("lows",lows);
        maps.put("closes",closes);

        return maps;
    }

    @Override
    public Map<String, List<String>> statisticalIncrease() {
        Map<String, List<String>> maps=new HashMap<>();
        List<String> stocks=new ArrayList<>();
        List<String> counts=new ArrayList<>();
        //获取所有的30天内超过5%的公司股票
        List<Map<String,Object>> list=stockInfoMapper
                .selectIncrease(5,DateUtil.getOldMouth("yyyy-MM-dd",1,new Date()),DateUtil.dateToStr(new Date()));

        for (Map<String,Object> map:
        list) {

           stocks.add(map.get("names").toString());
           counts.add(map.get("counts").toString());


        }
        maps.put("ids",stocks);
        maps.put("counts",counts);
        return maps;
    }
}
