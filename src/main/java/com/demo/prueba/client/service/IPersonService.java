package com.demo.prueba.client.service;


import com.demo.prueba.client.entity.PersonEntity;
import com.demo.prueba.vo.PersonVo;

public interface IPersonService {

    /**
     * Crear una persona
     * @param personVo
     * @return
     */
    PersonVo createPerson(PersonVo personVo);

    /**
     * Actualiza un cliente
     * @param personVo
     */
    void updatePerson(PersonVo personVo);

    /**
     * Busca el personEntity
     * @param personId
     * @return
     */
    PersonEntity findPersonEntity(String personId);

    /**
     * Actualiza el estado de la entidad persona.
     * @param personId
     * @param status
     */
    void updateStatusPerson(String personId, Boolean status);

}
