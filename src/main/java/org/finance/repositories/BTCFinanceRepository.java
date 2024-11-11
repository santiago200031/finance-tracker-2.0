package org.finance.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.finance.models.finance.BTCFinance;

import java.util.Optional;

@ApplicationScoped
public class BTCFinanceRepository implements PanacheRepository<BTCFinance> {

    @Transactional
    public Optional<BTCFinance> findLastValue() {
        return find(
                "order by id desc"
        ).firstResultOptional();
    }

}