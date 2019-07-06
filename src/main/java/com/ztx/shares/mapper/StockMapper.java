package com.ztx.shares.mapper;

import com.ztx.shares.pojo.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface StockMapper {
    int deleteByPrimaryKey(String stockId);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(String stockId);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    @Update("update stock set status=#{i} where stock_id=#{stockId}")
    void updateStatus(@Param("stockId") String stockId, @Param("i") int i);

    List<String> selectByStatusLimitTen(@Param("status") int status);

    List<Stock> selectByInfo( @Param("info") String info);

    @Select("select * from stock where status=#{status} ")
    List<Stock> selectByStatus(@Param("status") int status);
}