package com.log.sonatus.controller;

import java.time.Instant;
import java.util.List;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.log.sonatus.model.LogEntry;
import com.log.sonatus.service.LogEntryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin("*")
@RequestMapping("/logs")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogEntryService logEntryService;

    @PostMapping()
    public void addLogs(@RequestBody @Valid LogEntry log) {
        logger.info("Saving log for service '{}': {}", log.getServiceName(), log.getMessage());
        logEntryService.saveLog(log);
    }

    @GetMapping
    public List<LogEntry> getLogs(@RequestParam String service,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant end) {
        logger.info("Fetching logs for service '{}' from {} to {} ", service, start, end);
        return logEntryService.getLogs(service, start, end);
    }

}
