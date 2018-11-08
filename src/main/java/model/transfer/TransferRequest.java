package model.transfer;

import model.BaseRequest;

import java.math.BigDecimal;

public class TransferRequest implements BaseRequest {

    private String transferrerAccountName;

    private String transferredAccountName;

    private BigDecimal transferAmount;

    public String getTransferrerAccountName() {
        return transferrerAccountName;
    }

    public void setTransferrerAccountName(String transferrerAccountName) {
        this.transferrerAccountName = transferrerAccountName;
    }

    public String getTransferredAccountName() {
        return transferredAccountName;
    }

    public void setTransferredAccountName(String transferredAccountName) {
        this.transferredAccountName = transferredAccountName;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }
}