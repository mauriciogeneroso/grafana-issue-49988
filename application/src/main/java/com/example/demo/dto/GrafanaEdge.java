package com.example.demo.dto;

import org.springframework.util.StringUtils;

public record GrafanaEdge(String id, String source, String target, String mainStat) {

    public GrafanaEdge(String id, String source, String target) {
        this(id, source, target, null);
    }

    public GrafanaEdge {
        if (!StringUtils.hasLength(id)) {
            throw new IllegalStateException("Grafana edge id cannot be null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GrafanaEdge other = (GrafanaEdge) o;
        return id.equals(other.id) && source.equals(other.source) && target.equals(other.target);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
