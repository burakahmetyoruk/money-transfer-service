package model.transfer;

import model.base.BaseResponse;

import java.math.BigDecimal;

public class TransferResponse extends BaseResponse {

    private Long transferId;

    private Long created;

    private BigDecimal transferAmount;

    private String transferrerName;

    private String transferredName;

    public TransferResponse() {
    }

    public TransferResponse(Long transferId, Long created, BigDecimal transferAmount, String transferrerName, String transferredName) {
        this.transferId = transferId;
        this.created = created;
        this.transferAmount = transferAmount;
        this.transferrerName = transferrerName;
        this.transferredName = transferredName;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getTransferrerName() {
        return transferrerName;
    }

    public void setTransferrerName(String transferrerName) {
        this.transferrerName = transferrerName;
    }

    public String getTransferredName() {
        return transferredName;
    }

    public void setTransferredName(String transferredName) {
        this.transferredName = transferredName;
    }
}