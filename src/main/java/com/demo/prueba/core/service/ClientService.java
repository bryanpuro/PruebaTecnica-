package com.demo.prueba.core.service;

import com.demo.prueba.client.entity.ClientEntity;
import com.demo.prueba.client.entity.PersonEntity;
import com.demo.prueba.client.repository.IClientRepository;
import com.demo.prueba.client.service.IClientService;
import com.demo.prueba.client.service.IPersonService;
import com.demo.prueba.vo.ClientVo;
import com.demo.prueba.vo.PersonVo;
import com.demo.prueba.vo.ResponseClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Transactional("jpaTransactionManager")
@Validated
@Lazy
@Service
public class ClientService implements IClientService {

    @Lazy
    @Autowired
    private IClientRepository iClientRepository;

    @Lazy
    @Autowired
    private IPersonService iPersonService;


    /**
     * {@inheritDoc}
     */
    @Override
    public void createClient(ClientVo clientVo) {
        if (clientVo.getPassword() == null) {
            throw new IllegalArgumentException("Error al ingresar al crear el cliente: \n Ingrese una constaseña. ");
        } else {

            PersonVo personVo = iPersonService.createPerson(clientVo.getPersonVo());
            if (personVo.getPersonId() != null) {
                ClientEntity clientEntity = ClientEntity.builder().
                        personId(personVo.getPersonId()).
                        password(clientVo.getPassword()).
                        build();
                clientEntity.setStatus(true);
                clientEntity.setCreatedDate(new Date());
                clientEntity.setRegisterUser("admin");
                iClientRepository.save(clientEntity);
            }

        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ResponseClientVo> findallClients() {
        List<ResponseClientVo> responseClientVos = iClientRepository.findallClients();
        if (responseClientVos != null) {
            return responseClientVos;
        }else{
            throw new IllegalArgumentException("No se ha encontrado clientes. ");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClient(ClientVo updateClientVo) {
        if (updateClientVo.getClientID() != null) {
            ClientEntity clientEntity = iClientRepository.findById(updateClientVo.getClientID());
            if (clientEntity == null) {
                throw new IllegalArgumentException("El cliente no existe");
            } else {
                if (updateClientVo.getPassword() != null) {
                    clientEntity.setPassword(updateClientVo.getPassword());
                    clientEntity.setModificationUser("admin");
                    clientEntity.setLastModifiedDate(new Date());
                    iClientRepository.update(clientEntity);
                }
            }
            if (updateClientVo.getPersonVo() != null) {
                if (Stream.of(
                        updateClientVo.getPersonVo().getCompleteName(),
                        updateClientVo.getPersonVo().getGender(),
                        updateClientVo.getPersonVo().getAge(),
                        updateClientVo.getPersonVo().getIdentifyNumber(),
                        updateClientVo.getPersonVo().getAddress(),
                        updateClientVo.getPersonVo().getPhone()
                ).anyMatch(Objects::nonNull)) {
                    updateClientVo.getPersonVo().setPersonId(clientEntity.getPersonId());
                    iPersonService.updatePerson(updateClientVo.getPersonVo());
                }
            }
        } else {
            throw new IllegalArgumentException("Ingrese el id del cliente para actualizar sus datos");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void  deleteClient(String identifyNumber){
        ClientEntity clientEntity = iClientRepository.findByCi(identifyNumber);
        if (clientEntity == null) {
            throw new IllegalArgumentException("El cliente no existe");
        }else{
            iClientRepository.delete(clientEntity);
            //Se debe borrar de la tabla persona
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatusClient(String identifyNumber, Boolean status){
        ClientEntity clientEntity = iClientRepository.findByCi(identifyNumber);
        if (clientEntity == null) {
            throw new IllegalArgumentException("El cliente no existe");
        }else{
            iClientRepository.updateClientStatus(clientEntity.getClientID(), status);
            PersonEntity personEntity = iPersonService.findPersonEntity(clientEntity.getPersonId());
            iPersonService.updateStatusPerson(personEntity.getPersonId(),status);
            //// se debe borrar cuenta y movimientos.
        }
    }
}
