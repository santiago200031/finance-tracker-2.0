package org.finance.external;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.finance.models.FinanceChartDO;

import java.util.List;

@RegisterRestClient(baseUri = "https://assets.msn.com/service/Finance/Charts")
public interface MicrosoftChartRestClient {

    @GET
    List<FinanceChartDO> getFinanceChart(@QueryParam("apikey") String apikey,
                                   @QueryParam("activityId") String activityId,
                                   @QueryParam("ocid") String ocid,
                                   @QueryParam("cm") String cm,
                                   @QueryParam("ids") String ids,
                                   @QueryParam("wrapodata") boolean wrapodata);

    @GET
    List<List<FinanceChartDO>> getFinanceCharts(@QueryParam("apikey") String apikey,
                                          @QueryParam("activityId") String activityId,
                                          @QueryParam("ocid") String ocid,
                                          @QueryParam("cm") String cm,
                                          @QueryParam("ids") String ids,
                                          @QueryParam("wrapodata") boolean wrapodata);
}