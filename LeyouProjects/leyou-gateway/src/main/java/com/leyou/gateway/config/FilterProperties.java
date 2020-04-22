package com.leyou.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-04-13 17:01
 */
@ConfigurationProperties(prefix = "leyou.filter")
public class FilterProperties {

    private List<String> allowPaths;

    public List<String> getAllowPaths() {
        return allowPaths;
    }

    public void setAllowPaths(List<String> allowPaths) {
        this.allowPaths = allowPaths;
    }
}