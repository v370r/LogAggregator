package com.log.sonatus.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "log_entry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "serviceName is required")
    private String serviceName;

    @NotNull(message = "Timestamp is required")
    private Instant timestamp;

    @NotBlank(message = "Message is required")
    private String message;
}
