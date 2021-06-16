package com.rookiefly.open.dubbo.monitor.controller;

import com.google.common.collect.Lists;
import com.rookiefly.open.dubbo.monitor.domain.DubboInvoke;
import com.rookiefly.open.dubbo.monitor.domain.DubboInvokeLineChart;
import com.rookiefly.open.dubbo.monitor.domain.LineChartSeries;
import com.rookiefly.open.dubbo.monitor.service.DubboMonitorService;
import com.rookiefly.open.dubbo.monitor.support.CommonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private DubboMonitorService dubboMonitorService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "loadTopData")
    public CommonResponse loadTopDate(@ModelAttribute DubboInvoke dubboInvoke) {
        CommonResponse commonResponse = CommonResponse.createCommonResponse();
        List<DubboInvokeLineChart> dubboInvokeLineChartList = new ArrayList<>();
        DubboInvokeLineChart successDubboInvokeLineChart = new DubboInvokeLineChart();
        List<String> sxAxisCategories = Lists.newArrayList();
        LineChartSeries slineChartSeries = new LineChartSeries();
        List<double[]> sdataList = Lists.newArrayList();
        double[] data;
        Map<String, List<DubboInvoke>> dubboInvokeMap = dubboMonitorService.countDubboInvokeTopTen(dubboInvoke);
        List<DubboInvoke> success = dubboInvokeMap.get("success");
        for (DubboInvoke di : success) {
            sxAxisCategories.add(di.getMethod());
            data = new double[]{di.getSuccess()};
            sdataList.add(data);
        }
        slineChartSeries.setData(sdataList);
        slineChartSeries.setName("provider");

        successDubboInvokeLineChart.setXAxisCategories(sxAxisCategories);
        successDubboInvokeLineChart.setSeriesData(Arrays.asList(slineChartSeries));
        successDubboInvokeLineChart.setChartType("SUCCESS");
        successDubboInvokeLineChart.setTitle("The Top 20 of Invoke Success");
        successDubboInvokeLineChart.setYAxisTitle("t");
        dubboInvokeLineChartList.add(successDubboInvokeLineChart);

        DubboInvokeLineChart failureDubboInvokeLineChart = new DubboInvokeLineChart();
        List<String> fxAxisCategories = Lists.newArrayList();
        LineChartSeries flineChartSeries = new LineChartSeries();
        List<double[]> fdataList = Lists.newArrayList();
        List<DubboInvoke> failure = dubboInvokeMap.get("failure");
        for (DubboInvoke di : failure) {
            fxAxisCategories.add(di.getMethod());
            data = new double[]{di.getFailure()};
            fdataList.add(data);
        }
        flineChartSeries.setData(fdataList);
        flineChartSeries.setName("provider");

        failureDubboInvokeLineChart.setXAxisCategories(fxAxisCategories);
        failureDubboInvokeLineChart.setSeriesData(Arrays.asList(flineChartSeries));
        failureDubboInvokeLineChart.setChartType("FAILURE");
        failureDubboInvokeLineChart.setTitle("The Top 20 of Invoke Failure");
        failureDubboInvokeLineChart.setYAxisTitle("t");
        dubboInvokeLineChartList.add(failureDubboInvokeLineChart);

        commonResponse.setData(dubboInvokeLineChartList);
        return commonResponse;
    }
}
