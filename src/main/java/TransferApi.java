import rest.EndpointInitializer;
import rest.ExceptionHandlerInitializer;
import domain.H2DataBaseService;


public class TransferApi {

    public static void main(String[] args) {
        ExceptionHandlerInitializer.initialize();
        H2DataBaseService.init();
        EndpointInitializer.initialize();
    }
}