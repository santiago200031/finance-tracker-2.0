package org.finance.models.finance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "btc_finance", schema = "finance")
public class BTCFinance extends BaseFinance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "btc_finance_seq_gen")
    @SequenceGenerator(name = "btc_finance_seq_gen", sequenceName = "finance.btc_finance_seq")
    private Long id;

}