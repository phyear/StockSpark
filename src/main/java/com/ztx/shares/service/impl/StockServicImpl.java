package com.ztx.shares.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztx.shares.mapper.StockMapper;
import com.ztx.shares.pojo.Stock;
import com.ztx.shares.service.StockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StockServicImpl implements StockService {
    @Resource
    StockMapper stockMapper;

    @Override
    public Stock findById(String stockId) {
        return stockMapper.selectByPrimaryKey(stockId);
    }

    @Override
    public PageInfo<Stock> selectPage(String shareName, int page, int limit) {
        PageHelper.startPage(page,limit);
        List<Stock> list=stockMapper.selectByInfo(shareName);
        PageInfo<Stock> pageInfo=new PageInfo<>(list);

        return pageInfo;
    }
}
