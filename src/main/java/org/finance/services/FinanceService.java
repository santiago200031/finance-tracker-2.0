package org.finance.services;

import org.finance.models.finance.BaseFinance;
import org.finance.models.finance.FinanceDO;

import java.util.List;

public interface FinanceService {
    FinanceDO getCurrentFinanceOnline();

    List<FinanceDO> getAllFinanceDB();

    FinanceDO getLastFinanceDB();

    FinanceDO getPreviousFinanceCSV();

    FinanceDO getPreviousFinanceDB();

    float getDifferencePrice(FinanceDO currentFinance, FinanceDO previousFinance);

    void updatePreviousFinanceCSV(BaseFinance currentFinance);

    void updatePreviousFinanceDB(BaseFinance currentFinance);

    void persist(BaseFinance currentFinanceEntity);

}