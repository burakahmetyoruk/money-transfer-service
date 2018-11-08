package model.account;

import model.BaseRequest;

public class AccountRequest implements BaseRequest {

    private String accountName;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}