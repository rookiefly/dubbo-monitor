package com.rookiefly.open.dubbo.monitor.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DubboInvoke implements Serializable {

    private Long id;

    private String invokeId;

    private Date invokeDate;

    private String service;

    private String method;

    private String consumer;

    private String provider;

    private String type = "provider";

    private double success;

    private double failure;

    private double elapsed;

    private double concurrent;

    private double maxElapsed;

    private double maxConcurrent;

    private double invokeTime;

    // ====================查询辅助参数===================
    /**
     * 统计时间粒度(毫秒)
     */
    private long timeParticle = 60000;

    private Date invokeDateFrom;

    private Date invokeDateTo;
}
