package org.finance.models.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Finance extends PanacheEntity {

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