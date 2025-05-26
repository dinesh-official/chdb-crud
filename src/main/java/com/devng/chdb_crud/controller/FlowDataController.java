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
            @RequestParam(name = "p", defaultValue = "22") int ipDstPort,           // p = port
            @RequestParam(name = "a", defaultValue = "132420") int dstAsn,        // asn = destination ASN
            @RequestParam(name = "h", defaultValue = "600") int intervalHour,    // i = interval
            @RequestParam(name = "fc", defaultValue = "1") int flowCountThreshold,   // t = threshold
            @RequestParam(name = "rc", defaultValue = "2") int maxResults             // c = count (max results)
    ) {
        return flowDataService.getFlowData(ipDstPort, dstAsn, intervalHour, flowCountThreshold, maxResults);
    }
}
