package com.demo.prueba.client.repository;

import com.demo.prueba.client.entity.PersonEntity;
import com.demo.prueba.client.repository.configuration.IConfigJPAPersistence;
import com.demo.prueba.vo.PersonVo;


public interface IPersonRepository extends IConfigJPAPersistence<PersonEntity> {

    /**
     * Actualiza el status de la entidad persona.
     * @param personId
     * @param status
     */
    void updatePersonStatus(String personId, Boolean status);
}
