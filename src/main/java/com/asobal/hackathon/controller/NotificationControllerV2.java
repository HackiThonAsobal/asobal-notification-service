package com.asobal.hackathon.controller;

import com.asobal.hackathon.model.Message;
import com.asobal.hackathon.service.SseEmitters;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ScheduledExecutorService;

@RestController
@RequestMapping(path = "v2")
public class NotificationControllerV2 {

    private final SseEmitters emitters = new SseEmitters();

    @GetMapping(path = "/performance", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    SseEmitter getPerformance() {
        return emitters.add(new SseEmitter());
    }

    @PostMapping(path ="publish")
    void publishMessage(@RequestBody Message message) {
        emitters.send(message);
    }
}
