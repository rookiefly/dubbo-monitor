package com.rookiefly.open.dubbo.monitor.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DubboInvokeLineChart implements Serializable{

    private String method;

    private String chartType;

    private String title;

    private String subtitle;

    private List<String> xAxisCategories;

    private String yAxisTitle;

    private List<LineChartSeries> seriesData;
}
