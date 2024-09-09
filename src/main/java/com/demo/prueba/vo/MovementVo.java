package com.demo.prueba.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

/**
 * Class VO for movements.
 *
 * @author bcarrillo.
 * @version 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementVo extends BaseAuditableVo {

    private String movementId;

    private String accountID;

    private String accountNumber;

    private Date dateMovement;

    private String movementType;

    private Double movementValue;

    private Double availableBalance;
}
