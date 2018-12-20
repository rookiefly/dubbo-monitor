package com.rookiefly.open.dubbo.monitor.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DubboServer implements Serializable {

    private String address;

    private int port;

    private String hostname;

    private int clientCount;
}
