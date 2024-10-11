package org.finance.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinanceOffline {

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("prices")
    private List<Float> prices;

    @JsonProperty("differencePrices")
    private List<Float> differencePrices;

    @JsonProperty("priceChanges")
    private List<String> priceChanges;

    @JsonProperty("timesLastUpdated")
    private List<String> timesLastUpdated;

    @JsonProperty("localDateChanges")
    private List<String> localDateChanges;
}