package com.bank.transfersystem.banktransfersystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bank")
@Getter
@Setter
public class Bank {

    @Id
    @Column(name = "bank_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_bank_id_seq")
    @SequenceGenerator(name = "bank_bank_id_seq", sequenceName = "bank_bank_id_seq", allocationSize = 1)
    private Long bankId;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_address")
    private String bankAddress;

    @OneToOne(mappedBy = "bank")
    private Account account;

    
}
