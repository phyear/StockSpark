package com.ztx.shares.service;

import com.github.pagehelper.PageInfo;
import com.ztx.shares.pojo.Stock;

import java.util.List;

public interface StockService {

    Stock findById(String stockId);

    PageInfo<Stock> selectPage(String info, int page, int limit);

}
