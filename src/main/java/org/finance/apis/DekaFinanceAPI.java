package org.finance.apis;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.finance.models.finance.FinanceDO;
import org.finance.models.finance.FinanceOffline;
import org.finance.services.DekaFinanceService;

@Produces(MediaType.APPLICATION_JSON)
@Path("/finances/deka")
public class DekaFinanceAPI {

    @Inject
    DekaFinanceService dekaFinanceService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFinanceDekaGlobalChampions() {
        FinanceDO finance = dekaFinanceService.getCurrentFinanceOnline();
        if (finance == null) {
            return Response.noContent().build();
        }

        return Response.ok(finance).build();
    }

    @GET
    @Path("/cv")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDekaFinanceCSV() {
        FinanceOffline financeOffline = dekaFinanceService.getLocalFinance();

        if (financeOffline == null) {
            return Response.noContent().build();
        }

        return Response.ok(financeOffline).build();
    }
    @GET
    @Path("/data-base")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDekaFinanceDataBase() {
        FinanceDO financeOffline = dekaFinanceService.getLastFinanceDB();

        if (financeOffline == null) {
            return Response.noContent().build();
        }

        return Response.ok(financeOffline).build();
    }
}