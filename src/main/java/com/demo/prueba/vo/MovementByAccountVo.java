package com.demo.prueba.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class MovementByAccountVo {

    private Boolean status;

    private Date dateMovement;

    private String movementType;

    private Double movementValue;

    private Double availableBalance;
}
