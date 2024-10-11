package org.finance.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.finance.models.FinanceDO;

import java.util.List;

@RegisterRestClient(baseUri = "https://assets.msn.com/service/Finance")
public interface MicrosoftFinanceRestClient {

    @GET
    @Path("/Quotes")
    List<List<FinanceDO>> getFinances(
            @QueryParam("apikey") String apikey,
            @QueryParam("activityId") String activityId,
            @QueryParam("ocid") String ocid,
            @QueryParam("cm") String cm,
            @QueryParam("ids") String ids,
            @QueryParam("wrapodata") boolean wrapodata
    );

    @GET
    @Path("/Quotes")
    List<FinanceDO> getFinance(@QueryParam("apikey") String apikey,
                         @QueryParam("activityId") String activityId,
                         @QueryParam("ocid") String ocid,
                         @QueryParam("cm") String cm,
                         @QueryParam("ids") String ids,
                         @QueryParam("wrapodata") boolean wrapodata);

}