package com.rookiefly.open.dubbo.monitor.support;

import java.util.UUID;

/**
 * UUID生成工具类
 *
 */
public class UuidUtil {

    /**
     * 获得UUID的方法
     *
     * @return
     */
    public static String createUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}