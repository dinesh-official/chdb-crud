package com.devng.chdb_crud.controller;



import com.devng.chdb_crud.model.FlowData;

import com.devng.chdb_crud.service.FlowDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlowDataController {

    private final FlowDataService flowDataService;


    public FlowDataController(FlowDataService flowDataService) {
        this.flowDataService = flowDataService;
    }


    @GetMapping("/ssh")
    public List<FlowData> getFlowData(
            @RequestParam(name = "p", defaultValue = "22") int ipDstPort,
            @RequestParam(name = "a", defaultValue = "132420") int dstAsn,
            @RequestParam(name = "h", defaultValue = "600") int intervalSeconds,
            @RequestParam(name = "fc", defaultValue = "1") int flowCountThreshold,
            @RequestParam(name = "rc", defaultValue = "2") int maxResults,
            @RequestParam(name = "np", defaultValue = "2") int noPasswordFlag
    ) {
        return flowDataService.getFlowData(ipDstPort, dstAsn, intervalSeconds, flowCountThreshold, maxResults, noPasswordFlag);
    }
}
