package com.github.dmytr0.kinoreminderbot.dto.ivi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

public enum IviKind {

    FILM(1),
    COMPILATION(2);



    IviKind(int kind) {
        this.kind = kind;
    }

    @Getter(onMethod = @__(@JsonValue))
    private final int kind;

    @JsonCreator
    public static IviKind fromCode(int k) {
        return Arrays.stream(values())
                .filter(kind -> kind.getKind() == k)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ivi kind is not supported"));
    }
}
