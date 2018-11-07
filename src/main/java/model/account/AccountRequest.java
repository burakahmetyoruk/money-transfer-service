package model.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequest {

    @JsonProperty(value = "account_name")
    private String  accountName;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}