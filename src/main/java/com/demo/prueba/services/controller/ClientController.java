package com.demo.prueba.services.controller;


import com.demo.prueba.client.service.IClientService;
import com.demo.prueba.vo.ClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/clientes")
public class ClientController {


    @Autowired
    IClientService iClientService;
    /**
     * WS to create Client.
     *
     * @param personVo ClientVO
     * @return boolean
     */
    @PostMapping("/crearCliente")
    public ResponseEntity<String> createClient(@RequestBody ClientVo personVo) {
        try {
            iClientService.createClient(personVo);
            return ResponseEntity.ok("Cliente creado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    /**
     * WS to search a list client.
     *
     * @return list ClientVO.
     */
    @GetMapping("/findAllClientes")
    public ResponseEntity<?> findAllClient() {
        try {
            return ResponseEntity.ok(iClientService.findallClients());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    /**
     * WS to update Client.
     *
     * @param clientVo ClientVO
     * @return boolean
     */
    @PatchMapping("/updateCliente")
    public ResponseEntity<String> updateClient(@RequestBody ClientVo clientVo) {
        try {
            iClientService.updateClient(clientVo);
            return ResponseEntity.ok("Cliente actualizado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    /**
     * WS to detelte a  client.
     *
     * @return list ClientVO.
     */
    @DeleteMapping("/deleteClient/{identifyNumber}")
    public ResponseEntity<String> deleteClient(@PathVariable String identifyNumber) {
        //try {
            iClientService.deleteClient(identifyNumber);
            return ResponseEntity.ok("Cliente eliminado");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
//        }
    }

    /**
     * WS to update status a client.
     *.
     */
    @PatchMapping("/updateStatusClient/")
    public ResponseEntity<String> updateStatusClient(@RequestParam String identifyNumber, @RequestParam Boolean status) {
        try {
            iClientService.updateStatusClient(identifyNumber,status);
            return ResponseEntity.ok("Estado del cliente actualizado");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

}
