package rest;

import model.transfer.TransferRequest;
import model.transfer.TransferResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.TransferService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class TransferController implements BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);
    private static final TransferController transferController = new TransferController();

    private final TransferService transferService;

    public static TransferController of() {
        return transferController;
    }

    TransferResponse transfer(Request request, Response response) throws SQLException {
        TransferRequest transferRequest = retrieveRequest(request, TransferRequest.class);
        final TransferResponse transfer = transferService.transfer(transferRequest);
        response.status(HttpStatus.SC_CREATED);
        return transfer;
    }

    private TransferController() {
        this.transferService = TransferService.of();
    }
}