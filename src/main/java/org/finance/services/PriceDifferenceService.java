package org.finance.services;

import org.finance.models.Finance;
import org.finance.models.FinanceDO;

public abstract class PriceDifferenceService {
    public abstract float getDifferencePrice(FinanceDO currentFinance, FinanceDO previousFinance);
}