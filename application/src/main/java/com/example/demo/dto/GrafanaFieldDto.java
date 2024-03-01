package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.dto.FieldType.NUMBER;
import static com.example.demo.dto.FieldType.STRING;

/**
 * Those field values defined in the builders are static defined in the api documentation:
 * https://grafana.com/docs/grafana/latest/visualizations/node-graph/#data-api
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record GrafanaFieldDto(@JsonProperty("field_name") String fieldName,
                              String type,
                              String displayName,
                              String color) {

    public GrafanaFieldDto(String fieldName, String type) {
        this(fieldName, type, null, null);
    }

    public GrafanaFieldDto(String fieldName, String type, String displayName) {
        this(fieldName, type, displayName, null);
    }

    public static NodeFieldsBuilder nodeFieldsBuilder() {
        return new NodeFieldsBuilder();
    }

    public static EdgeFieldsBuilder edgeFieldsBuilder() {
        return new EdgeFieldsBuilder();
    }

    public static class NodeFieldsBuilder {

        private static final List<GrafanaFieldDto> MANDATORY_NODE_FIELDS =
            List.of(new GrafanaFieldDto("id", STRING.getName()));

        private boolean title;
        private boolean subtitle;
        private boolean mainstat;
        private boolean secondarystat;
        private boolean color;

        public NodeFieldsBuilder withTitle() {
            this.title = true;
            return this;
        }

        public NodeFieldsBuilder withSubtitle() {
            this.subtitle = true;
            return this;
        }

        public NodeFieldsBuilder withMainStat() {
            this.mainstat = true;
            return this;
        }

        public NodeFieldsBuilder withSecondaryStat() {
            this.secondarystat = true;
            return this;
        }

        public NodeFieldsBuilder withColor() {
            this.color = true;
            return this;
        }


        public List<GrafanaFieldDto> build() {
            List<GrafanaFieldDto> nodeFields = new ArrayList<>(MANDATORY_NODE_FIELDS);

            if (title) {
                nodeFields.add(new GrafanaFieldDto("title", STRING.getName(), "title"));
            }

            if (subtitle) {
                nodeFields.add(new GrafanaFieldDto("subtitle", STRING.getName(), "subtitle"));
            }

            if (mainstat) {
                nodeFields.add(new GrafanaFieldDto("mainstat", STRING.getName(), "mainstat"));
            }

            if (secondarystat) {
                nodeFields.add(new GrafanaFieldDto("secondarystat", STRING.getName(), "secondarystat"));
            }

            if (color) {
                nodeFields.add(new GrafanaFieldDto("arc__failed", NUMBER.getName(), "failed", "red"));
                nodeFields.add(new GrafanaFieldDto("arc__passed", NUMBER.getName(), "passed", "green"));
            }

            return nodeFields;
        }

    }

    public static class EdgeFieldsBuilder {

        private static final List<GrafanaFieldDto> MANDATORY_EDGE_FIELDS = List.of(
            new GrafanaFieldDto("id", STRING.getName()),
            new GrafanaFieldDto("source", STRING.getName()),
            new GrafanaFieldDto("target", STRING.getName())
        );

        private boolean mainStat;
        private boolean secondaryStat;
        private boolean description;

        public EdgeFieldsBuilder withMainStat() {
            this.mainStat = true;
            return this;
        }

        public EdgeFieldsBuilder withSecondaryStat() {
            this.secondaryStat = true;
            return this;
        }

        public EdgeFieldsBuilder withDescription() {
            this.description = true;
            return this;
        }

        public List<GrafanaFieldDto> build() {
            List<GrafanaFieldDto> edgeFields = new ArrayList<>(MANDATORY_EDGE_FIELDS);

            if (mainStat) {
                edgeFields.add(new GrafanaFieldDto("mainstat", STRING.getName()));
            }

            if (secondaryStat) {
                edgeFields.add(new GrafanaFieldDto("secondarystat", NUMBER.getName()));
            }

            if (description) {
                edgeFields.add(new GrafanaFieldDto("detail__description", STRING.getName()));
            }

            return edgeFields;
        }
    }
}
