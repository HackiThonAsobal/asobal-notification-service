package com.asobal.hackathon.service;

import com.asobal.hackathon.model.Message;
import reactor.core.publisher.FluxSink;

public class MyEventProcessor {

    FluxSink<Message> fluxSink;

    public void register(FluxSink<Message> incomingFluxSink) {
        fluxSink = incomingFluxSink;
    }

    public void publish(Message message) {
        fluxSink.next(message);
    }
}
