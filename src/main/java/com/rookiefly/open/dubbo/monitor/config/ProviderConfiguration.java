package com.rookiefly.open.dubbo.monitor.config;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDubbo(scanBasePackages = "com.rookiefly.open.dubbo.monitor.service")
@DubboComponentScan(basePackages = "com.rookiefly.open.dubbo.monitor.service")
public class ProviderConfiguration {

}