package org.finance.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class User {

    @JsonProperty("api_key")
    private String apiKey;

    private UUID activityId;
}