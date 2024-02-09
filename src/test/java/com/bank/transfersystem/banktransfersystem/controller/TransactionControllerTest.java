package com.bank.transfersystem.banktransfersystem.controller;

import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bank.transfersystem.banktransfersystem.entity.Account;
import com.bank.transfersystem.banktransfersystem.entity.Transaction;
import com.bank.transfersystem.banktransfersystem.payload.request.FundTransferRequest;
import com.bank.transfersystem.banktransfersystem.service.AccountService;
import com.bank.transfersystem.banktransfersystem.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionService trxService;

    private ObjectMapper mapper = new ObjectMapper();

    private Account sourceAccountExist;
    private Account destinedAccountExist;
    private Transaction transaction;
    private FundTransferRequest request;

    @BeforeEach
    public void init() {
        sourceAccountExist = Account.builder().accountNo("123456").balance(10000000.0).build();
        destinedAccountExist = Account.builder().accountNo("987654").balance(20000000.0).build();
    }

    @Test
    public void fundTransfer_transferSuccess_returnResponseAndStatusOK() throws Exception {
        transaction = Transaction.builder().transactionId(1L).transactionDate(new Date()).amount(100000).build();
        when(accountService.findByAccountNo("123456")).thenReturn(Optional.of(sourceAccountExist));
        when(accountService.findByAccountNo("987654")).thenReturn(Optional.of(destinedAccountExist));
        when(trxService.save(any(Transaction.class))).thenReturn(transaction);
        when(accountService.save(sourceAccountExist)).thenReturn(sourceAccountExist);
        when(accountService.save(destinedAccountExist)).thenReturn(destinedAccountExist);

        request = FundTransferRequest.builder()
            .amount(100000)
            .sourceAccount("123456")
            .destinedAccount("987654")
            .build();

        ResultActions response = mockMvc.perform(
            MockMvcRequestBuilders.post("/rest/api/transaction/fund-transfer")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.transactionDate", CoreMatchers.notNullValue()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.transactionID", CoreMatchers.notNullValue()));
    }

    @Test
    public void fundTransfer_sourceAccountNotExist_returnResponseAndStatusBadRequest() throws Exception {
        transaction = Transaction.builder().transactionId(1L).transactionDate(new Date()).amount(100000).build();
        when(accountService.findByAccountNo("123456")).thenReturn(Optional.empty());
        when(accountService.findByAccountNo("987654")).thenReturn(Optional.of(destinedAccountExist));
        when(trxService.save(any(Transaction.class))).thenReturn(transaction);
        when(accountService.save(sourceAccountExist)).thenReturn(sourceAccountExist);
        when(accountService.save(destinedAccountExist)).thenReturn(destinedAccountExist);

        request = FundTransferRequest.builder()
            .amount(100000)
            .sourceAccount("123456")
            .destinedAccount("987654")
            .build();

        ResultActions response = mockMvc.perform(
            MockMvcRequestBuilders.post("/rest/api/transaction/fund-transfer")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void fundTransfer_destinedAccountNotExist_returnResponseAndStatusBadRequest() throws Exception {
        transaction = Transaction.builder().transactionId(1L).transactionDate(new Date()).amount(100000).build();
        when(accountService.findByAccountNo("123456")).thenReturn(Optional.of(sourceAccountExist));
        when(accountService.findByAccountNo("987654")).thenReturn(Optional.empty());
        when(trxService.save(any(Transaction.class))).thenReturn(transaction);
        when(accountService.save(sourceAccountExist)).thenReturn(sourceAccountExist);
        when(accountService.save(destinedAccountExist)).thenReturn(destinedAccountExist);

        request = FundTransferRequest.builder()
            .amount(100000)
            .sourceAccount("123456")
            .destinedAccount("987654")
            .build();

        ResultActions response = mockMvc.perform(
            MockMvcRequestBuilders.post("/rest/api/transaction/fund-transfer")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void fundTransfer_insufficientBalance_returnResponseAndStatusBadRequest() throws Exception {
        transaction = Transaction.builder().transactionId(1L).transactionDate(new Date()).amount(999999999).build();
        when(accountService.findByAccountNo("123456")).thenReturn(Optional.of(sourceAccountExist));
        when(accountService.findByAccountNo("987654")).thenReturn(Optional.of(destinedAccountExist));
        when(trxService.save(any(Transaction.class))).thenReturn(transaction);
        when(accountService.save(sourceAccountExist)).thenReturn(sourceAccountExist);
        when(accountService.save(destinedAccountExist)).thenReturn(destinedAccountExist);

        request = FundTransferRequest.builder()
            .amount(999999999)
            .sourceAccount("123456")
            .destinedAccount("987654")
            .build();

        ResultActions response = mockMvc.perform(
            MockMvcRequestBuilders.post("/rest/api/transaction/fund-transfer")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
