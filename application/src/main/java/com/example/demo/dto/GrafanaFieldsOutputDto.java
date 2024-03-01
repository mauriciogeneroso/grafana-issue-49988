package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GrafanaFieldsOutputDto(@JsonProperty("nodes_fields")
                                       List<GrafanaFieldDto> nodeFields,
                                     @JsonProperty("edges_fields")
                                       List<GrafanaFieldDto> edgeFields) {
}
