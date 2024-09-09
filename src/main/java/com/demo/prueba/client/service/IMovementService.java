package com.demo.prueba.client.service;

import com.demo.prueba.vo.MovementVo;
import com.demo.prueba.vo.ResponseAccountVo;

import java.util.Date;
import java.util.List;

public interface IMovementService {

    /**
     * Crear un movimiento
     * @param movementVo
     */
    void createMovement(MovementVo movementVo);

    /**
     * Actualiza un movimiento de cuenta
     * @param movementVo
     */
    void updateMovement(MovementVo movementVo);

    /**
     * Lista movimientos con sus cuentas por fecha
     * @param identifyNumber
     * @param datefrom
     * @param dateTo
     * @return
     */
    List<ResponseAccountVo> findMovementsAccountsByDate(String identifyNumber, Date datefrom, Date dateTo);
}
