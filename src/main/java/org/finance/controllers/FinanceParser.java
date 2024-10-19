package org.finance.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import org.finance.models.finance.Finance;
import org.finance.models.finance.FinanceDO;

@ApplicationScoped
public class FinanceParser {

    public FinanceDO toFinanceDO(Finance finance) {
        return FinanceDO.builder()
                .displayName(finance.getDisplayName())
                .timeLastUpdated(finance.getTimeLastUpdated())
                .price(finance.getPrice())
                .priceChange(finance.getPriceChange())
                .build();
    }

    public Finance toFinance(FinanceDO financeDO) {
        Finance finance = new Finance();
        finance.setDisplayName(financeDO.getDisplayName());
        finance.setTimeLastUpdated(financeDO.getTimeLastUpdated());
        finance.setPrice(financeDO.getPrice());
        finance.setPriceChange(financeDO.getPriceChange());

        return finance;
    }
}
