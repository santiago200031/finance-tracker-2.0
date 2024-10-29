package org.finance.models.finance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "deka_finance", schema = "finance")
public class DekaFinance extends BaseFinance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deka_finance_seq_gen")
    @SequenceGenerator(name = "deka_finance_seq_gen", sequenceName = "finance.deka_finance_seq", allocationSize = 1)
    private Long id;

}