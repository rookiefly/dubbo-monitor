package com.rookiefly.open.dubbo.monitor.support;

/**
 * 特殊字符串替换
 */
public class StringMethods {

    public static String replaceMethod(String text, String regex, String replacement) {
        String s = text.replaceAll(regex, replacement);
        if (s.endsWith("_")) {
            return s.substring(0, s.length() - 1);
        }
        return s;
    }

    public static void main(String[] args) {
        String replace = StringMethods.replaceMethod("dispatchRegisterCouponsAndSendSms(String,String,String,String)", "\\(|\\)|,", "_");
        System.out.println(replace);
    }
}
