package org.finance.services;

import org.finance.models.finance.Finance;
import org.finance.models.finance.FinanceDO;

public interface FinanceService {
    FinanceDO getCurrentFinance();

    FinanceDO getPreviousFinance();

    float getDifferencePrice(FinanceDO currentFinance, FinanceDO previousFinance);

    void updatePreviousFinance(Finance currentFinance);
}