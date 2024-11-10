package org.finance.scheduledTasks;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.finance.controllers.FinanceParser;
import org.finance.models.finance.BaseFinance;
import org.finance.models.finance.FinanceDO;
import org.finance.models.finance.SupportedFinances;
import org.finance.services.FinanceService;
import org.finance.services.FinanceServiceFactory;
import org.finance.utils.FinanceCSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@ApplicationScoped
public class FinanceServicesTasks {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @ConfigProperty(name = "resources.deka.csv-file")
    String dekaCsvFile;

    @ConfigProperty(name = "resources.btc.csv-file")
    String btcCsvFile;

    @Inject
    FinanceServiceFactory financeServiceFactory;

    @Inject
    FinanceCSVWriter financeCSVWriter;

    @Inject
    FinanceParser financeParser;

    private boolean isFirstStartDeka = true, firstStartBTC = true;

    @Scheduled(every = "60s", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
    public void saveInDekaFileIfPriceHasChanged() {
        saveInDBIfPriceHasChanged(SupportedFinances.DEKA, this.isFirstStartDeka);
        this.isFirstStartDeka = false;
    }

    @Scheduled(every = "17s", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
    public void saveInBTCFileIfPriceHasChanged() {
        saveInDBIfPriceHasChanged(SupportedFinances.BTC, this.firstStartBTC);
        this.firstStartBTC = false;
    }

    private void saveInDBIfPriceHasChanged(SupportedFinances financeType, boolean isFirstStart) {
        FinanceService financeService = financeServiceFactory.getFinanceService(financeType);
        FinanceDO currentFinanceOnline = financeService.getCurrentFinanceOnline();
        //FinanceDO previousFinanceCSV = financeService.getPreviousFinanceCSV();
        FinanceDO previousFinanceDB = financeService.getPreviousFinanceDB();
        BaseFinance currentFinanceEntity = financeParser.toFinance(currentFinanceOnline, financeType);

        String displayName = currentFinanceOnline.getDisplayName();
        if (isFirstStart) {
            String methodName = displayName.trim();
            LOGGER.info("Task save{}InFileIfPriceHasChanged() started...", methodName);
            if (previousFinanceDB == null) {
                handleFirstExecutionWithNoDataInDB(financeService, currentFinanceEntity);
                //handleFirstExecutionWithNoDataInFile(currentFinanceEntity, path);
            }
            return;
        }

        /*
        float differencePriceCSV = financeService.getDifferencePrice(currentFinanceOnline, previousFinanceCSV);
        if (differencePriceCSV != 0f) {
            financeService.updatePreviousFinanceCSV(currentFinanceEntity);
        }
         */

        float differencePriceDB = financeService.getDifferencePrice(currentFinanceOnline, previousFinanceDB);
        if (differencePriceDB != 0f) {
            financeService.updatePreviousFinanceDB(currentFinanceEntity);
        }

        /*
        if (checkIfDiffIsToSaveToType(financeType, differencePriceCSV)) {
            LOGGER.debug("Saving in CSV file for {}", displayName.trim());
            LOGGER.info("Difference for {} was: {} EUR", displayName, differencePriceCSV);
            handleSaveInFile(currentFinanceEntity, differencePriceCSV, path);
        }
         */
        if (checkIfDiffIsToSaveToType(financeType, differencePriceDB)) {
            LOGGER.debug("Saving in DB for {}", displayName.trim());
            LOGGER.info("Difference for {} was: {} EUR", displayName, differencePriceDB);
            handleSaveInDB(currentFinanceEntity, differencePriceDB, financeService);
        }
    }

    private void handleSaveInDB(BaseFinance currentFinanceEntity, float differencePrice, FinanceService financeService) {
        currentFinanceEntity.setDifferencePrice(differencePrice);
        financeService.persist(currentFinanceEntity);
    }

    private void handleFirstExecutionWithNoDataInDB(FinanceService financeService, BaseFinance currentFinanceEntity) {
        financeService.persist(currentFinanceEntity);
    }

    private void handleSaveInFile(BaseFinance currentFinance, float differencePrice, String path) {
        currentFinance.setDifferencePrice(differencePrice);
        financeCSVWriter.appendFinanceCSV(path, currentFinance);
    }

    private void handleFirstExecutionWithNoDataInFile(BaseFinance finance, String path) {
        LOGGER.info("Inserting first data of {}...", finance.getDisplayName());
        finance.setPriceChange(finance.getPrice());
        finance.setDifferencePrice(finance.getPrice());
        finance.setLocalDateChange(LocalDate.now());
        financeCSVWriter.appendFinanceCSV(path, finance);
        FinanceService financeService = financeServiceFactory.getFinanceService(path.equals(dekaCsvFile) ? SupportedFinances.DEKA : SupportedFinances.BTC);
        financeService.updatePreviousFinanceCSV(finance);
    }

    private boolean checkIfDiffIsToSaveToType(SupportedFinances financeType, float differencePrice) {
        if (financeType.equals(SupportedFinances.DEKA)) {
            return differencePrice < -0.2f || differencePrice > 0.2f;
        }
        if (financeType.equals(SupportedFinances.BTC)) {
            return differencePrice < -2f || differencePrice > 2f;
        }
        return false;
    }
}