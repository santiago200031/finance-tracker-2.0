package org.finance.models.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseFinance extends PanacheEntityBase {

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
    private LocalDate localDateChange;

}