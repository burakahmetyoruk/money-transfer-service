package model.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TransferRequest {

    @JsonProperty(value = "transferrer_account_name")
    private String transferrerAccountName;

    @JsonProperty(value = "transferred_account_name")
    private String transferredAccountName;

    @JsonProperty(value = "transfer_amount")
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