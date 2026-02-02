package org.finance.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.finance.external.MicrosoftChartRestClient;
import org.finance.models.ChartRequestDO;
import org.finance.models.FinanceChartDO;
import org.finance.utils.FinanceIds;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class FinanceChartService {

    @Inject
    @RestClient
    MicrosoftChartRestClient microsoftChartRestClient;

    public FinanceChartDO getDekaGlobalChampions() {
        UUID activityId = UUID.randomUUID();
        String activityName = activityId.toString();

        ChartRequestDO chartRequestDO = new ChartRequestDO();
        chartRequestDO.activityId = activityName;
        Map<String, Object> queryParams = chartRequestDO.toQueryParams();

        List<FinanceChartDO> financeChart = microsoftChartRestClient.getFinanceChart(
                (String) queryParams.get("apikey"),
                (String) queryParams.get("activityId"),
                (String) queryParams.get("ocid"),
                (String) queryParams.get("cm"),
                FinanceIds.DEKA,
                (Boolean) queryParams.get("wrapodata")
        );
        return financeChart.get(0);
    }


    public List<FinanceChartDO> getFinanceCharts() {
        UUID activityId = UUID.randomUUID();
        String activityName = activityId.toString();

        ChartRequestDO chartRequestDO = new ChartRequestDO();
        chartRequestDO.activityId = activityName;
        Map<String, Object> queryParams = chartRequestDO.toQueryParams();

        List<List<FinanceChartDO>> financeCharts = microsoftChartRestClient.getFinanceCharts(
                (String) queryParams.get("apikey"),
                (String) queryParams.get("activityId"),
                (String) queryParams.get("ocid"),
                (String) queryParams.get("cm"),
                FinanceIds.ALL_FINANCES,
                (Boolean) queryParams.get("wrapodata")
        );
        return financeCharts.get(0);
    }
}
