package org.finance.controllers;

import org.finance.models.finance.FinanceDO;
import org.slf4j.Logger;

public abstract class PriceDifferenceController {

    protected abstract Logger getLogger();

    protected abstract int getGauge();

    protected abstract void setGauge(int gauge);

    public float getDifferencePrice(FinanceDO currentFinance, FinanceDO previousFinance) {
        int gauge = getGauge();

        float currentPrice = currentFinance.getPrice();
        float previousPrice = previousFinance.getPrice();

        float difference = currentPrice - previousPrice;
        if (difference > 0) {
            setGauge(gauge + 1);
        }

        if (difference < 0) {
            setGauge(gauge - 1);
        }

        if (gauge != getGauge()) {
            getLogger().info("{} Gauge: {}", currentFinance.getDisplayName(), getGauge());
        }
        return difference;
    }
}
