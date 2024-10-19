package org.finance.models.finance;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "btc_finance")
public class BTCFinance extends BaseFinance {
}