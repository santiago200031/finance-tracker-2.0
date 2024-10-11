package org.finance.apis;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.finance.models.FinanceChartDO;
import org.finance.services.FinanceChartService;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/financeChart")
public class FinanceChartAPI {

    @Inject
    FinanceChartService financeChartService;

    @GET
    @Path("/deka")
    public Response getFinanceDekaGlobalChampions() {
        FinanceChartDO financeChartDO = financeChartService.getDekaGlobalChampions();
        if (financeChartDO == null) {
            return Response.noContent().build();
        }
        return Response.ok(financeChartDO).build();
    }

    @GET
    @Path("/finances")
    public Response getFinances() {
        List<FinanceChartDO> finance = financeChartService.getFinanceCharts();
        if (finance == null) {
            return Response.noContent().build();
        }
        return Response.ok(finance).build();
    }
}