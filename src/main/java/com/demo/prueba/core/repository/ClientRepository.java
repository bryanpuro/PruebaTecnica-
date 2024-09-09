package com.demo.prueba.core.repository;

import com.demo.prueba.client.entity.ClientEntity;
import com.demo.prueba.client.entity.QClientEntity;
import com.demo.prueba.client.entity.QPersonEntity;
import com.demo.prueba.client.repository.IClientRepository;
import com.demo.prueba.core.repository.configuration.ConfigJPAPersistence;
import com.demo.prueba.vo.ResponseClientVo;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static com.querydsl.core.types.Projections.bean;

/**
 * Client repository
 * @author bcarrillo.
 * @version 1.0.0
 */
@Slf4j
@Lazy
@Repository
public class ClientRepository extends ConfigJPAPersistence<ClientEntity> implements IClientRepository {

    /**
     * Constructor.
     */
    public ClientRepository() {
        super(ClientEntity.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ResponseClientVo> findallClients() {
        QClientEntity clientEntity = QClientEntity.clientEntity;
        QPersonEntity personEntity = QPersonEntity.personEntity;

        JPQLQuery<ClientEntity> query = from(clientEntity);
        query.leftJoin(clientEntity.personEntity, personEntity);
        query.where((clientEntity.status.eq(true)).and(personEntity.status.eq(true)));
        return query.select(bean(ResponseClientVo.class,
                clientEntity.password,
                clientEntity.status,
                clientEntity.personEntity.completeName,
                clientEntity.personEntity.age,
                clientEntity.personEntity.identifyNumber,
                clientEntity.personEntity.address,
                clientEntity.personEntity.phone,
                clientEntity.personEntity.gender
        )).fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientEntity findByCi(String identifyNumber) {
        QClientEntity clientEntity = QClientEntity.clientEntity;
        QPersonEntity personEntity = QPersonEntity.personEntity;
        JPQLQuery<ClientEntity> query = from(clientEntity);
        query.leftJoin(clientEntity.personEntity, personEntity);
        query.where(clientEntity.personEntity.identifyNumber.eq(identifyNumber));
        return query.fetchFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClientStatus(String clientId, Boolean status) {
        QClientEntity clientEntity = QClientEntity.clientEntity;
        update(clientEntity).where(
                        clientEntity.clientID.eq(clientId))
                .set(clientEntity.status, status)
                .set(clientEntity.modificationUser, "admin")
                .set(clientEntity.lastModifiedDate, new Date())
                .execute();
    }
}
