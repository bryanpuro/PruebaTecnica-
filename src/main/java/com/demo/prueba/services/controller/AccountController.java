package com.demo.prueba.services.controller;

import com.demo.prueba.client.service.IAccountService;
import com.demo.prueba.vo.AccountVo;
import com.demo.prueba.vo.ClientVo;
import com.demo.prueba.vo.ResponseAccountVo;
import com.demo.prueba.vo.ResponseClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")

public class AccountController {

    @Autowired
    IAccountService iAccountService;

    /**
     * WS to create account.
     *
     * @param accountVo AccountVo.
     * @return boolean
     */
    @PostMapping("/crearCuenta")
    public ResponseEntity<String> createClient(@RequestBody AccountVo accountVo) {
        try {
            iAccountService.createAccount(accountVo);
            return ResponseEntity.ok("Cuenta creada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    /**
     * WS to update account.
     *
     * @param accountVo ClientVO
     * @return boolean
     */
    @PatchMapping("/updateAccount")
    public ResponseEntity<String> updateAccount(@RequestBody AccountVo accountVo) {
        try {
            iAccountService.updateAccount(accountVo);
            return ResponseEntity.ok("Cuenta actualizada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    /**
     * WS to find accounts by client.
     *
     * @return ResponseEntity with a List of ResponseAccountVo.
     */
    @GetMapping("/findAccountsByClient/")
    public ResponseEntity<?> findAccountsByClient(@RequestParam String identifyNumber) {
        try {
            List<ResponseAccountVo> accounts = iAccountService.findAccountsByClient(identifyNumber);
            return ResponseEntity.ok(accounts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    /**
     * WS to update status a client.
     *.
     */
    @PatchMapping("/updateAccountStatus/")
    public ResponseEntity<String> updateAccountStatus(@RequestParam String accountNumber, @RequestParam Boolean status) {
        try {
            iAccountService.updateAccountStatus(accountNumber,status);
            return ResponseEntity.ok("Estado de la cuenta actualizado.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}
