package org.finance.external;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.finance.external.models.requests.ChatCompletionRequest;
import org.finance.external.models.responses.ChatCompletionResponse;


@RegisterRestClient(baseUri = "http://localhost:1234/v1", configKey = "timeout: 40000")
public interface CustomLLMModelRestClient {

    @GET
    @Path("models")
    Object getLLMModels();

    @POST
    @Path("/chat/completions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ChatCompletionResponse sendMessage(ChatCompletionRequest request);

}