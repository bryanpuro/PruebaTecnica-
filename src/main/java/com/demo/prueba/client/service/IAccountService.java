package com.demo.prueba.client.service;

import com.demo.prueba.vo.AccountVo;
import com.demo.prueba.vo.ResponseAccountVo;

import java.util.List;

public interface IAccountService {
    /**
     * Crear una cuenta para un cliente
     * @param accountVo
     */
    void createAccount(AccountVo accountVo);

    /**
     * Actualiar una cuenta.
     * @param accountVo
     */
    void updateAccount(AccountVo accountVo);

    /**
     * Busca cuentas por cliente.
     * @param identifyNumber
     * @return
     */
    List<ResponseAccountVo> findAccountsByClient(String identifyNumber);

    /**
     * Actualiza el estado de una cuenta.
     * @param accountNumber
     * @param status
     */
    void updateAccountStatus(String accountNumber, Boolean status);
}
