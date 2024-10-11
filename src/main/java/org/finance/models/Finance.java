package org.finance.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
@Setter
@Builder
@Data
@ToString
public class Finance {

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
    @JsonProperty("differencePrice")
    private Float differencePrice;
    @JsonProperty("localDateChange")
    private String localDateChange;

}