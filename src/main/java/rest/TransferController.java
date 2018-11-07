package rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.entity.Transfer;
import exception.RequestNotValidException;
import model.transfer.TransferRequest;
import model.transfer.TransferResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.TransferService;
import spark.Request;
import spark.Response;

import java.io.IOException;

public class TransferController {

    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);
    private static final TransferController transferController = new TransferController();

    private final TransferService transferService;

    public static TransferController of() {
        return transferController;
    }

    TransferResponse transfer(Request request, Response response) {
        TransferRequest transferRequest = retrieveRequest(request);
        return transferService.transfer(transferRequest);
    }

    private TransferRequest retrieveRequest(Request request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(request.body(), TransferRequest.class);
        } catch (IOException e) {
            logger.error("Error Occurred When Parsing Request", e);
            throw new RequestNotValidException();
        }
    }

    private TransferController() {
        this.transferService = TransferService.of();
    }
}