package com.devng.chdb_crud.controller;



import com.devng.chdb_crud.model.FlowData;

import com.devng.chdb_crud.service.FlowDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ssh")
public class FlowDataController {

    private final FlowDataService flowDataService;

    public FlowDataController(FlowDataService flowDataService) {
        this.flowDataService = flowDataService;
    }

    @GetMapping
    public List<FlowData> getAllFlowData() {
        return flowDataService.getFlowData();
    }
}
