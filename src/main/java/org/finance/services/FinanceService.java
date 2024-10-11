package org.finance.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.finance.controllers.FinanceController;
import org.finance.controllers.FinanceParser;
import org.finance.models.Finance;
import org.finance.models.FinanceDO;
import org.finance.models.FinanceOffline;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class FinanceService {

    @Inject
    FinanceController financeController;

    @Inject
    UserService userService;

    @Inject
    FinanceParser financeParser;

    private FinanceDO previousFinanceDeka;

    private FinanceDO previousFinanceBTC;

    public FinanceDO getDekaGlobalChampions() {
        UUID activityId = userService.getActivityId();
        return financeController.getDekaGlobalChampions(activityId);
    }

    public FinanceDO getBTC() {
        UUID activityId = userService.getActivityId();
        return financeController.getBTC(activityId);
    }

    public FinanceDO getLastDekaFinance() {
        Finance lastDekaFinance = financeController.getLastDekaFinance();
        return financeParser.toFinanceDO(lastDekaFinance);
    }

    public FinanceDO getPreviousFinanceDeka() {
        if (this.previousFinanceDeka == null) {
            FinanceDO lastDekaFinance = this.getLastDekaFinance();
            this.previousFinanceDeka = lastDekaFinance;
            return lastDekaFinance;
        }
        return this.previousFinanceDeka;
    }

    public FinanceDO getPreviousFinanceBTC() {
        if (this.previousFinanceBTC == null) {
            FinanceDO lastBTCFinance = this.getLastBTCFinance();
            this.previousFinanceBTC = lastBTCFinance;
            return lastBTCFinance;
        }
        return this.previousFinanceBTC;
    }

    public FinanceDO getLastBTCFinance() {
        Finance lastBTCFinance = financeController.getLastBTCFinance();
        return financeParser.toFinanceDO(lastBTCFinance);
    }

    public FinanceOffline getDekaLocalFinanceAsJson() {
        return financeController.getDekaGlobalChampionsLocalData();
    }

    public Finance updatePreviousFinanceDeka(Finance currentFinance) {
        this.previousFinanceDeka = financeParser.toFinanceDO(currentFinance);
        return financeParser.toFinance(previousFinanceDeka);
    }

    public Finance updatePreviousFinanceBTC(Finance currentFinance) {
        this.previousFinanceBTC = financeParser.toFinanceDO(currentFinance);
        return financeParser.toFinance(previousFinanceBTC);
    }

    public List<List<FinanceDO>> getFinances() {
        UUID activityId = userService.getActivityId();
        return financeController.getFinances(activityId);
    }

}