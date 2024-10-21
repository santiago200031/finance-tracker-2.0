package org.finance.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinanceChartDO {

    @JsonProperty("series")
    private Series series;

    @JsonProperty("pricePreviousClose")
    private double pricePreviousClose;

    @JsonProperty("priceChange")
    private double priceChange;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("timeLastUpdated")
    private String timeLastUpdated;

}