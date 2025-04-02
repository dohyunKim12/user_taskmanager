package com.bos.usertaskmanager.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "utmd-agent")
@Getter
@Setter
public class UtmdProperties {
    private List<String> ipPortList = new ArrayList<>();
}