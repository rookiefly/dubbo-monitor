package com.rookiefly.open.dubbo.monitor.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DubboHost implements Serializable {

    private String host;

    private String hostname;

    private String application;

    private String organization;

    private String owner;

    private int providerCount;

    private int consumerCount;
}
