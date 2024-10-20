package org.finance;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.finance.external.CustomLLMModelRestClient;
import org.finance.external.models.requests.ChatCompletionRequest;
import org.finance.external.models.requests.ChatMessage;
import org.finance.external.models.responses.ChatCompletionResponse;

import java.util.ArrayList;
import java.util.List;

@Path("/chatbot")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChatBotAPI {

    @Inject
    @RestClient
    CustomLLMModelRestClient llmClient;

    String chatId;

    List<ChatMessage> messages = new ArrayList<>();

    @POST
    @Path("/chatcompletionresponse")
    public ChatCompletionResponse chat(@Schema(example = "Hola, quien eres?") String message) {
        ChatMessage systemMessage =
                new ChatMessage(
                        "system",
                        "Answer as you are my aunt called Lorena. She lives in Switzerland and she has 3 kids, Giuliana, Michaela, Sven. His hubsband is called Walter Hersche. Her Mom is Victoria and she is going to fly tomorrow to Ecuador. She is under stress."
                );
        ChatMessage userMessage = new ChatMessage(
                "user",
                message
        );

        if (chatId == null) {
            messages.addAll(List.of(systemMessage, userMessage));
        } else {
            messages.add(userMessage);
        }
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("qwen2.5-coder-7b-instruct")
                .messages(messages)
                .temperature(0.7)
                .maxTokens(-1)
                .stream(false)
                .build();

        //this.chatId = chatCompletionResponse.getId();
        //ChatMessage assistantMessage = new ChatMessage(
          //      "assistant",
          //      chatCompletionResponse.getChoices().getFirst().getMessage().getContent()
        //);
        //this.messages.add(assistantMessage);
        return llmClient.sendMessage(request);
    }

    @POST
    @Path("/string")
    public Response stream(@Schema(example = "Hola, quien eres?") String message) {
        ChatMessage systemMessage =
                new ChatMessage(
                        "system",
                        "Answer as you are my aunt called Lorena. She lives in Switzerland and she has 3 kids, Giuliana, Michaela, Sven. Her Hubsband is called Walter Hersche. Her Mom is Victoria and she is going to fly tomorrow to Ecuador. She is under stress."
                );
        ChatMessage userMessage = new ChatMessage(
                "user",
                message
        );

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("qwen2.5-coder-7b-instruct")
                .messages(List.of(systemMessage, userMessage))
                .temperature(0.7)
                .maxTokens(-1)
                .stream(false)
                .build();

        //String content = llmClient.sendMessage(request).getChoices().getFirst().getMessage().getContent();
        return Response.ok(llmClient.sendMessage(request)).build();
    }

    @GET
    @Path("/models")
    public Object getModels() {
        return llmClient.getLLMModels();
    }
}
