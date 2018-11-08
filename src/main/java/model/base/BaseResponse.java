package model.base;


public class BaseResponse {

    private OperationResult operationResult = new OperationResult(0, "Success");

    public BaseResponse() {
    }

    public BaseResponse(OperationResult operationResult) {
        this.operationResult = operationResult;
    }

    public OperationResult getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(OperationResult operationResult) {
        this.operationResult = operationResult;
    }
}