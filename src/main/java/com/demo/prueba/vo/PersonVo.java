package com.demo.prueba.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

/**
 * Class VO for Persons.
 *
 * @author bcarrillo.
 * @version 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonVo extends BaseAuditableVo {

    private String personId;

    private String completeName;

    private String gender;

    private Integer age;

    private String identifyNumber;

    private String address;

    private String phone;


}
