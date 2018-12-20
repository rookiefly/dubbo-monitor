package com.rookiefly.open.dubbo.monitor.domain;

import com.alibaba.dubbo.common.status.Status;
import lombok.Data;

import java.io.Serializable;

@Data
public class DubboStatus implements Serializable {

    private String name;

    private Status status;

    private String description;
}
