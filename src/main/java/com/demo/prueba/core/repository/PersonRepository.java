package com.demo.prueba.core.repository;


import com.demo.prueba.client.entity.PersonEntity;
import com.demo.prueba.client.entity.QPersonEntity;
import com.demo.prueba.client.repository.IPersonRepository;
import com.demo.prueba.core.repository.configuration.ConfigJPAPersistence;
import com.demo.prueba.vo.PersonVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Person repository
 * @author bcarrillo.
 * @version 1.0.0
 */
@Slf4j
@Lazy
@Repository
public class PersonRepository  extends ConfigJPAPersistence<PersonEntity> implements IPersonRepository {


    /**
     * Constructor.
     */
    public PersonRepository() {
        super(PersonEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePersonStatus(String personId, Boolean status) {
        QPersonEntity personEntity = QPersonEntity.personEntity;
        update(personEntity).where(
                        personEntity.personId.eq(personId))
                .set(personEntity.status, status)
                .set(personEntity.modificationUser, "admin")
                .set(personEntity.lastModifiedDate, new Date())
                .execute();
    }



}
