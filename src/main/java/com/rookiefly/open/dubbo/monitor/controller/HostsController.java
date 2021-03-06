package com.rookiefly.open.dubbo.monitor.controller;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.rookiefly.open.dubbo.monitor.domain.DubboHost;
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
@RequestMapping("/hosts")
public class HostsController {

    @Resource
    private RegistryContainer registryContainer;

    @RequestMapping(method = RequestMethod.GET)
    public String hosts(Model model) {
        List<DubboHost> rows = new ArrayList<>();
        Set<String> hosts = registryContainer.getHosts();

        if (!hosts.isEmpty()) {
            DubboHost dubboHost;
            for (String host : hosts) {
                dubboHost = new DubboHost();

                dubboHost.setHost(host);
                dubboHost.setHostname(NetUtils.getHostName(host));

                List<URL> providers = registryContainer.getProvidersByHost(host);
                List<URL> consumers = registryContainer.getConsumersByHost(host);

                if (!providers.isEmpty() || !consumers.isEmpty()) {
                    URL url = !providers.isEmpty() ? providers.iterator().next() : consumers.iterator().next();
                    dubboHost.setApplication(url.getParameter(Constants.APPLICATION_KEY, ""));
                    dubboHost.setOwner(url.getParameter("owner", ""));
                    dubboHost.setOrganization((url.hasParameter("organization") ? url.getParameter("organization") : ""));
                }

                int providerSize = providers == null ? 0 : providers.size();
                dubboHost.setProviderCount(providerSize);

                int consumerSize = consumers == null ? 0 : consumers.size();
                dubboHost.setConsumerCount(consumerSize);

                rows.add(dubboHost);
            }
        }

        model.addAttribute("rows", rows);
        return "host/hosts";
    }

    @RequestMapping(value = "/providers", method = RequestMethod.GET)
    public String providers(@RequestParam String host, Model model) {
        List<URL> providers = registryContainer.getProvidersByHost(host);
        List<String> rows = new ArrayList<>();
        if (!providers.isEmpty()) {
            for (URL u : providers) {
                rows.add(u.toFullString());
            }
        }

        model.addAttribute("host", host);
        model.addAttribute("address", NetUtils.getHostName(host) + "/" + host);
        model.addAttribute("rows", rows);
        return "host/providers";
    }

    @RequestMapping(value = "/consumers", method = RequestMethod.GET)
    public String consumers(@RequestParam String host, Model model) {
        List<URL> consumers = registryContainer.getConsumersByHost(host);
        List<String> rows = new ArrayList<>();
        if (!consumers.isEmpty()) {
            for (URL u : consumers) {
                rows.add(u.toFullString());
            }
        }

        model.addAttribute("host", host);
        model.addAttribute("address", NetUtils.getHostName(host) + "/" + host);
        model.addAttribute("rows", rows);
        return "host/consumers";
    }

}
