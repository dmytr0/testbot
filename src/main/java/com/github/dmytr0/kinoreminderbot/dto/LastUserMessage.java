package com.github.dmytr0.kinoreminderbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LastUserMessage {

    private Integer id;
    private LocalDateTime date;
    private String text;
}
