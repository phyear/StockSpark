package com.ztx.shares.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ztx.shares.mapper.StockInfoMapper;
import com.ztx.shares.mapper.StockMapper;

import com.ztx.shares.pojo.Stock;
import com.ztx.shares.pojo.StockInfo;
import com.ztx.shares.service.JsoupService;
import com.ztx.shares.util.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.io.IOException;

@Service
public class JsoupServiceImpl implements JsoupService {
    @Resource
    StockMapper stockMapper;
    @Resource
    StockInfoMapper stockInfoMapper;
    @Resource
    private RestTemplate restTemplate;
    @Override
    public int initStock(){
        int result=0;
        Document document = null;
        String url = "http://www.yz21.org/stock/info/";
        int pages=0;//当前页数
        while (true){
            pages+=1;
            if(pages>1){
             url="http://www.yz21.org/stock/info/stocklist_"+pages+".html";
            }
            try {
                document = Jsoup.connect(url).get();

            } catch (IOException e) {
                break;
            }

            Elements elements = document.select(".PindaoColumn tr:gt(0)");
            for(int i=0;i<elements.size();i++){

                String shareCoding=elements.get(i).select("td:eq(1) a").text();

                String val=elements.get(i).select("td:eq(1) a").attr("href");
                String[] vals=val.split("\\/");
                String shareId=vals[vals.length-2]+vals[vals.length-1];

                String shareName=elements.get(i).select("td:eq(2) a").text();

                String companyName=elements.get(i).select("td:eq(3) a").text();

                String companyShort=elements.get(i).select("td:eq(4)").text();

                if(stockMapper.selectByPrimaryKey(shareId)==null){
                    Stock stock=new Stock();
                    stock.setStockId(shareId);
                    stock.setShareName(shareName);
                    stock.setCompanyName(companyName);
                    stock.setCompanyShort(companyShort);
                    stock.setShareCoding(shareCoding);
                    int res=stockMapper.insertSelective(stock);
                    if(res>0){
                        result+=1;
                    }
                }

            }


        }
     return  result;
    }

    @Override
    @Transactional
    public int initYearInfo(String url,String stockId,int val) {
        //设置统计变量
         int count=0;
        //获取接口的数据并转换
        String res=restTemplate.getForObject(url+stockId, String.class);
        JSONArray results = null;
        //是否有数据，没有就返回
        if(res.equals("")|| res==""){return 0;}
        try{
            results = JSONArray.parseArray(res);
        }
        catch (Exception e){
            return 0;
        }


        /**
         *1.遍历json
         * 2.转换为对象并依次添加进数据库
         */
        for(int i=0;i<results.size();i++){
            JSONObject object=results.getJSONObject(i);
            StockInfo stockInfo=setValue(object,stockId);
            if(val==1){
              int a=stockInfoMapper.selectByIdAndTime(stockId,object.get("day").toString());
              if(a>0){return 0;}
            }
            int resa=stockInfoMapper.insertSelective(stockInfo);
            count+=resa;
        }
        //修改股票代码表中的状态：0：未获取历史数据 1：已经获取数据
        if(val==0){stockMapper.updateStatus(stockId,1);}
        return count;
    }



    /**
     * 转换stockinfo的帮助类
     * @param object
     * @param stockId
     * @return
     */
    private StockInfo setValue(JSONObject object, String stockId){
        StockInfo stockInfo=new StockInfo();
        stockInfo.setStockId(stockId);
        stockInfo.setDay(object.getDate("day"));
        stockInfo.setOpen(object.getString("open"));
        stockInfo.setHigh(object.getString("high"));
        stockInfo.setLow(object.getString("low"));
        stockInfo.setClose(object.getString("close"));
        stockInfo.setVolume(object.getString("volume"));
        return stockInfo;
    }
}
