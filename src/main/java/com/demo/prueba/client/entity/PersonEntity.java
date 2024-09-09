package com.demo.prueba.client.entity;

import com.demo.prueba.client.audit.AuditEntity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Persons Entity.
 *
 * @author bcarrillo.
 * @version 1.0.0
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PERSONS")
public class PersonEntity  extends AuditEntity {

    /**
     * Serial generated by default.
     */
    private static final long serialVersionUID = 3656121202396441803L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "PERSON_ID")
    private String personId;

    @Column(name = "COMPLETE_NAME")
    private String completeName;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "IDENTIFY_NUMBER")
    private String identifyNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;


    /**
     * Get entity id.
     */
    public String getId() {
        return personId;
    }
}