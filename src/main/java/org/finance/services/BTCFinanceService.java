package org.finance.services;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.finance.controllers.FinanceController;
import org.finance.controllers.FinanceParser;
import org.finance.models.finance.Finance;
import org.finance.models.finance.FinanceDO;
import org.finance.repositories.BtcFinanceRepository;
import org.finance.services.priceDifferences.PriceDifferenceBTCService;

import java.util.UUID;

@ApplicationScoped
public class BTCFinanceService implements FinanceService {

    @Inject
    BtcFinanceRepository btcRepository;

    @Inject
    FinanceParser financeParser;

    @Inject
    PriceDifferenceBTCService priceDifferenceServiceBTC;

    @Inject
    FinanceController financeController;

    @Inject
    UserService userService;

    private FinanceDO previousFinance;

    @Override
    //fixme: @Tool(name = "get_current_finance_btc", value = {"Get the current data value for Bitcoin (BTC)"})
    public FinanceDO getCurrentFinance() {
        return getCurrentFinanceBTC();
    }

    @Tool(name = "get_current_finance_btc", value = {"Get the current data value for Bitcoin (BTC)"})
    private FinanceDO getCurrentFinanceBTC() {
        UUID activityId = userService.getActivityId();
        return financeController.getBTC(activityId);
    }

    @Override
    public FinanceDO getPreviousFinance() {
        if (this.previousFinance == null) {
            FinanceDO lastBTCFinance = this.getLastFinance();
            this.previousFinance = lastBTCFinance;
            return lastBTCFinance;
        }
        return this.previousFinance;
    }

    @Override
    public float getDifferencePrice(FinanceDO currentFinance, FinanceDO previousFinance) {
        return priceDifferenceServiceBTC.getDifferencePrice(currentFinance, previousFinance);
    }

    @Override
    public void updatePreviousFinance(Finance currentFinance) {
        this.previousFinance = financeParser.toFinanceDO(currentFinance);
    }

    private FinanceDO getLastFinance() {
        Finance lastBTCFinance = financeController.getLastBTCFinance();
        return financeParser.toFinanceDO(lastBTCFinance);
    }
}
