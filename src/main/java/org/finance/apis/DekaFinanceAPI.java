package org.finance.apis;

import hex.genmodel.MojoModel;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.exception.PredictException;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.finance.models.finance.FinanceDO;
import org.finance.models.finance.FinanceOffline;
import org.finance.services.DekaFinanceService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @GET
    @Path("/prediction")
    public Response getDekaFinancePrediction(@QueryParam("date") String dateStr) {
        try {
            MojoModel model = MojoModel.load("libs/GBM_grid_1_AutoML_1_20250218_130822_model_13.zip");
            double prediction = getPrediction(model, dateStr);
            String jsonResponse = "{\"predictedPrice\": " + prediction + "}";
            return Response.ok(jsonResponse).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Prediction error").build();
        }
    }

    private double getPrediction(MojoModel model, String dateStr) throws PredictException, ParseException {
        EasyPredictModelWrapper modelWrapper = new EasyPredictModelWrapper(model);
        RowData rowData = new RowData();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateStr);
        long timestamp = date.getTime();
        rowData.put("localdatechange", String.valueOf(timestamp));

        // Predict
        return modelWrapper.predictRegression(rowData).value;
    }
}