package org.finance.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.finance.models.finance.DekaFinance;

import java.util.Optional;

@ApplicationScoped
public class DekaFinanceRepository implements PanacheRepository<DekaFinance> {

    @Transactional
    public Optional<DekaFinance> findLastValue() {
        return find(
                "order by id desc"
        ).firstResultOptional();
    }

}