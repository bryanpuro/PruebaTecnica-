package com.demo.prueba.core.service;

import com.demo.prueba.client.entity.AccountEntity;
import com.demo.prueba.client.entity.ClientEntity;
import com.demo.prueba.client.entity.MovementEntity;
import com.demo.prueba.client.repository.IAccountRepository;
import com.demo.prueba.client.repository.IClientRepository;
import com.demo.prueba.client.repository.IMovementRepository;
import com.demo.prueba.client.service.IAccountService;
import com.demo.prueba.client.service.IMovementService;
import com.demo.prueba.vo.AccountVo;
import com.demo.prueba.vo.MovementByAccountVo;
import com.demo.prueba.vo.MovementVo;
import com.demo.prueba.vo.ResponseAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Transactional("jpaTransactionManager")
@Validated
@Lazy
@Service
public class MovementService implements IMovementService {

    @Lazy
    @Autowired
    private IAccountRepository iAccountRepository;

    @Lazy
    @Autowired
    private IMovementRepository iMovementRepository;

    @Lazy
    @Autowired
    private IAccountService iAccountService;


    @Lazy
    @Autowired
    private IClientRepository iClientRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createMovement(MovementVo movementVo) {
        if (movementVo.getAccountNumber() == null || movementVo.getMovementValue() == null) {
            throw new IllegalArgumentException("Error al ingresar los datos, \n Recuerde ingresar: " +
                    " \n El valor del movimiento \n Número de cuenta");
        } else {

            AccountEntity accountEntity = iAccountRepository.findAccountByNumber(movementVo.getAccountNumber());
            AccountVo accountVo = new AccountVo();
            if (accountEntity != null) {

                movementVo.setAccountID(accountEntity.getAccountID());
                movementVo.setDateMovement(new Date());
                movementVo.setMovementType((movementVo.getMovementValue() < 0) ? "Retiro" : "Deposito");

                if (movementVo.getMovementValue() < 0) {
                    if (accountEntity.getBalance() == 0 || (-movementVo.getMovementValue() > accountEntity.getBalance())) {
                        throw new IllegalArgumentException("Saldo no disponible.");
                    } else {
                        movementVo.setAvailableBalance(accountEntity.getBalance() + (movementVo.getMovementValue()));

                        accountVo.setAccountID(accountEntity.getAccountID());
                        accountVo.setAccountNumber(accountEntity.getAccountNumber());
                        accountVo.setBalance(movementVo.getAvailableBalance());
                        iAccountService.updateAccount(accountVo);

                        setDataMovement(movementVo, accountEntity);

                    }

                } else {
                    movementVo.setAvailableBalance(accountEntity.getBalance() + movementVo.getMovementValue());

                    accountVo.setAccountID(accountEntity.getAccountID());
                    accountVo.setBalance(movementVo.getAvailableBalance());
                    accountVo.setAccountNumber(accountEntity.getAccountNumber());
                    iAccountService.updateAccount(accountVo);

                    setDataMovement(movementVo, accountEntity);

                }

            } else {
                throw new IllegalArgumentException("La cuenta ingresada no existe.");
            }

        }
    }

    /**
     * Setea datos para crear la entidad.
     *
     * @param movementVo
     * @param accountEntity
     */
    private void setDataMovement(MovementVo movementVo, AccountEntity accountEntity) {
        MovementEntity movementEntity = MovementEntity.builder().
                accountID(accountEntity.getAccountID()).
                dateMovement(movementVo.getDateMovement()).
                movementType(movementVo.getMovementType()).
                movementValue(movementVo.getMovementValue()).
                availableBalance(movementVo.getAvailableBalance()).
                build();
        movementEntity.setStatus(true);
        movementEntity.setCreatedDate(new Date());
        movementEntity.setRegisterUser("admin");
        iMovementRepository.save(movementEntity);
    }


