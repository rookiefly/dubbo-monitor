package com.rookiefly.open.dubbo.monitor.controller;

import com.rookiefly.open.dubbo.monitor.service.DubboMonitorService;
import com.rookiefly.open.dubbo.monitor.domain.DubboInvoke;
import com.rookiefly.open.dubbo.monitor.domain.DubboStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/services/statistics")
public class StatisticsController {

    @Autowired
    private DubboMonitorService dubboMonitorService;

    @RequestMapping()
    public String index(@ModelAttribute DubboInvoke dubboInvoke, Model model) {
        //获取Service方法
        List<String> methods = dubboMonitorService.getMethodsByService(dubboInvoke);
        List<DubboInvoke> dubboInvokes;
        List<DubboStatistics> dubboStatisticses = new ArrayList<DubboStatistics>();
        DubboStatistics dubboStatistics;
        for (String method : methods) {
            dubboStatistics = new DubboStatistics();
            dubboStatistics.setMethod(method);
            dubboInvoke.setMethod(method);
            dubboInvoke.setType("provider");
            dubboInvokes = dubboMonitorService.countDubboInvokeInfo(dubboInvoke);
            for (DubboInvoke di : dubboInvokes) {
                if (di == null) {
                    continue;
                }
                dubboStatistics.setProviderSuccess(di.getSuccess());
                dubboStatistics.setProviderFailure(di.getFailure());
                dubboStatistics.setProviderAvgElapsed(di.getElapsed());
                dubboStatistics.setProviderMaxElapsed(di.getMaxElapsed());
                dubboStatistics.setProviderMaxConcurrent(di.getMaxConcurrent());
            }
            dubboInvoke.setType("consumer");
            dubboInvokes = dubboMonitorService.countDubboInvokeInfo(dubboInvoke);
            for (DubboInvoke di : dubboInvokes) {
                if (di == null) {
                    continue;
                }
                dubboStatistics.setConsumerSuccess(di.getSuccess());
                dubboStatistics.setConsumerFailure(di.getFailure());
                dubboStatistics.setConsumerAvgElapsed(di.getElapsed());
                dubboStatistics.setConsumerMaxElapsed(di.getMaxElapsed());
                dubboStatistics.setConsumerMaxConcurrent(di.getMaxConcurrent());
            }
            dubboStatisticses.add(dubboStatistics);
        }
        model.addAttribute("rows", dubboStatisticses);
        model.addAttribute("service", dubboInvoke.getService());
        return "service/statistics";
    }

}

