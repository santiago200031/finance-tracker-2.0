package org.finance.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@Data
@ToString
public class Series {

    @JsonProperty("prices")
    private List<Float> prices;

    @JsonProperty("timeStamps")
    private List<String> timestamps;

    @JsonProperty("startTime")
    private String startTime;

    @JsonProperty("endTime")
    private String endTime;

}