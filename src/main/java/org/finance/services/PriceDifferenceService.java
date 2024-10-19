package org.finance.services;

import org.finance.models.finance.FinanceDO;

public abstract class PriceDifferenceService {
    public abstract float getDifferencePrice(FinanceDO currentFinance, FinanceDO previousFinance);
}