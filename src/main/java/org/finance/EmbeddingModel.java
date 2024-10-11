package org.finance;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.huggingface.client.EmbeddingRequest;
import dev.langchain4j.model.output.Response;
import io.quarkus.arc.properties.IfBuildProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import org.finance.models.Finance;

import java.util.List;

@ApplicationScoped
@IfBuildProperty(name = "quarkus.langchain4j.embedding-model.provider", stringValue = "custom")
public class EmbeddingModel implements dev.langchain4j.model.embedding.EmbeddingModel {
    private static final String MODEL_URL = "http://localhost:1234/v1/";


    public Finance embed(EmbeddingRequest request) {
        var client = ClientBuilder.newClient();
        return client.target(MODEL_URL)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(request), Finance.class);

    }

    @Override
    public Response<Embedding> embed(String text) {
        return dev.langchain4j.model.embedding.EmbeddingModel.super.embed(text);
    }

    @Override
    public Response<Embedding> embed(TextSegment textSegment) {
        return dev.langchain4j.model.embedding.EmbeddingModel.super.embed(textSegment);
    }

    @Override
    public Response<List<Embedding>> embedAll(List<TextSegment> list) {
        return null;
    }

    @Override
    public int dimension() {
        return dev.langchain4j.model.embedding.EmbeddingModel.super.dimension();
    }
}
