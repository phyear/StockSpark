package com.ztx.shares;

import com.ztx.shares.util.DateUtil;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test
{

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        ParsePosition pos = new ParsePosition(0);
        System.out.println(format.parse("180122",pos));
    }
}
