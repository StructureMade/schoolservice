package de.structuremade.ms.schoolservice.database.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(schema = "structuremade", indexes = {
        @Index(name = "id_permsid", columnList = "id", unique = true)
})
public class Permissions {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String name;

    @Column
    private boolean masterPerms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMasterPerms() {
        return masterPerms;
    }

    public void setMasterPerms(boolean masterPerms) {
        this.masterPerms = masterPerms;
    }
}
