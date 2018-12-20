package com.rookiefly.open.dubbo.monitor.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DubboStatistics implements Serializable {

    private String method;

    private double consumerSuccess;

    private double providerSuccess;

    private double consumerFailure;

    private double providerFailure;

    private double consumerAvgElapsed;

    private double providerAvgElapsed;

    private double consumerMaxElapsed;

    private double providerMaxElapsed;

    private double consumerMaxConcurrent;

    private double providerMaxConcurrent;
}
