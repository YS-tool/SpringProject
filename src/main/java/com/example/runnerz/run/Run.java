package com.example.runnerz.run;

import com.example.runnerz.RunnerzApplication;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location
) {
    private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);
    public Run{
        if(completedOn.isBefore(startedOn)) {
            throw new IllegalArgumentException("completedOn should be before startedOn");
        }
    }
}
