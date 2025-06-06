package com.devng.chdb_crud.controller;



import com.devng.chdb_crud.model.FlowData;

import com.devng.chdb_crud.model.Mail;
import com.devng.chdb_crud.service.MailService;
import com.devng.chdb_crud.service.SshServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FlowDataController {

    private final SshServices flowDataService;
    private final MailService mailService;

    public FlowDataController(SshServices flowDataService , MailService mailService) {
        this.flowDataService = flowDataService;
        this.mailService = mailService;
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
        return flowDataService.getSsh(ipDstPort, dstAsn, intervalSeconds, flowCountThreshold, maxResults, noPasswordFlag);
    }

    @GetMapping("/mail")
    public List<Mail> getMailRecords(
            @RequestParam(name = "vi", required = false) String vmId,
            @RequestParam(name = "vo", required = false) String vmOwner,
            @RequestParam(name = "vip", required = false) String vmIp,
            @RequestParam(name = "vn", required = false) String vmName,
            @RequestParam(name = "mt", required = false) String mailType,
            @RequestParam(name = "d", required = false, defaultValue = "0") int lastDays
    ) {
        return mailService.fetchMailRecords(vmId, vmOwner, vmIp, vmName, mailType, lastDays);
    }

}
