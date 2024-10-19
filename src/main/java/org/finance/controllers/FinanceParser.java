package org.finance.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import org.finance.models.finance.*;

@ApplicationScoped
public class FinanceParser {

    public FinanceDO toFinanceDO(BaseFinance finance) {
        return FinanceDO.builder()
                .displayName(finance.getDisplayName())
                .timeLastUpdated(finance.getTimeLastUpdated())
                .price(finance.getPrice())
                .priceChange(finance.getPriceChange())
                .build();
    }

    public BaseFinance toFinance(FinanceDO financeDO, SupportedFinances financeType) {
        BaseFinance finance;
        switch (financeType) {
            case DEKA -> finance = new DekaFinance();
            case BTC -> finance = new BTCFinance();

            default -> throw new IllegalArgumentException("Unsupported finance type: " + financeType);
        }
        finance.setDisplayName(financeDO.getDisplayName());
        finance.setTimeLastUpdated(financeDO.getTimeLastUpdated());
        finance.setPrice(financeDO.getPrice());
        finance.setPriceChange(financeDO.getPriceChange());

        return finance;
    }
}