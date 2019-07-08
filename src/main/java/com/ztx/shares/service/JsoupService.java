package com.ztx.shares.service;

public interface JsoupService {
     /**
      * 查询股票代码
      * @return
      */
     int initStock();

     /**
      * 查询股票信息
      * @param url
      * @param stockId
      * @return
      */
     int initYearInfo(String url, String stockId,int val);

     /**
      *从腾讯接口获取一个股票一年的数据
      * @param url
      * @param stockId
      * @return
      */
     String getTencentDate(String url,String stockId);



}
