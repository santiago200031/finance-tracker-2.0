package org.finance.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@ToString
public class FinanceDO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("price")
    private float price;
    @JsonProperty("priceChange")
    private float priceChange;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("timeLastUpdated")
    private String timeLastUpdated;

}