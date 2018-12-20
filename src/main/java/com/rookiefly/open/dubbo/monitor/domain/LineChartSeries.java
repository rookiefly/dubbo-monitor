
package com.rookiefly.open.dubbo.monitor.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LineChartSeries implements Serializable {

    private String name;

    private List<double[]> data;

}
