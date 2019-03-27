package com.gokousei.weather.utils.textUtils;

import android.graphics.Paint;

public class Align {
    static final String firstKG = "\u00A0";
    static final String secondKG = "\u2009";//两种不同长度的空格

    public static String textAlign(String str, int tevWidth, Paint tevPaint) {

        if (str.length() < 2) {
            return str;  //字符个数小于2，没有对齐的必要
        }
        int width = tevWidth;
        int number;

        Paint p = tevPaint;
        float firstLength = p.measureText(firstKG, 0, 1);
        float secondLength = p.measureText(secondKG, 0, 1);
        int sizeCha = (int) (firstLength - secondLength);//基于当前的TextSize，算出两种空格的长度和差值
        number = str.length();
        StringBuilder stringBuilderNullFirst = new StringBuilder();
        StringBuilder stringBuilderNullSecond = new StringBuilder();
        StringBuilder stringBuilderValid = new StringBuilder();
        for (int k = 0; k < number; k++) {
            if (str.charAt(k) == '\u00A0') {
                stringBuilderNullFirst.append(str.charAt(k));
            } else if (str.charAt(k) == '\u2009') {
                stringBuilderNullSecond.append(str.charAt(k));
            } else {
                stringBuilderValid.append(str.charAt(k));
            }
        }//计算当前文本所有字符的数量并归类，主要针对重新设置size导致的重绘
        float checkSum = stringBuilderNullFirst.length() * firstLength + stringBuilderNullSecond.length() * secondLength + p.measureText(stringBuilderValid.toString(), 0, stringBuilderValid.length());//算出按照目前的文字和size，总长度会是多少
        if (checkSum > width) {
            return stringBuilderValid.toString();
            /*因为size的增加，导致View容不下字，重新分配空格*/
        } else if (checkSum == width) {
            /*完美显示，直接返回绘制*/
            return str;
        } else {
            number = stringBuilderValid.length();
            if ((width - checkSum) > (sizeCha * (number - 1))) {
                if (stringBuilderNullSecond.length() != 0) {
                    /*总余量大于每个缝隙的差值，且存在小空格,重置*/
                    return stringBuilderValid.toString();
                } else {
                    /*只有大空格的情况，总余量大于每个缝隙插一个小空格,重置*/
                    if ((width - checkSum) > (secondLength * (number - 1))) {
                        return stringBuilderValid.toString();
                    } else {
                        return str;
                    }
                }
            }
        }
        number = str.length();

        int rest = width - (int) p.measureText(str.toString(), 0, str.length());
        if (rest < 0) {
            /*不加空格的情况下，字号超过组件需要换行了，本方法不生效*/
            return str;
        }
        int restPer = rest / (number - 1);
        int secondPer = restPer / (int) secondLength;
        int secondLeft = restPer % (int) secondLength;//先用小字号空格填缝隙
        int firstPer = 0;
        if (sizeCha != 0) {
            firstPer = secondLeft / sizeCha;
        }
        if (secondPer > firstPer) {
            secondPer -= firstPer;
        } else {
            firstPer = secondPer;
            secondPer = 0;
        }//根据计算剩下的空间，将相应的小空格替换为大空格进一步缩小误差
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < number; j++) {
            stringBuilder.append(str.charAt(j));
            if (j == number - 1) {
                break;
            } else {
                for (int i = 0; i < firstPer; i++) {
                    stringBuilder.append("\u00A0");
                }
                for (int n = 0; n < secondPer; n++) {
                    stringBuilder.append("\u2009");
                }
            }
        }//重设我们的文本，完成！
        return stringBuilder.toString();
    }
}
