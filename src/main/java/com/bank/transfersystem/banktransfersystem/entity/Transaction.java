package com.bank.transfersystem.banktransfersystem.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_transaction_id_seq")
    @SequenceGenerator(name = "transaction_transaction_id_seq", sequenceName = "transaction_transaction_id_seq", allocationSize = 1)
    private Long transactionId;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "amount")
    private double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "source_account", referencedColumnName = "account_id")
    private Account sourceAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destined_account", referencedColumnName = "account_id")
    private Account destinedAccount;

}
