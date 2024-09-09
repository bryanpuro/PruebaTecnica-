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
public class ResponseClientVo {

    private Boolean status;

    private String completeName;

    private String gender;

    private Integer age;

    private String identifyNumber;

    private String address;

    private String phone;
}
