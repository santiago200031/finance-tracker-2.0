package org.finance.apis;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.finance.models.finance.FinanceDO;
import org.finance.services.BTCFinanceService;

@Produces(MediaType.APPLICATION_JSON)
@Path("/finances/btc")
public class BTCFinanceAPI {

    @Inject
    BTCFinanceService btcFinanceService;

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
    @Path("/data-base")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDekaFinanceDataBase() {
        FinanceDO financeOffline = btcFinanceService.getLastFinanceDB();

        if (financeOffline == null) {
            return Response.noContent().build();
        }

        return Response.ok(financeOffline).build();
    }

}