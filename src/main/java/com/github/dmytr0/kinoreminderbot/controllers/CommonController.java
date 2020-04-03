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

    @GetMapping("/")
    public String startPage() {
        return "main";
    }

    @GetMapping("/wh")
    public String whPage() {
        return "webhook";
    }

    @GetMapping(value = "/img/{name}", produces = IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] img(@PathVariable String name) throws IOException {
        InputStream in = getClass().getResourceAsStream("/img/" + name);
        return IOUtils.toByteArray(in);
    }

    @PostMapping("/setWebhook")
    public String setWebhook(@RequestParam(name = "host", required = false) String host) {
        telegramSender.setWebhook(host);
        return "main";
    }
}
