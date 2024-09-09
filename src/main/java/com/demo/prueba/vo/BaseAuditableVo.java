package com.demo.prueba.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;


/**
 * Class BaseAuditableVo.
 *
 * @author bcarrillo.
 * @version 1.0.0
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseAuditableVo {

    private Boolean status;

    private String registerUser;

    private Date createDdate;

    private String modificationUser;

    private Date lastModifiedDate;
}
