package com.log.sonatus.service;

import java.time.Instant;
import java.util.List;

import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.log.sonatus.model.LogEntry;
import com.log.sonatus.repository.LogEntryRepository;

@Service
public class LogEntryService {

    @Autowired
    private LogEntryRepository logRepository;

    public void saveLog(LogEntry log) {
        logRepository.save(log);
    }

    public List<LogEntry> getLogs(String serviceName, Instant start, Instant end) {
        return logRepository.findByServiceNameAndTimeRange(serviceName, start, end);
    }

    public void deleteLogsOlderThan(Instant cutoff) {
        logRepository.deleteAll(
                logRepository.findAll().stream()
                        .filter(log -> log.getTimestamp().isBefore(cutoff))
                        .toList());

    }

}
