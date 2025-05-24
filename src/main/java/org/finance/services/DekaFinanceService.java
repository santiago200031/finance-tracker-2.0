package org.finance.services;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.finance.controllers.FinanceController;
import org.finance.controllers.FinanceParser;
import org.finance.models.finance.BaseFinance;
import org.finance.models.finance.DekaFinance;
import org.finance.models.finance.FinanceDO;
import org.finance.models.finance.FinanceOffline;
import org.finance.repositories.DekaFinanceRepository;
import org.finance.services.priceDifferences.PriceDifferenceDekaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class DekaFinanceService implements FinanceService {

    @Inject
    FinanceController financeController;

    @Inject
    FinanceParser financeParser;

    @Inject
    DekaFinanceRepository dekaRepository;

    @Inject
    PriceDifferenceDekaService priceDifferenceServiceDeka;

    private FinanceDO previousFinanceCSV;

    private FinanceDO previousFinanceDB;

    @Override
    //fixme(when the name of the tool is really set, it should work
    // @Tool(name = "deka_get_current_finance", value = {"Get the current data value for Deka Global Champions"})
    public FinanceDO getCurrentFinanceOnline() {
        return getCurrentFinanceDekaOnline();
    }

    @Tool(
            name = "get_current_finance_online_deka",
            value = {"Get the current data value for Deka Global Champions online"}
    )
    public FinanceDO getCurrentFinanceDekaOnline() {
        UUID activityId = UUID.randomUUID();
        return financeController.getDekaGlobalChampions(activityId);
    }

    @Override
    public List<FinanceDO> getAllFinanceDB() {
        return getAllFinanceDekaDB();
    }

    @Tool(
            name = "get_all_finance_db_deka",
            value = {"Get all the data from Deka Global Champion from the Data Base"}
    )
    public List<FinanceDO> getAllFinanceDekaDB() {
        return dekaRepository.listAll()
                .stream()
                .map(financeParser::toFinanceDO)
                .toList();
    }


    @Override
    public FinanceDO getPreviousFinanceCSV() {
        if (this.previousFinanceCSV == null) {
            FinanceDO lastDekaFinance = this.getLastDekaFinance();
            this.previousFinanceCSV = lastDekaFinance;
            return lastDekaFinance;
        }
        return this.previousFinanceCSV;
    }

    @Override
    public FinanceDO getPreviousFinanceDB() {
        if (this.previousFinanceDB == null) {
            FinanceDO lastFinance = this.getLastFinanceDB();
            this.previousFinanceDB = lastFinance;
            return lastFinance;
        }
        return this.previousFinanceDB;
    }

    @Override
    public FinanceDO getLastFinanceDB() {
        return getLastFinanceDBDeka();
    }

    @Tool(
            name = "get_last_finance_db_deka",
            value = {"Retrieve the last value of the finance Deka Global Champion from the Data Base"}
    )
    public FinanceDO getLastFinanceDBDeka() {
        Optional<DekaFinance> finance = dekaRepository.findLastValue();

        if (finance.isEmpty()) {
            return FinanceDO.builder()
                    .price(0)
                    .priceChange(0)
                    .build();
        }

        DekaFinance presentFinance = finance.get();
        return financeParser.toFinanceDO(presentFinance);
    }

    @Override
    public float getDifferencePrice(FinanceDO currentFinance, FinanceDO previousFinance) {
        return priceDifferenceServiceDeka.getDifferencePrice(currentFinance, previousFinance);
    }

    @Override
    public void updatePreviousFinanceCSV(BaseFinance currentFinance) {
        this.previousFinanceCSV = financeParser.toFinanceDO(currentFinance);
    }

    @Override
    public void updatePreviousFinanceDB(BaseFinance currentFinance) {
        this.previousFinanceDB = financeParser.toFinanceDO(currentFinance);
    }

    @Override
    @Transactional
    public void persist(BaseFinance currentFinanceEntity) {
        DekaFinance dekaFinance = (DekaFinance) currentFinanceEntity;
        dekaRepository.persistAndFlush(dekaFinance);
    }

    public FinanceDO getLastDekaFinance() {
        DekaFinance lastDekaFinance = financeController.getLastDekaFinance();
        if(lastDekaFinance == null){
            return FinanceDO.builder().build();
        }

        return financeParser.toFinanceDO(lastDekaFinance);
    }

    public FinanceOffline getLocalFinance() {
        return financeController.getDekaGlobalChampionsLocalData();
    }

}