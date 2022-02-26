package com.asobal.hackathon.controller;

import com.asobal.hackathon.model.Message;
import com.asobal.hackathon.service.MyEventProcessor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class NotificationController {

    private MyEventProcessor myEventProcessor = new MyEventProcessor();



    Flux<Message> bridge = Flux.create(messageFluxSink -> {
        myEventProcessor.register(messageFluxSink);
    });


    @GetMapping(path = "/consume")
    public Flux<ServerSentEvent<Message>> getNotifications() {
        return bridge.map(message ->
            ServerSentEvent.<Message> builder()
                    .data(message)
                .build()
        );
    }

    @PostMapping(path = "/publish")
    public void publishMessage(@RequestBody Message message) {
        myEventProcessor.publish(message);
    }
}
