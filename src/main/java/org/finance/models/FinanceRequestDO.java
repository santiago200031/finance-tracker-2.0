package org.finance.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FinanceRequestDO {

    @JsonProperty("apikey")
    String apikey = "0QfOX3Vn51YCzitbLaRkTTBadtWpgTN8NZLW0C1SEM";

    @JsonProperty("activityId")
    public String activityId;

    @JsonProperty("ocid")
    String ocid = "finance-utils-peregrine";

    @JsonProperty("cm")
    String cm = "de-de";

    @JsonProperty("ids")
    public String ids;

    @JsonProperty("wrapodata")
    boolean wrapodata = false;

    public Map<String, Object> toQueryParams() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("apikey", apikey);
        queryParams.put("activityId", activityId);
        queryParams.put("ocid", ocid);
        queryParams.put("cm", cm);
        queryParams.put("ids", ids);
        queryParams.put("wrapodata", wrapodata);
        return queryParams;
    }

}