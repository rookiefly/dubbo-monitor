package com.rookiefly.open.dubbo.monitor.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DubboRegistry implements Serializable {

    private String server;

    private String hostname;

    private boolean available;

    private int registeredCount;

    private int subscribedCount;
}
