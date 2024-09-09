package com.demo.prueba.client.service;

import com.demo.prueba.vo.ClientVo;
import com.demo.prueba.vo.ResponseClientVo;

import java.util.List;

public interface IClientService {

    /**
     * Crear cliente
     * @param clientVo
     */
    void createClient(ClientVo clientVo);

    /**
     *
     * @return lista de clientes
     */
    List<ResponseClientVo> findallClients();

    /**
     * Actualiza clientes
     * @param updateClientVo
     */
    void updateClient(ClientVo updateClientVo);

    /**
     * Borra clientes
     * @param identifyNumber
     */
    void  deleteClient(String identifyNumber);

    /**
     * Actualizar status del cliente
     * @param identifyNumber
     * @param status
     */
    void updateStatusClient(String identifyNumber, Boolean status);

}
