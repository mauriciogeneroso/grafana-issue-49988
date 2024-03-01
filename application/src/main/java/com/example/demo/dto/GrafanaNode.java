package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

public record GrafanaNode(String id,
                          @JsonProperty("title") String title,
                          @JsonProperty("subtitle") String subtitle,
                          @JsonProperty("mainstat") String mainstat,
                          @JsonProperty("secondarystat") String secondarystat,
                          @JsonProperty("arc__failed") double failed,
                          @JsonProperty("arc__passed") double passed) {

    public GrafanaNode {
        if (!StringUtils.hasLength(id)) {
            throw new IllegalStateException("Grafana node id cannot be null");
        }
    }
}
