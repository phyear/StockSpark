package com.ztx.shares;

import com.ztx.shares.util.DateUtil;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test
{

    public static void main(String[] args) {


//        List<Map<String,Object>> list=new ArrayList<>();
//        Map<String,Object> map=new HashMap<>();
//        map.put("id",1);
//        map.put("name","历史");
//        map.put("position","1111");
//        Map<String,Object> map2=new HashMap<>();
//        map2.put("id",2);
//        map2.put("name","aad");
//        map2.put("position","adad");
//        list.add(map2);
//        list.add(map);
//
//        list.parallelStream().filter(n-> (int)n.get("id")>1).forEach(n -> print(n));print
//          Random random=new Random();
//          random.ints().limit(20).sorted().forEach(System.out::println);

//        List<Integer> numbers = Arrays.asList(3, 2, 11, 9, 7, 9, 5);
//
//        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
//
//        System.out.println("列表中最大的数 : " + stats.getMax());
//        System.out.println("列表中最小的数 : " + stats.getMin());
//        System.out.println("所有数之和 : " + stats.getSum());
//        System.out.println("平均数 : " + stats.getAverage());

        List<String> strings = Arrays.asList("ss", "dd", "bfc", "sd", "ada","fghj", "yyy");
        strings=strings.stream().map(n->n+"ss").collect(Collectors.toList());
        System.out.println(strings);
    }
    public static void print(Map<String,Object>  value){
        System.out.println(value.get("id"));
    }
}
