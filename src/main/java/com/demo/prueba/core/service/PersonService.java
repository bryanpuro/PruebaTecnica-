package com.demo.prueba.core.service;

import com.demo.prueba.client.entity.PersonEntity;
import com.demo.prueba.client.repository.IPersonRepository;
import com.demo.prueba.client.service.IPersonService;
import com.demo.prueba.vo.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Transactional("jpaTransactionManager")
@Validated
@Lazy
@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonVo createPerson(PersonVo personVo) {
        if (personVo.getIdentifyNumber() == null || personVo.getCompleteName() == null ||
                personVo.getAddress() == null || personVo.getPhone() == null) {
            throw new IllegalArgumentException("Error al ingresar los datos, \n Recuerde ingresar: " +
                    "\n Nombre completo, \n Cédula, \n Dirección, \n Telefono ");
        } else {
            PersonEntity personEntity = PersonEntity.builder().
                    completeName(personVo.getCompleteName()).
                    gender(personVo.getGender()).
                    age(personVo.getAge()).
                    identifyNumber(personVo.getIdentifyNumber()).
                    address(personVo.getAddress()).
                    phone(personVo.getPhone()).
                    build();
            personEntity.setStatus(true);
            personEntity.setCreatedDate(new Date());
            personEntity.setRegisterUser("admin");
            repository.save(personEntity);
            personVo.setPersonId(personEntity.getPersonId());
            personVo.setStatus(personEntity.getStatus());
        }
        return personVo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePerson(PersonVo personVo) {

        PersonEntity personEntity = repository.findById(personVo.getPersonId());
        if (personEntity == null) {
            throw new IllegalArgumentException("La persona a actualizar no existe");
        } else {
            personEntity.setCompleteName((personVo.getCompleteName() != null)  ?personVo.getCompleteName() : personEntity.getCompleteName());
            personEntity.setGender((personVo.getGender() != null) ? personVo.getGender() : personEntity.getGender());
            personEntity.setAge((personVo.getAge() != null) ? personVo.getAge():personEntity.getAge());
            personEntity.setIdentifyNumber((personVo.getIdentifyNumber() != null)? personVo.getIdentifyNumber() : personEntity.getIdentifyNumber());
            personEntity.setAddress((personVo.getAddress() != null) ? personVo.getAddress() : personEntity.getAddress());
            personEntity.setPhone((personVo.getPhone() != null) ? personVo.getPhone() : personEntity.getPhone());
            personEntity.setModificationUser("admin");
            personEntity.setLastModifiedDate(new Date());
            repository.update(personEntity);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PersonEntity findPersonEntity(String personId){
        return repository.findById(personId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatusPerson(String personId, Boolean status){
        repository.updatePersonStatus( personId, status);
    }
}
