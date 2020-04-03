package com.github.dmytr0.kinoreminderbot.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

public enum MovieSource {

    IVI(0, 10),
    MDB(1, 20),
    KTUA(2, 0);


    MovieSource(int code, int priority) {
        this.code = code;
        this.priority = priority;
    }

    @Getter(onMethod = @__(@JsonValue))
    private final int code;

    @Getter
    private final int priority;

    @JsonCreator
    public static MovieSource fromCode(int k) {
        return Arrays.stream(values())
                .filter(code -> code.getCode() == k)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Movie source is not supported"));
    }
}
