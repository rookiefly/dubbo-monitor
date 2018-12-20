package com.rookiefly.open.dubbo.monitor.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DubboService implements Serializable {

    private String name;

    private String application;

    private String organization;

    private String owner;

    private int providerCount;

    private int consumerCount;
}
