package org.finance.services;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.finance.controllers.FinanceController;
import org.finance.controllers.FinanceParser;
import org.finance.models.finance.Finance;
import org.finance.models.finance.FinanceDO;
import org.finance.models.finance.FinanceOffline;
import org.finance.repositories.DekaFinanceRepository;
import org.finance.services.priceDifferences.PriceDifferenceDekaService;

import java.util.UUID;

@ApplicationScoped
public class DekaFinanceService implements FinanceService {

    @Inject
    FinanceController financeController;

    @Inject
    UserService userService;

    @Inject
    FinanceParser financeParser;

    @Inject
    DekaFinanceRepository dekaRepository;

    @Inject
    PriceDifferenceDekaService priceDifferenceServiceDeka;

    private FinanceDO previousFinance;

    @Override
    //fixme(when the name of the tool is really set, it should work
    // @Tool(name = "deka_get_current_finance", value = {"Get the current data value for Deka Global Champions"})
    public FinanceDO getCurrentFinance() {
        return getCurrentFinanceDeka();
    }

    @Tool(name = "get_current_finance_deka", value = {"Get the current data value for Deka Global Champions"})
    private FinanceDO getCurrentFinanceDeka() {
        UUID activityId = userService.getActivityId();
        return financeController.getDekaGlobalChampions(activityId);
    }

    @Override
    public FinanceDO getPreviousFinance() {
        if (this.previousFinance == null) {
            FinanceDO lastDekaFinance = this.getLastDekaFinance();
            this.previousFinance = lastDekaFinance;
            return lastDekaFinance;
        }
        return this.previousFinance;
    }

    @Override
    public float getDifferencePrice(FinanceDO currentFinance, FinanceDO previousFinance) {
        return priceDifferenceServiceDeka.getDifferencePrice(currentFinance, previousFinance);
    }

    @Override
    public void updatePreviousFinance(Finance currentFinance) {
        this.previousFinance = financeParser.toFinanceDO(currentFinance);
    }

    public FinanceDO getLastDekaFinance() {
        Finance lastDekaFinance = financeController.getLastDekaFinance();
        return financeParser.toFinanceDO(lastDekaFinance);
    }

    public FinanceOffline getLocalFinance() {
        return financeController.getDekaGlobalChampionsLocalData();
    }

}