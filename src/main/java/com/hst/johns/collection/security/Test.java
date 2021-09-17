package com.hst.johns.collection.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {

        String tel1 = "13022458889" ;
        String tel2 = "18901433451" ;
        String tel3 = "13888348888" ;//匹配3位以上豹子号

        //匹配3位以上豹子号
        String pattern1 = "([\\d])\\1{2,}" ;
        //匹配3位或以上顺增或顺降

        Pattern p = Pattern.compile(pattern1);
        Matcher m1 = p.matcher(tel1);
        Matcher m2 = p.matcher(tel2);
        Matcher m3 = p.matcher(tel3);
        System.out.println("3位以上豹子号::" + m1.find());
        System.out.println("3位以上豹子号:" + m2.find());
        System.out.println("3位以上豹子号:" + m3.find());
        System.out.println("*******分割线*******");//匹配3位或上顺增或顺降

        //匹配3位或以上顺增或顺降
        String pattern2 = "(?:(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){2,}|(?:9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){2,})\\d" ;
        Pattern p2 = Pattern.compile(pattern2);

        String tel4 = "13907311236" ;
        String tel5 = "13945678134" ;
        String tel6 = "15930308888" ;//匹配3位以上豹子号

        Matcher m4 = p2.matcher(tel4);
        Matcher m5 = p2.matcher(tel5);
        Matcher m6 = p2.matcher(tel6);
        System.out.println("3位顺增或顺降:" + m4.find());
        System.out.println("3位顺增或顺降:" + m5.find());
        System.out.println("3位顺增或顺降:" + m6.find());
        System.out.println("*******分割线*******");
    }
}
