package com.ztx.shares;

import com.ztx.shares.mapper.StockInfoMapper;
import com.ztx.shares.service.JsoupService;
import com.ztx.shares.service.StockInfoService;
import com.ztx.shares.util.JsoupUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SharesApplicationTests {
    @Resource
    StockInfoMapper stockInfoMapper;
    @Resource
    StockInfoService stockInfoService;
    @Resource
    JsoupService jsoupService;
    @Test
    public void contextLoads() {
        String url="http://data.gtimg.cn/flashdata/hushen/daily/17/sz000750.js?visitDstTime=1";
       jsoupService.getTencentDate(
               url,"sz000750");
    }

}
