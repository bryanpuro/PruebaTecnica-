package com.demo.prueba.client.repository;

import com.demo.prueba.client.entity.AccountEntity;
import com.demo.prueba.client.repository.configuration.IConfigJPAPersistence;
import com.demo.prueba.vo.ResponseAccountVo;

import java.util.List;

public interface IAccountRepository extends IConfigJPAPersistence<AccountEntity> {

    /**
     * Busca cuentas por cliente.
     * @param identifyNumber
     * @return
     */
    List<ResponseAccountVo> findAccountsByClient(String identifyNumber);

    /**
     * Buscar una cuenta por numero.
     * @param accountNumber
     * @return
     */
    AccountEntity findAccountByNumber(String accountNumber);

    /**
     * Actualiza el estado de una cuenta
     * @param accountId
     * @param status
     */
    void updateAccountStatus(String accountId, Boolean status);

}
