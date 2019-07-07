package com.ztx.shares.config;

import com.ztx.shares.mapper.StockMapper;
import com.ztx.shares.service.JsoupService;
import com.ztx.shares.util.JsoupUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Component
@EnableScheduling
public class JobDemo {
    @Resource
    JsoupService jsoupService;
    @Resource
    StockMapper stockMapper;
    @Scheduled(cron = "0 0 0 16 *  ?")
    public void addStock(){
        int i=jsoupService.initStock();
        System.out.println("新增"+i+"个股票");
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void addHistoryInfo(){
        List<String> list=stockMapper.selectByStatusLimitTen(0);
        System.out.println(list);
        if(list==null||list.size()==0){ return;}
        int a=0;
        String url=JsoupUtil.urls[a];
        new Thread() {
            public void run() {
                for (String coding:list) {
                    int i=jsoupService.initYearInfo(url,coding,a);
                    System.out.println("成功"+1);
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }.start();

     }

    @Scheduled(cron = "0 40 18 ? * MON-FRI")
    public void getLastMarket(){
        List<String> list=stockMapper.selectByStatusLimitTen(1);
        if(list==null||list.size()==0){ return;}
        int a=1;
        String url=JsoupUtil.urls[a];
        new Thread() {
            public void run() {
                for (String coding:list) {
                    int i=jsoupService.initYearInfo(url,coding,a);
                    System.out.println("实时数据更新成功");
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }.start();
    }
}
