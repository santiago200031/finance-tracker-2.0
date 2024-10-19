package org.finance.services.priceDifferences;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.finance.controllers.priceDifferences.PriceDifferenceBTCController;
import org.finance.models.finance.FinanceDO;
import org.finance.services.PriceDifferenceService;

@ApplicationScoped
public class PriceDifferenceBTCService extends PriceDifferenceService {
    @Inject
    PriceDifferenceBTCController priceDifferenceController;

    public float getDifferencePrice(FinanceDO currentFinance, FinanceDO previousFinance) {
        return priceDifferenceController.getDifferencePrice(currentFinance, previousFinance);
    }
}
