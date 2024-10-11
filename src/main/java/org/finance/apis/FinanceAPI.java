package org.finance.apis;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.finance.models.FinanceDO;
import org.finance.models.FinanceOffline;
import org.finance.services.FinanceService;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/finances")
public class FinanceAPI {

    @Inject
    FinanceService financeService;

    @GET
    @Path("/deka")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFinanceDekaGlobalChampions() {
        FinanceDO finance = financeService.getDekaGlobalChampions();
        if (finance == null) {
            return Response.noContent().build();
        }

        return Response.ok(finance).build();
    }

    @GET
    @Path("/btc")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFinanceBitcoin() {
        FinanceDO finance = financeService.getBTC();
        if (finance == null) {
            return Response.noContent().build();
        }

        return Response.ok(finance).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFinances() {
        List<List<FinanceDO>> finances = financeService.getFinances();

        if (finances == null) {
            return Response.noContent().build();
        }
        return Response.ok(finances).build();
    }

    @GET
    @Path("/deka-offline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDekaFinanceLocalData() {
        FinanceOffline financeOffline = financeService.getDekaLocalFinanceAsJson();

        if (financeOffline == null) {
            return Response.noContent().build();
        }

        return Response.ok(financeOffline).build();
    }
}