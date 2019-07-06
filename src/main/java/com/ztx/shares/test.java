package com.ztx.shares;

import com.ztx.shares.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test
{

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(DateUtil.getOldMouth("yyyy-MM-dd",1,new Date()));
    }
}
