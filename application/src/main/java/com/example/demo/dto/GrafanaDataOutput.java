package com.example.demo.dto;

import java.util.List;

public record GrafanaDataOutput(List<GrafanaNode> nodes, List<GrafanaEdge> edges) {
}
