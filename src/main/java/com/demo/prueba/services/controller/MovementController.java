package com.demo.prueba.services.controller;


import com.demo.prueba.client.service.IMovementService;
import com.demo.prueba.vo.MovementVo;
import com.demo.prueba.vo.ResponseAccountVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/movimientos")
public class MovementController {

    @Autowired
    IMovementService iMovementService;

    /**
     * Crear un cliente
     *
     * @param movementVo
     * @return
     */
    @PostMapping("/crearMovimiento")
    public ResponseEntity<String> createClient(@RequestBody MovementVo movementVo) {
        try {
            iMovementService.createMovement(movementVo);
            return ResponseEntity.ok("Se registro el movimiento");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }


    /**
     * WS para actualizar un movimiento
     *
     * @param movementVo
     * @return
     */
    @PatchMapping("/updateMovement")
    public ResponseEntity<String> updateMovement(@RequestBody MovementVo movementVo) {
        try {
            iMovementService.updateMovement(movementVo);
            return ResponseEntity.ok("Se actualizo el movimiento");
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
    @GetMapping("/findAccountsByDate/")
    public ResponseEntity<?> findAccountsByDate(@RequestParam String identifyNumber, @RequestParam Date datefrom, @RequestParam Date dateTo) {
        try {
            List<ResponseAccountVo> accounts = iMovementService.findMovementsAccountsByDate(identifyNumber, datefrom, dateTo);
            return ResponseEntity.ok(accounts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }

    }
}
