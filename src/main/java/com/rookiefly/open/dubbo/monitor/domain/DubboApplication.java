package com.rookiefly.open.dubbo.monitor.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DubboApplication implements Serializable {

    private String name;

    private String owner;

    private String organization;

    private int providerCount;

    private int consumerCount;

    private int efferentCount;

    private int afferentCount;
}
