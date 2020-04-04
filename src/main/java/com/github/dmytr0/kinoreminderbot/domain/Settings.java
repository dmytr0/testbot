package com.github.dmytr0.kinoreminderbot.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity(name = "settings")
public class Settings {

    @JsonProperty("id")
    private String _id;
    private String value;
    private Map<String, String> param;
}
