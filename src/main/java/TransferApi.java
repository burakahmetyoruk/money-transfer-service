import domain.H2DataBaseService;
import rest.EndpointInitializer;
import rest.ExceptionHandlerInitializer;

import static spark.Spark.port;


public class TransferApi {

    public static void main(String[] args) {
        port(8080);
        ExceptionHandlerInitializer.initialize();
        H2DataBaseService.init();
        EndpointInitializer.initialize();
    }
}