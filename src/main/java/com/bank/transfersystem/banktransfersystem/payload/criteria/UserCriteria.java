package com.bank.transfersystem.banktransfersystem.payload.criteria;
import java.util.*;

import lombok.*;

@Getter
@Setter
public class UserCriteria {

    private Long userId;

    private String name;

    private String password;

    private String address;

    private String placeOfBirth;

    private String dateOfBirth;

    private String email;

    private String phone_number;

    private List<AccountCriteria> accounts;

}
