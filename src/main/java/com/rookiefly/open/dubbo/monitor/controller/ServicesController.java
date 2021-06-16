package com.rookiefly.open.dubbo.monitor.controller;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.rookiefly.open.dubbo.monitor.domain.DubboService;
import com.rookiefly.open.dubbo.monitor.service.RegistryContainer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/services")
public class ServicesController {

    @Resource
    private RegistryContainer registryContainer;

    @RequestMapping(method = RequestMethod.GET)
    public String services(Model model) {
        List<DubboService> rows = new ArrayList<>();
        Set<String> services = registryContainer.getServices();

        if (!services.isEmpty()) {
            DubboService dubboService;
            for (String service : services) {
                dubboService = new DubboService();
                dubboService.setName(service);

                List<URL> providers = registryContainer.getProvidersByService(service);
                int providerSize = providers == null ? 0 : providers.size();
                dubboService.setProviderCount(providerSize);

                List<URL> consumers = registryContainer.getConsumersByService(service);
                int consumerSize = consumers == null ? 0 : consumers.size();
                dubboService.setConsumerCount(consumerSize);

                if (providerSize > 0) {
                    URL provider = providers.iterator().next();
                    dubboService.setApplication(provider.getParameter(Constants.APPLICATION_KEY, ""));
                    dubboService.setOwner(provider.getParameter("owner", ""));
                    dubboService.setOrganization((provider.hasParameter("organization") ? provider.getParameter("organization") : ""));
                }

                rows.add(dubboService);
            }
        }

        model.addAttribute("rows", rows);
        return "service/services";
    }

    @RequestMapping(value = "/providers", method = RequestMethod.GET)
    public String providers(@RequestParam String service, Model model) {
        List<URL> providers = registryContainer.getProvidersByService(service);
        List<String> rows = new ArrayList<>();
        if (!providers.isEmpty()) {
            for (URL u : providers) {
                rows.add(u.toFullString());
            }
        }

        model.addAttribute("service", service);
        model.addAttribute("rows", rows);
        return "service/providers";
    }

    @RequestMapping(value = "/consumers", method = RequestMethod.GET)
    public String consumers(@RequestParam String service, Model model) {
        List<URL> consumers = registryContainer.getConsumersByService(service);
        List<String> rows = new ArrayList<>();
        if (!consumers.isEmpty()) {
            for (URL u : consumers) {
                rows.add(u.toFullString());
            }
        }

        model.addAttribute("service", service);
        model.addAttribute("rows", rows);
        return "service/consumers";
    }
}
