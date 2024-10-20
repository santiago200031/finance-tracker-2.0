package org.finance.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.finance.models.finance.BTCFinance;
import org.finance.models.finance.BaseFinance;

import java.util.Optional;

@ApplicationScoped
@Table(name = "btc_finance")
public class BTCFinanceRepository implements PanacheRepository<BTCFinance> {

    @Transactional
    public Optional<BTCFinance> findLastValue() {
        return find(
                "order by id desc"
        ).firstResultOptional();
    }

}