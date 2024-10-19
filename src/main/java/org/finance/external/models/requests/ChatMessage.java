package org.finance.external.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ChatMessage {

    @JsonProperty("role")
    private String role;

    @JsonProperty("content")
    private String content;
}