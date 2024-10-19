package org.finance.apis;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.finance.models.finance.FinanceDO;
import org.finance.models.finance.FinanceOffline;
import org.finance.services.BTCFinanceService;
import org.finance.services.DekaFinanceService;

@Produces(MediaType.APPLICATION_JSON)
@Path("/finances")
public class FinanceAPI {

    @Inject
    DekaFinanceService dekaFinanceService;

    @Inject
    BTCFinanceService btcFinanceService;

    @GET
    @Path("/deka")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFinanceDekaGlobalChampions() {
        FinanceDO finance = dekaFinanceService.getCurrentFinanceOnline();
        if (finance == null) {
            return Response.noContent().build();
        }

        return Response.ok(finance).build();
    }

    @GET
    @Path("/btc")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFinanceBitcoin() {
        FinanceDO finance = btcFinanceService.getCurrentFinanceOnline();
        if (finance == null) {
            return Response.noContent().build();
        }

        return Response.ok(finance).build();
    }

    @GET
    @Path("/deka-offline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDekaFinanceLocalData() {
        FinanceOffline financeOffline = dekaFinanceService.getLocalFinance();

        if (financeOffline == null) {
            return Response.noContent().build();
        }

        return Response.ok(financeOffline).build();
    }
}