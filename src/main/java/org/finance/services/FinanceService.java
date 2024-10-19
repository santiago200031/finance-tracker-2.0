package org.finance.services;

import org.finance.models.finance.BaseFinance;
import org.finance.models.finance.FinanceDO;

public interface FinanceService {
    FinanceDO getCurrentFinanceOnline();

    FinanceDO getLastFinanceDB();

    FinanceDO getPreviousFinanceCSV();

    FinanceDO getPreviousFinanceDB();

    float getDifferencePrice(FinanceDO currentFinance, FinanceDO previousFinance);

    void updatePreviousFinance(BaseFinance currentFinance);

    void persist(BaseFinance currentFinanceEntity);
}