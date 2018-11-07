package rest;

import com.google.gson.Gson;

import static spark.Spark.get;
import static spark.Spark.post;

public class EndpointInitializer {

    public static void initialize() {
        Gson gson = new Gson();

        AccountController accountController = AccountController.of();
        get("/accounts/:name", (request, response) -> accountController.retrieve(request.params("name")), gson::toJson);
        post("/accounts", accountController::create, gson::toJson);

        TransferController transferController = TransferController.of();
        post("/transfers", transferController::transfer, gson::toJson);
    }
}