    public void updateMovement(MovementVo movementVo) {
        MovementEntity movementEntity = iMovementRepository.findById(movementVo.getMovementId());
        if (movementEntity == null) {
            throw new IllegalArgumentException("El movimiento que busca no existe");

        } else {
            AccountEntity accountEntity = iAccountRepository.findById(movementEntity.getAccountID());
            AccountVo accountVo = new AccountVo();
            double diferrenceVal;

            movementVo.setMovementType((movementVo.getMovementValue() < 0) ? "Retiro" : "Deposito");

            if (movementVo.getMovementValue() > 0 && movementVo.getMovementValue() > movementEntity.getMovementValue()
                    && movementEntity.getMovementType().equalsIgnoreCase("Deposito")) {
                //deposito
                diferrenceVal = movementVo.getMovementValue() - movementEntity.getMovementValue();
                accountVo.setBalance(accountEntity.getBalance() + diferrenceVal);
            }

            if (movementVo.getMovementValue() > 0 && movementVo.getMovementValue() < movementEntity.getMovementValue()
                    && movementEntity.getMovementType().equalsIgnoreCase("Deposito")) {
                diferrenceVal = movementEntity.getMovementValue() - movementVo.getMovementValue();
                accountVo.setBalance(accountEntity.getBalance() - diferrenceVal);
            }

            if (movementVo.getMovementValue() < 0 && movementVo.getMovementValue() < movementEntity.getMovementValue()
                    && movementEntity.getMovementType().equalsIgnoreCase("Retiro")) {
                diferrenceVal = movementEntity.getMovementValue() - (movementVo.getMovementValue());
                accountVo.setBalance(accountEntity.getBalance() - diferrenceVal);
            }

            if (movementVo.getMovementValue() < 0 && movementVo.getMovementValue() > movementEntity.getMovementValue()
                    && movementEntity.getMovementType().equalsIgnoreCase("Retiro")) {
                diferrenceVal = movementEntity.getMovementValue() - (+movementVo.getMovementValue());
                accountVo.setBalance(accountEntity.getBalance() - (diferrenceVal));
            }

            if (movementVo.getMovementValue() > 0 && movementVo.getMovementValue() > movementEntity.getMovementValue()
                    && movementEntity.getMovementType().equalsIgnoreCase("Retiro")) {
                diferrenceVal = (movementVo.getMovementValue() + movementEntity.getMovementValue()) + movementVo.getMovementValue();
                accountVo.setBalance(accountEntity.getBalance() + diferrenceVal);
            }

            if (movementVo.getMovementValue() < 0 && movementVo.getMovementValue() < movementEntity.getMovementValue()
                    && movementEntity.getMovementType().equalsIgnoreCase("Deposito")) {
                diferrenceVal = accountEntity.getBalance() - movementEntity.getMovementValue();
                accountVo.setBalance(diferrenceVal + (movementVo.getMovementValue()));

            }

            accountVo.setAccountNumber(accountEntity.getAccountNumber());
            iAccountService.updateAccount(accountVo);


            movementEntity.setMovementType(movementVo.getMovementType());
            movementEntity.setMovementValue(movementVo.getMovementValue());
            movementEntity.setDateMovement(movementVo.getDateMovement());
            movementEntity.setStatus(true);
            movementEntity.setLastModifiedDate(new Date());
            movementEntity.setModificationUser("admin");
            iMovementRepository.update(movementEntity);

        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ResponseAccountVo> findMovementsAccountsByDate(String identifyNumber,Date datefrom, Date dateTo) {

        ClientEntity clientEntity = iClientRepository.findByCi(identifyNumber);
        if (clientEntity != null) {
            List<ResponseAccountVo> responseAccountList = iAccountRepository.findAccountsByClient(identifyNumber);
            responseAccountList.forEach(account -> {
                List<MovementByAccountVo>  movementByAccountVos = iMovementRepository.findMovements(account.getAccountID(), datefrom, dateTo);
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
}
