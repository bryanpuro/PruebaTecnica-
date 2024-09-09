package com.demo.prueba.client.repository;


import com.demo.prueba.client.entity.ClientEntity;
import com.demo.prueba.client.repository.configuration.IConfigJPAPersistence;
import com.demo.prueba.vo.ResponseClientVo;

import java.util.List;

public interface IClientRepository extends IConfigJPAPersistence<ClientEntity> {

    /**
     * Listado de clientes
     * @return
     */
    List<ResponseClientVo> findallClients();

    /**
     * Busca cliente por cedula.
     * @return ClientEntity
     */
    ClientEntity findByCi(String identifyNumber);

    /**
     * Actualiza el status de la entidad cliente.
     * @param clientId
     * @param status
     */
    void updateClientStatus(String clientId, Boolean status);

}
