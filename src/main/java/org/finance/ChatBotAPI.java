package org.finance;

import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import jakarta.inject.Inject;

@WebSocket(path = "/chatbot")
public class ChatBotAPI {

    @Inject
    Bot bot;

    @OnOpen
    public String onOpen() {
        return "Hello, I'm Bob, how can I help you?";
    }

    @OnTextMessage
    public String onMessage(String message) {
        return bot.chat(message);
    }

}
