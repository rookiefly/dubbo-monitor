package com.rookiefly.open.dubbo.monitor.domain;

import lombok.Data;
import org.apache.dubbo.common.status.Status;

import java.io.Serializable;

@Data
public class DubboStatus implements Serializable {

    private String name;

    private Status status;

    private String description;
}
