package com.log.sonatus.scheduler;

import java.time.Duration;
import java.time.Instant;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.log.sonatus.service.LogEntryService;

@Component
public class LogCleanupScheduler {

    private static final Logger logger = LoggerFactory.getLogger(LogCleanupScheduler.class);

    @Autowired
    private LogEntryService logEntryService;

    @Scheduled(fixedRate = 60000)
    public void logsCleanUps() {
        Instant cutoff = Instant.now().minus(Duration.ofHours(1));
        logger.warn("Cleaning Logs older than {}", cutoff);
        logEntryService.deleteLogsOlderThan(cutoff);
    }
}
