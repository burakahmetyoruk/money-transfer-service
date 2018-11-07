import rest.EndpointInitializer;
import rest.ExceptionHandlerInitializer;


public class TransferApi {

    public static void main(String[] args) {
        ExceptionHandlerInitializer.initialize();
        EndpointInitializer.initialize();
    }
}