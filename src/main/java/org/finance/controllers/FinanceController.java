package org.finance.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.finance.models.Finance;
import org.finance.models.FinanceDO;
import org.finance.models.FinanceOffline;
import org.finance.models.FinanceRequestDO;
import org.finance.utils.FinanceCSVReader;
import org.finance.utils.FinanceIds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
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

    public FinanceDO getDekaGlobalChampions(UUID activityId) {
        FinanceRequestDO financeRequestDO = new FinanceRequestDO();
        financeRequestDO.activityId = activityId.toString();

        Map<String, Object> queryParams = financeRequestDO.toQueryParams();
        List<FinanceDO> finance = microsoftFinanceRestClient.getFinance(
                (String) queryParams.get("apikey"),
                (String) queryParams.get("activityId"),
                (String) queryParams.get("ocid"),
                (String) queryParams.get("cm"),
                FinanceIds.DEKA,
                (Boolean) queryParams.get("wrapodata")
        );
        return finance.getFirst();
    }

    public FinanceDO getBTC(UUID activityId) {
        FinanceRequestDO financeRequestDO = new FinanceRequestDO();
        financeRequestDO.activityId = activityId.toString();

        Map<String, Object> queryParams = financeRequestDO.toQueryParams();
        List<FinanceDO> finance = microsoftFinanceRestClient.getFinance(
                (String) queryParams.get("apikey"),
                (String) queryParams.get("activityId"),
                (String) queryParams.get("ocid"),
                (String) queryParams.get("cm"),
                FinanceIds.BTC,
                (Boolean) queryParams.get("wrapodata")
        );
        return finance.getFirst();
    }

    public Finance getLastDekaFinance() {
        return financeCSVReader
                .readLastFinanceCSV(dekaCsvFile);
    }

    public Finance getLastBTCFinance() {
        return financeCSVReader
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