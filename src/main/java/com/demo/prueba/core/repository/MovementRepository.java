package com.demo.prueba.core.repository;


import com.demo.prueba.client.entity.*;
import com.demo.prueba.client.repository.IMovementRepository;
import com.demo.prueba.core.repository.configuration.ConfigJPAPersistence;
import com.demo.prueba.vo.MovementByAccountVo;
import com.demo.prueba.vo.ResponseAccountVo;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static com.querydsl.core.types.Projections.bean;

/**
 * Account repository
 *
 * @author bcarrillo.
 * @version 1.0.0
 */
@Slf4j
@Lazy
@Repository
public class MovementRepository extends ConfigJPAPersistence<MovementEntity> implements IMovementRepository {

    /**
     * Constructor.
     */
    public MovementRepository() {
        super(MovementEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MovementByAccountVo> findMovementsByAccount(String accountID) {

        QMovementEntity movementEntity = QMovementEntity.movementEntity;

        JPQLQuery<MovementEntity> query = from(movementEntity);

        query.where(movementEntity.accountID.eq(accountID)
                .and(movementEntity.status.eq(true)));
        return query.select(bean(MovementByAccountVo.class,
                movementEntity.dateMovement,
                movementEntity.movementType,
                movementEntity.movementValue,
                movementEntity.status,
                movementEntity.availableBalance
        )).fetch();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<MovementByAccountVo> findMovements(String accountID, Date datefrom, Date dateTo) {

        QMovementEntity movementEntity = QMovementEntity.movementEntity;

        JPQLQuery<MovementEntity> query = from(movementEntity);

        query.where(movementEntity.accountID.eq(accountID)
                .and(movementEntity.status.eq(true))
                .and(movementEntity.dateMovement.between(datefrom, dateTo)));
        return query.select(bean(MovementByAccountVo.class,
                movementEntity.dateMovement,
                movementEntity.movementType,
                movementEntity.movementValue,
                movementEntity.status,
                movementEntity.availableBalance
        )).fetch();
    }
}
