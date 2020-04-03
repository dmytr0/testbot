package com.github.dmytr0.kinoreminderbot.config;

import com.github.dmytr0.kinoreminderbot.annotations.CallbackCommands;
import com.github.dmytr0.kinoreminderbot.annotations.TextCommands;
import com.github.dmytr0.kinoreminderbot.service.commands.CallbackCommand;
import com.github.dmytr0.kinoreminderbot.service.commands.TextCommand;
import feign.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;


@Log4j2
@EnableAsync
@Configuration
@RequiredArgsConstructor
public class MainConfig {

    private final ApplicationContext context;
    private static String SCAN_PACKAGE = "com.github.dmytr0.kinoreminderbot.service.commands";

    @Bean(name = "telegramTP")
    Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(100);
        return executor;
    }

    @Bean(name = "moviesAsync")
    Executor moviesExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(1000);
        return executor;
    }

    @Bean(name = "scheduler")
    ScheduledExecutorService scheduledExecutor() {
        return Executors.newScheduledThreadPool(1);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


    @Bean("textCommands")
    public Map<String, TextCommand> textCommands(Set<TextCommand> commands) {
        return commands.stream().map(TextCommand::getClass)
                .flatMap(type -> stream(type.getAnnotation(TextCommands.class).targets()).map(target -> Pair.of(target, type)))
                .collect(toMap(Pair::getKey, pair -> (TextCommand) context.getBean(pair.getValue())));
    }

    @Bean("callbackCommands")
    public Map<String, CallbackCommand> callbackCommands(Set<CallbackCommand> commands) {
        return commands.stream().map(CallbackCommand::getClass)
                .flatMap(type -> stream(type.getAnnotation(CallbackCommands.class).targets()).map(target -> Pair.of(target, type)))
                .collect(toMap(Pair::getKey, pair -> (CallbackCommand) context.getBean(pair.getValue())));
    }

}
