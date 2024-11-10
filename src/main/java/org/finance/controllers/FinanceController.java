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

    public FinanceDO getDekaGlobalChampions(UUID activityId) {
        FinanceRequestDO financeRequestDO = new FinanceRequestDO();
        financeRequestDO.activityId = activityId.toString();

        Map<String, Object> queryParams = financeRequestDO.toQueryParams();
        List<FinanceDO> finance = List.of();
        try {
            finance = microsoftFinanceRestClient.getFinance(
                    (String) queryParams.get("apikey"),
                    (String) queryParams.get("activityId"),
                    (String) queryParams.get("ocid"),
                    (String) queryParams.get("cm"),
                    FinanceIds.DEKA,
                    (Boolean) queryParams.get("wrapodata")
            );
            return finance.getFirst();

        } catch (ProcessingException ignored) {
            Optional<DekaFinance> lastValue = dekaFinanceRepository.findLastValue();
            if (lastValue.isPresent()) {
                DekaFinance lastDBFinance = lastValue.get();
                LOGGER.info("No internet connection for {}, returning last value of the DB",
                        lastDBFinance.getDisplayName());
                return financeParser.toFinanceDO(lastDBFinance);
            }
        }
        return finance.getFirst();
    }

    public FinanceDO getBTC(UUID activityId) {
        FinanceRequestDO financeRequestDO = new FinanceRequestDO();
        financeRequestDO.activityId = activityId.toString();

        Map<String, Object> queryParams = financeRequestDO.toQueryParams();
        List<FinanceDO> finance = List.of();
        try {
            finance = microsoftFinanceRestClient.getFinance(
                    (String) queryParams.get("apikey"),
                    (String) queryParams.get("activityId"),
                    (String) queryParams.get("ocid"),
                    (String) queryParams.get("cm"),
                    FinanceIds.BTC,
                    (Boolean) queryParams.get("wrapodata")
            );
            return finance.getFirst();

        } catch (ProcessingException ignored) {
            Optional<BTCFinance> lastValue = btcFinanceRepository.findLastValue();
            if (lastValue.isPresent()) {
                BTCFinance lastDBFinance = lastValue.get();
                LOGGER.info("No internet connection for {}, returning last value of the DB",
                        lastDBFinance.getDisplayName());
                return financeParser.toFinanceDO(lastValue.get());
            }
        }
        return finance.getFirst();
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