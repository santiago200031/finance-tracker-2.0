package org.finance.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.finance.models.finance.Finance;

import java.util.Optional;

@ApplicationScoped
@Table(name = "deka_finance")
public class DekaFinanceRepository implements PanacheRepository<Finance> {

    @Transactional
    public Optional<Finance> findLastValue() {
        return find(
                "order by id desc"
        ).firstResultOptional();
    }

}