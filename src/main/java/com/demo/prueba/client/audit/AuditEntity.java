package com.demo.prueba.client.audit;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditEntity {

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "REGISTER_USER")
    private String registerUser;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "MODIFICATION_USER")
    private String modificationUser;

    @Column(name = "LASTMODIFIED_DATE")
    private Date lastModifiedDate;

}
