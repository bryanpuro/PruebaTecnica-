package com.demo.prueba.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class VO for Clients.
 *
 * @author bcarrillo.
 * @version 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientVo extends BaseAuditableVo {

    private PersonVo personVo;

    private String clientID;

    private String personId;

    private String password;
}
