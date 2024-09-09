package com.demo.prueba.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class VO for accounts.
 *
 * @author bcarrillo.
 * @version 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountVo extends BaseAuditableVo {


    private String accountID;

    private String movementId;

    private String clientId;

    private String accountNumber;

    private String typeAccount;

    private Double balance;

    private String identifyNumber;
}
