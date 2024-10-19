package org.finance.scheduledTasks;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.finance.controllers.FinanceParser;
import org.finance.models.finance.Finance;
import org.finance.models.finance.FinanceDO;
import org.finance.models.finance.SupportedFinances;
import org.finance.services.FinanceService;
import org.finance.services.FinanceServiceFactory;
import org.finance.services.UserService;
import org.finance.utils.FinanceCSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

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
    UserService userService;

    @Inject
    FinanceCSVWriter financeCSVWriter;

    @Inject
    FinanceParser financeParser;

    private boolean isFirstStartDeka = true, firstStartBTC = true;

    @Scheduled(every = "60s", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
    public void saveInDekaFileIfPriceHasChanged() {
        saveInFileIfPriceHasChanged(SupportedFinances.DEKA, dekaCsvFile, this.isFirstStartDeka);
        this.isFirstStartDeka = false;
    }

    @Scheduled(every = "17s", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
    public void saveInBTCFileIfPriceHasChanged() {
        saveInFileIfPriceHasChanged(SupportedFinances.BTC, btcCsvFile, this.firstStartBTC);
        this.firstStartBTC = false;
    }

    private void saveInFileIfPriceHasChanged(SupportedFinances financeType, String path, boolean isFirstStart) {
        userService.getActivityId();
        FinanceService financeService = financeServiceFactory.getFinanceService(financeType);
        FinanceDO currentFinance = financeService.getCurrentFinance();
        FinanceDO previousFinance = financeService.getPreviousFinance();
        float differencePrice = financeService.getDifferencePrice(currentFinance, previousFinance);
        Finance currentFinanceEntity = financeParser.toFinance(currentFinance);

        if (isFirstStart) {
            String methodName = currentFinance.getDisplayName().trim();
            LOGGER.debug("Task save{}InFileIfPriceHasChanged() started...", methodName);
            if (previousFinance == null) {
                handleFirstExecutionWithNoDataInFile(currentFinanceEntity, path);
            }
            return;
        }

        if (differencePrice != 0f) {
            financeService.updatePreviousFinance(currentFinanceEntity);
        }

        if (checkIfDiffIsToSaveToType(path, differencePrice)) {
            LOGGER.debug("Saving in {}", currentFinance.getDisplayName().trim());
            handleSaveInFile(currentFinanceEntity, differencePrice, path);
        }
    }

    private void handleSaveInFile(Finance currentFinance, float differencePrice, String path) {
        currentFinance.setDifferencePrice(differencePrice);
        LOGGER.info("Difference for {} was: {} EUR", currentFinance.getDisplayName(), differencePrice);
        financeCSVWriter.appendFinanceCSV(path, currentFinance);
    }

    private void handleFirstExecutionWithNoDataInFile(Finance finance, String path) {
        LOGGER.info("Inserting first data of {}...", finance.getDisplayName());
        finance.setPriceChange(finance.getPrice());
        finance.setDifferencePrice(finance.getPrice());
        finance.setLocalDateChange(String.valueOf(Instant.now().toEpochMilli()));
        financeCSVWriter.appendFinanceCSV(path, finance);
        FinanceService financeService = financeServiceFactory.getFinanceService(path.equals(dekaCsvFile) ? SupportedFinances.DEKA : SupportedFinances.BTC);
        financeService.updatePreviousFinance(finance);
    }

    private boolean checkIfDiffIsToSaveToType(String path, float differencePrice) {
        if (path.equals(dekaCsvFile)) {
            return differencePrice < -0.2f || differencePrice > 0.2f;
        }
        if (path.equals(btcCsvFile)) {
            return differencePrice < -2f || differencePrice > 2f;
        }
        return false;
    }
}