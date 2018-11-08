# Money Transfer API

# Tech Stack
```bash
Spark Framework
H2
Java 8
Mockito
JUnit
```

# Requirements

Maven

JDK 1.8 and above.

# Installation

### Git Checkout

Step 1: Clone the repository via Git. Run the following command:

```bash
https://github.com/burakahmetyoruk/money-transfer-service.git
```

### Maven Build

Step 2: Maven build

```bash
cd money-transfer-service
mvn clean package
```

### Running

```bash
mvn exec:java
```

# Documentation

### Introduction

The application starts on port 8080.  
The accounts are created in the system with initial balance 1000 for test simplicity.

| Account ID  | Balance | Name
| :---: | :---: | :---: 
| 1  | 1000  | accountName
| 2  | 1000  | accountName1

#### Accounts

##### Create Account

This endpoint creates account.

**Request** 

`POST http://localhost:8080/accounts`

<sub><sup>**Request JSON**</sup></sub>

{
    "account_name": "accountName"
}

| Field  | Description | Format |
| :---: | :---: | :---:
| account_name  | name of the account  | String

**Response**

Response with HTTP 201 CREATED status

| Field  | Description | Format |
| :---: | :---: | :---:
| name  | name of the account  | String
| balance  | the available balance  | BigDecimal
| operation_result.return_code  | System return code | return code
| operation_result.return_message  | System return message  | return message

##### Get Account

This endpoint retrieves one of your accounts by ID.

**Request** 

`GET http://localhost:8080/accounts/{accountId}`

**Response** 

| Field  | Description | Format |
| :---: | :---: | :---:
| id  | the account ID  | Integer
| name  | name of the account  | String
| balance  | the available balance  | BigDecimal
| operation_result.return_code  | System return code | return code
| operation_result.return_message  | System return message  | return message

##### Transfer

This endpoint processes transfers between accounts.

**Request** 

`POST http://localhost:8080/transfers`

<sub><sup>**Request JSON**</sup></sub>

{
    "transferrer_account_name": "transferrerName",
    "transferred_account_name": "transferredName",
    "transfer_amount": 123.45
}

| Field  | Description | Format |
| :---: | :---: | :---:
| transferrer_account_name  | the sender account name  | String
| transferred_account_name  | the receiver account name  | String
| transfer_amount  | the transfer amount  | BigDecimal

**Response** 


| Field  | Description | Format |
| :---: | :---: | :---:
| transferrer_name  | the sender account name  | String
| transferred_name  | the receiver account name  | String
| transfer_amount  | the transfer amount  | BigDecimal
| created  | Transfer Time  | Long
| operation_result.return_code  | System return code | return code
| operation_result.return_message  | System return message 



