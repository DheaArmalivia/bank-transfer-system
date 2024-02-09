package com.bank.transfersystem.banktransfersystem.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_account_id_seq")
    @SequenceGenerator(name = "account_account_id_seq", sequenceName = "account_account_id_seq", allocationSize = 1)
    private Long accountId;

    @Column(name = "balance")
    private double balance;

    @Column(name = "account_no")
    private String accountNo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id", referencedColumnName = "bank_id")
    private Bank bank;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sourceAccount", cascade = CascadeType.ALL)
    private List<Transaction> sourceAccount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "destinedAccount", cascade = CascadeType.ALL)
    private List<Transaction> destinedAccount;

}
