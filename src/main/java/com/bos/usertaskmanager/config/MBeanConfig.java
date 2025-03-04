package com.bos.usertaskmanager.config;

import org.springframework.boot.admin.SpringApplicationAdminMXBeanRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MBeanConfig {

    @Bean
    public MBeanExporter mBeanExporter() {
        MBeanExporter exporter = new MBeanExporter();
        Map<String, Object> beans = new HashMap<>();
        beans.put("org.springframework.boot:type=Admin,name=SpringApplication", new SpringApplicationAdminMXBeanRegistrar());
        exporter.setBeans(beans);
        return exporter;
    }
}
