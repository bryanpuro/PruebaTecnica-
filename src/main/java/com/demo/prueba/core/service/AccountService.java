package com.demo.prueba.core.service;

import com.demo.prueba.client.entity.AccountEntity;
import com.demo.prueba.client.entity.ClientEntity;
import com.demo.prueba.client.entity.PersonEntity;
import com.demo.prueba.client.repository.IAccountRepository;
import com.demo.prueba.client.repository.IClientRepository;
import com.demo.prueba.client.repository.IMovementRepository;
import com.demo.prueba.client.service.IAccountService;
import com.demo.prueba.vo.AccountVo;
import com.demo.prueba.vo.MovementByAccountVo;
import com.demo.prueba.vo.PersonVo;
import com.demo.prueba.vo.ResponseAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Transactional("jpaTransactionManager")
@Validated
@Lazy
@Service
public class AccountService implements IAccountService {

    @Lazy
    @Autowired
    private IAccountRepository iAccountRepository;
    @Lazy
    @Autowired
    private IClientRepository iClientRepository;

    @Lazy
    @Autowired
    IMovementRepository iMovementRepository;

    Set<Integer> generatedNumbers = new HashSet<>();
    Random random = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public void createAccount(AccountVo accountVo) {
        if (accountVo.getIdentifyNumber() == null || accountVo.getTypeAccount() == null) {
            throw new IllegalArgumentException("Error al ingresar los datos, \n Recuerde ingresar: " +
                    " \n Cédula del cliente, \n Tipo de cuenta: Corriente/Ahorros \n Saldo: De ser necesario");
        } else {

            ClientEntity clientEntity = iClientRepository.findByCi(accountVo.getIdentifyNumber());
            if (clientEntity != null) {
                int accounNumber = generateUniqueRandomNumber();

                accountVo.setClientId(clientEntity.getClientID());
                accountVo.setAccountNumber(String.valueOf(accounNumber));


                AccountEntity accountEntity = AccountEntity.builder().
                        clientId(accountVo.getClientId()).
                        accountNumber(accountVo.getAccountNumber()).
                        typeAccount(accountVo.getTypeAccount()).
                        balance(accountVo.getBalance() != null ? accountVo.getBalance() : 0).
                        build();
                accountEntity.setStatus(true);
                accountEntity.setCreatedDate(new Date());
                accountEntity.setRegisterUser("admin");
                iAccountRepository.save(accountEntity);
            } else {
                throw new IllegalArgumentException("El cliente no existe.");
            }

        }
    }

    private int generateUniqueRandomNumber() {
        int randomNumber;
        do {
            randomNumber = 100000 + random.nextInt(900000);
        } while (generatedNumbers.contains(randomNumber));
        generatedNumbers.add(randomNumber);
        return randomNumber;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAccount(AccountVo accountVo) {
        AccountEntity accountEntity = iAccountRepository.findAccountByNumber(accountVo.getAccountNumber());
        if (accountEntity == null) {
            throw new IllegalArgumentException("La cuenta con número: " + accountVo.getAccountNumber() + " no existe");
        } else {
            accountEntity.setTypeAccount((accountVo.getTypeAccount() != null) ? accountVo.getTypeAccount() : accountEntity.getTypeAccount());
            accountEntity.setBalance((accountVo.getBalance() != null) ? accountVo.getBalance() : accountEntity.getBalance());
            accountEntity.setModificationUser("admin");
            accountEntity.setLastModifiedDate(new Date());
            iAccountRepository.update(accountEntity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ResponseAccountVo> findAccountsByClient(String identifyNumber) {

        ClientEntity clientEntity = iClientRepository.findByCi(identifyNumber);
        if (clientEntity != null) {
            List<ResponseAccountVo> responseAccountList = iAccountRepository.findAccountsByClient(identifyNumber);
            responseAccountList.forEach(account -> {
                List<MovementByAccountVo>  movementByAccountVos = iMovementRepository.findMovementsByAccount(account.getAccountID());
                account.setMovementByAccount(movementByAccountVos);
            });

            if (responseAccountList != null) {
                return responseAccountList;
            } else {
                throw new IllegalArgumentException("El cliente no tiene cuentas registradas.");
            }
        } else {
            throw new IllegalArgumentException("Ingrese un cliente valido.");

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAccountStatus(String accountNumber, Boolean status) {
        AccountEntity accountEntity = iAccountRepository.findAccountByNumber(accountNumber);
        if (accountEntity == null) {
            throw new IllegalArgumentException("No existe una cuenta con el numero: " + accountNumber);
        } else {
            iAccountRepository.updateAccountStatus(accountEntity.getAccountID(), status);
//            PersonEntity personEntity = iPersonService.findPersonEntity(clientEntity.getPersonId());
//            iPersonService.updateStatusPerson(personEntity.getPersonId(),status);
        }
    }
}
