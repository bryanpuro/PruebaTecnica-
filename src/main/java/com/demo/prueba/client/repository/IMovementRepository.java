package com.demo.prueba.client.repository;

import com.demo.prueba.client.entity.MovementEntity;
import com.demo.prueba.client.repository.configuration.IConfigJPAPersistence;
import com.demo.prueba.vo.MovementByAccountVo;

import java.util.Date;
import java.util.List;

public interface IMovementRepository extends IConfigJPAPersistence<MovementEntity> {
    /**
     * Lista de movimientos por cuenta.
     * @param accountID
     * @return
     */
    List<MovementByAccountVo> findMovementsByAccount(String accountID);

    /**
     * Busca movimientos por fecha
     * @param accountID
     * @param datefrom
     * @param dateTo
     * @return
     */
    List<MovementByAccountVo> findMovements(String accountID, Date datefrom, Date dateTo);
}
