package com.github.dmytr0.kinoreminderbot.controllers;

import com.github.dmytr0.kinoreminderbot.service.TelegramSender;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;


@SuppressWarnings("SameReturnValue")
@Controller
@RequiredArgsConstructor
public class CommonController {

    private final TelegramSender telegramSender;

    @RequestMapping("/")
    public String startPage() {
        return "main";
    }

    @GetMapping("/wh")
    public String whPage() {
        return "webhook";
    }

//


    @PostMapping("/setWebhook")
    public String setWebhook(@RequestParam(name = "host", required = false) String host) {
        telegramSender.setWebhook(host);
        return "main";
    }
}
