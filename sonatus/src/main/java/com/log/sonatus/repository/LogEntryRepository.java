package com.log.sonatus.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.log.sonatus.model.LogEntry;

@Repository
public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {

    @Query("SELECT le FROM LogEntry le WHERE le.serviceName = :serviceName AND le.timestamp BETWEEN :start AND :end ORDER BY le.timestamp ASC")
    List<LogEntry> findByServiceNameAndTimeRange(String serviceName, Instant start, Instant end);
}
