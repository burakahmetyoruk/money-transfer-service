package model.account;


import com.fasterxml.jackson.annotation.JsonProperty;
import model.base.BaseResponse;

import java.math.BigDecimal;

public class AccountResponse extends BaseResponse {

    @JsonProperty(value = "account_id")
    private Long id;

    @JsonProperty(value = "account_name")
    private String name;

    private BigDecimal balance;

    public AccountResponse() {
    }

    public AccountResponse(Long id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}