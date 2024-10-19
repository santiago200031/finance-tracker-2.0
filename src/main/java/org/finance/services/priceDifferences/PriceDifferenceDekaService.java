package org.finance.services.priceDifferences;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.finance.controllers.priceDifferences.PriceDifferenceDekaController;
import org.finance.models.finance.FinanceDO;
import org.finance.services.PriceDifferenceService;

@ApplicationScoped
public class PriceDifferenceDekaService extends PriceDifferenceService {

    @Inject
    PriceDifferenceDekaController priceDifferenceController;

    public float getDifferencePrice(FinanceDO currentDeka, FinanceDO previousDeka) {
        return priceDifferenceController.getDifferencePrice(currentDeka, previousDeka);
    }

    public int getGauge() {
        return priceDifferenceController.getGauge();
    }
}