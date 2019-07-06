package com.ztx.shares.service;

import com.ztx.shares.pojo.StockInfo;

import java.util.List;
import java.util.Map;

public interface StockInfoService {
    /**
     * 根据股票代码获取所有的详细信息
     * @param stockId
     * @return
     */
    Map<String,List<String>> getById(String stockId,String startTime,String endtime);

    /**
     * 获取前30天内涨幅超过5%的次数
     * @return
     */
    Map<String,List<String>> statisticalIncrease();
}
