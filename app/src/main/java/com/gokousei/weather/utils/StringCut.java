package com.gokousei.weather.utils;

public class StringCut {


    /**
     * @param origin 传入待剪切字符串
     * @param begin 开始剪切的位置
     * @param end 结束的位置
     * @return 剪切过的字符串
     */
    public static String cut(String origin, int begin, int end) {
        return origin.substring(begin, end);
    }
}
