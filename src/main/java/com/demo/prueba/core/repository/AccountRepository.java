package com.demo.prueba.core.repository;

import com.demo.prueba.client.entity.*;
import com.demo.prueba.client.repository.IAccountRepository;
import com.demo.prueba.core.repository.configuration.ConfigJPAPersistence;
import com.demo.prueba.vo.ResponseAccountVo;
import com.demo.prueba.vo.ResponseClientVo;
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
public class AccountRepository extends ConfigJPAPersistence<AccountEntity> implements IAccountRepository {

    /**
     * Constructor.
     */
    public AccountRepository() {
        super(AccountEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountEntity findAccountByNumber(String accountNumber) {
        QAccountEntity accountEntity = QAccountEntity.accountEntity;
        JPQLQuery<AccountEntity> query = from(accountEntity);
        query.where(accountEntity.accountNumber.eq(accountNumber));
        return query.fetchFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ResponseAccountVo> findAccountsByClient(String identifyNumber) {
        QAccountEntity accountEntity = QAccountEntity.accountEntity;
        QClientEntity clientEntity = QClientEntity.clientEntity;
        QPersonEntity personEntity = QPersonEntity.personEntity;

        JPQLQuery<AccountEntity> query = from(accountEntity);
        query.leftJoin(accountEntity.clientEntity,clientEntity);
        query.leftJoin(clientEntity.personEntity, personEntity);
        query.where(accountEntity.clientEntity.personEntity.identifyNumber.eq(identifyNumber)
                .and(accountEntity.status.eq(true)));
        return query.select(bean(ResponseAccountVo.class,
                accountEntity.accountID,
                accountEntity.accountNumber,
                accountEntity.clientEntity.personEntity.completeName,
                accountEntity.typeAccount,
                accountEntity.balance,
                accountEntity.status
        )).fetch();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAccountStatus(String accountId, Boolean status) {
        QAccountEntity accountEntity = QAccountEntity.accountEntity;
        update(accountEntity).where(
                        accountEntity.accountID.eq(accountId))
                .set(accountEntity.status, status)
                .set(accountEntity.modificationUser, "admin")
                .set(accountEntity.lastModifiedDate, new Date())
                .execute();
    }
}
