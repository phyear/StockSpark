package com.ztx.shares.mapper;

import com.ztx.shares.pojo.StockInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface StockInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockInfo record);

    int insertSelective(StockInfo record);

    StockInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockInfo record);

    int updateByPrimaryKey(StockInfo record);

    List<Map<String,Object>> getById(@Param("stockId") String stockId,@Param("startTime")String startTime,@Param("endTime")String endTime);


    int selectByIdAndTime(@Param("day") String day, @Param("stockId")String stockId);

    /**
     * 获取三十天内涨幅大于5%的股票
     * @param val
     * @param openTime
     * @param endTime
     * @return
     */
    List<Map<String,Object>> selectIncrease(@Param("val")int val,@Param("openTime")String openTime,@Param("endTime")String endTime);
}