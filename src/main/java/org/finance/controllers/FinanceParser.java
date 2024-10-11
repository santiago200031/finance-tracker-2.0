package org.finance.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import org.finance.models.Finance;
import org.finance.models.FinanceDO;

@ApplicationScoped
public class FinanceParser {

    public FinanceDO toFinanceDO(Finance finance) {
        return FinanceDO.builder()
                .id(finance.getId())
                .displayName(finance.getDisplayName())
                .timeLastUpdated(finance.getTimeLastUpdated())
                .price(finance.getPrice())
                .priceChange(finance.getPriceChange())
                .build();
    }

    public Finance toFinance(FinanceDO financeDO) {
        return Finance.builder()
                .id(financeDO.getId())
                .displayName(financeDO.getDisplayName())
                .timeLastUpdated(financeDO.getTimeLastUpdated())
                .price(financeDO.getPrice())
                .priceChange(financeDO.getPriceChange())
                .build();
    }
}
