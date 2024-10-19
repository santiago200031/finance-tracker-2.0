package org.finance.external.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ChatMessage {

    @JsonProperty("role")
    private String role;

    @JsonProperty("content")
    private String content;
}