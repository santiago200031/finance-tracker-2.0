package org.finance.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ProcessingException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.finance.external.MicrosoftFinanceRestClient;
import org.finance.models.finance.*;
import org.finance.models.FinanceRequestDO;
import org.finance.repositories.BTCFinanceRepository;
import org.finance.repositories.DekaFinanceRepository;
import org.finance.utils.FinanceCSVReader;
import org.finance.utils.FinanceIds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class FinanceController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @ConfigProperty(name = "resources.deka.csv-file")
    String dekaCsvFile;

    @ConfigProperty(name = "resources.btc.csv-file")
    String btcCsvFile;

    @Inject
    FinanceCSVReader financeCSVReader;

    @Inject
    @RestClient
    MicrosoftFinanceRestClient microsoftFinanceRestClient;
    @Inject
    DekaFinanceRepository dekaFinanceRepository;
    @Inject
    BTCFinanceRepository btcFinanceRepository;
    @Inject
    FinanceParser financeParser;

    public FinanceDO getFinance(UUID activityId, String financeId) {
        FinanceRequestDO financeRequestDO = new FinanceRequestDO();
        financeRequestDO.activityId = activityId.toString();

        Map<String, Object> queryParams = financeRequestDO.toQueryParams();
        List<FinanceDO> finance;
        try {
            finance = microsoftFinanceRestClient.getFinance(
                    (String) queryParams.get("apikey"),
                    (String) queryParams.get("activityId"),
                    (String) queryParams.get("ocid"),
                    (String) queryParams.get("cm"),
                    financeId,
                    (Boolean) queryParams.get("wrapodata")
            );
            if (!finance.isEmpty()) {
                return finance.getFirst();
            }
        } catch (ProcessingException e) {
            LOGGER.error("Error fetching finance data", e);
            Optional<? extends BaseFinance> lastValue = getLastValue(financeId);
            if (lastValue.isPresent()) {
                BaseFinance lastDBFinance = lastValue.get();
                LOGGER.info("No internet connection for {}, returning last value of the DB",
                        lastDBFinance.getDisplayName());
                return financeParser.toFinanceDO(lastDBFinance);
            }
        }
        return null;
    }

    private Optional<? extends BaseFinance> getLastValue(String financeId) {
        switch (financeId) {
            case FinanceIds.DEKA:
                return dekaFinanceRepository.findLastValue();
            case FinanceIds.BTC:
                return btcFinanceRepository.findLastValue();
            default:
                return Optional.empty();
        }
    }

    public FinanceDO getDekaGlobalChampions(UUID activityId) {
        return getFinance(activityId, FinanceIds.DEKA);
    }

    public FinanceDO getBTC(UUID activityId) {
        return getFinance(activityId, FinanceIds.BTC);
    }

    public DekaFinance getLastDekaFinance() {
        return (DekaFinance) financeCSVReader
                .readLastFinanceCSV(dekaCsvFile);
    }

    public BTCFinance getLastBTCFinance() {
        return (BTCFinance) financeCSVReader
                .readLastFinanceCSV(btcCsvFile);
    }

    public FinanceOffline getDekaGlobalChampionsLocalData() {
        return financeCSVReader
                .readFinanceCSV(dekaCsvFile);
    }

    public List<List<FinanceDO>> getFinances(UUID activityId) {
        FinanceRequestDO financeRequestDO = new FinanceRequestDO();
        financeRequestDO.activityId = activityId.toString();

        Map<String, Object> queryParams = financeRequestDO.toQueryParams();
        return microsoftFinanceRestClient.getFinances(
                (String) queryParams.get("apikey"),
                (String) queryParams.get("activityId"),
                (String) queryParams.get("ocid"),
                (String) queryParams.get("cm"),
                FinanceIds.ALL_FINANCES,
                (Boolean) queryParams.get("wrapodata")
        );
    }
}