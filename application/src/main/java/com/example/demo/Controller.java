package com.example.demo;

import com.example.demo.dto.GrafanaDataOutput;
import com.example.demo.dto.GrafanaEdge;
import com.example.demo.dto.GrafanaFieldDto;
import com.example.demo.dto.GrafanaFieldsOutputDto;
import com.example.demo.dto.GrafanaNode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping(value = "/api/graph", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private static final int NUMBER_OF_NODES = 500;

    /**
     * Endpoint used by grafana to set the used fields on the graph
     *
     * @param query parameters not used, grafana sends it for both /fields and /data endpoints
     * @return all necessary fields
     */
    @GetMapping("/fields")
    public GrafanaFieldsOutputDto getFields(@RequestParam(value = "query", required = false) String query) {
        var nodeFields = GrafanaFieldDto.nodeFieldsBuilder().withTitle().withSubtitle().withMainStat().withSecondaryStat().withColor().build();

        var edgeFields = GrafanaFieldDto.edgeFieldsBuilder().withMainStat().build();
        return new GrafanaFieldsOutputDto(nodeFields, edgeFields);
    }

    @GetMapping("/data")
    public GrafanaDataOutput getData(@RequestParam(value = "query", required = false) String query) {
        var nodes = new ArrayList<GrafanaNode>();
        var mainNode = new GrafanaNode("1", "main-1", "sub-1", "main-1", "secondary-1", 0, 1);

        for (var i = 1; i <= NUMBER_OF_NODES; i++) {
            nodes.add(new GrafanaNode(String.valueOf(i), "main-" + i, "sub-" + i, "main-" + i, "secondary-" + 1, 0, 1));
        }

        List<GrafanaEdge> edges = new ArrayList<>();
        for (var i = 0; i < nodes.size(); i++) {
            edges.add(new GrafanaEdge(String.valueOf(i + 1), mainNode.id(), String.valueOf(nodes.get(i).id())));
        }

        nodes.add(mainNode);
        return new GrafanaDataOutput(nodes, edges);
    }
}
