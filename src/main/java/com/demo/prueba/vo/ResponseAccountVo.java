package com.demo.prueba.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Class VO for response accounts.
 *
 * @author bcarrillo.
 * @version 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAccountVo {

    private String accountID;

    private String completeName;

    private String accountNumber;

    private String typeAccount;

    private Double balance;

    private Boolean status;

    private List<MovementByAccountVo>  movementByAccount;


}
