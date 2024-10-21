package org.finance.services;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.finance.controllers.FinanceController;
import org.finance.controllers.FinanceParser;
import org.finance.models.finance.BTCFinance;
import org.finance.models.finance.BaseFinance;
import org.finance.models.finance.FinanceDO;
import org.finance.repositories.BTCFinanceRepository;
import org.finance.services.priceDifferences.PriceDifferenceBTCService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class BTCFinanceService implements FinanceService {

    @Inject
    BTCFinanceRepository btcRepository;

    @Inject
    FinanceParser financeParser;

    @Inject
    PriceDifferenceBTCService priceDifferenceServiceBTC;

    @Inject
    FinanceController financeController;

    @Inject
    UserService userService;

    private FinanceDO previousFinanceCSV;

    private FinanceDO previousFinanceDB;

    @Override
    //fixme: @Tool(name = "get_current_finance_btc", value = {"Get the current data value for Bitcoin (BTC)"})
    public FinanceDO getCurrentFinanceOnline() {
        return getCurrentFinanceBTC();
    }


    @Tool(name = "get_current_finance_online_btc", value = {"Get the current data value for Bitcoin (BTC)"})
    public FinanceDO getCurrentFinanceBTC() {
        UUID activityId = userService.getActivityId();
        return financeController.getBTC(activityId);
    }

    @Override
    public List<FinanceDO> getAllFinanceDB() {
        return getAllFinanceBTCDB();
    }

    @Tool(
            name = "get_all_finance_db_btc",
            value = {"Get all the data from Bitcoin (BTC) from the Data Base"}
    )
    public List<FinanceDO> getAllFinanceBTCDB() {
        return btcRepository.listAll()
                .stream()
                .map(financeParser::toFinanceDO)
                .toList();
    }

    @Override
    public FinanceDO getPreviousFinanceCSV() {
        if (this.previousFinanceCSV == null) {
            FinanceDO lastBTCFinance = this.getLastFinance();
            this.previousFinanceCSV = lastBTCFinance;
            return lastBTCFinance;
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
        return getLastFinanceDBBTC();
    }

    @Tool(
            name = "get_last_finance_db_btc",
            value = {"Retrieve the last value of the finance Bitcoin (BTC) from the Data Base"}
    )
    public FinanceDO getLastFinanceDBBTC() {
        Optional<BTCFinance> finance = btcRepository.findLastValue();
        return finance.map(financeParser::toFinanceDO)
                .orElse(FinanceDO.builder()
                        .price(0)
                        .priceChange(0)
                        .build());
    }

    @Override
    public float getDifferencePrice(FinanceDO currentFinance, FinanceDO previousFinance) {
        return priceDifferenceServiceBTC.getDifferencePrice(currentFinance, previousFinance);
    }

    @Override
    public void updatePreviousFinanceCSV(BaseFinance currentFinance) {
        this.previousFinanceCSV = financeParser.toFinanceDO(currentFinance);
    }

    @Override
    public void updatePreviousFinanceDB(BaseFinance currentFinance) {
        this.previousFinanceDB = financeParser.toFinanceDO(currentFinance);
    }

    @Transactional
    public void persist(BaseFinance currentFinanceEntity) {
        BTCFinance btcFinance = (BTCFinance) currentFinanceEntity;
        btcRepository.persistAndFlush(btcFinance);
    }

    private FinanceDO getLastFinance() {
        BaseFinance lastBTCFinance = financeController.getLastBTCFinance();
        return financeParser.toFinanceDO(lastBTCFinance);
    }
}
