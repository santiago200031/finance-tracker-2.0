package org.finance.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Table;
import org.finance.models.finance.Finance;

@ApplicationScoped
@Table(name = "deka_finance")
public class DekaFinanceRepository implements PanacheRepository<Finance> {
}