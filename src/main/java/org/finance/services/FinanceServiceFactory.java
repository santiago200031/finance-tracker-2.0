package org.finance.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.finance.models.finance.SupportedFinances;

@ApplicationScoped
public class FinanceServiceFactory {

    @Inject
    DekaFinanceService dekaFinanceService;

    @Inject
    BTCFinanceService btcFinanceService;

    public FinanceService getFinanceService(SupportedFinances financeType) {
        switch (financeType) {
            case DEKA:
                return dekaFinanceService;
            case BTC:
                return btcFinanceService;
            default:
                throw new IllegalArgumentException("Unsupported finance type: " + financeType);
        }
    }
}