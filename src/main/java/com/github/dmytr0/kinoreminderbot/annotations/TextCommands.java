package com.github.dmytr0.kinoreminderbot.annotations;

import org.springframework.stereotype.Service;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Service
@Retention(RUNTIME)
@Target(TYPE)
public @interface TextCommands {
    String[] targets();
}
