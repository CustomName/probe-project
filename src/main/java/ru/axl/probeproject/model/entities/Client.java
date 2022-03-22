package ru.axl.probeproject.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @Column(name="fio", length=100, nullable=false)
    private String fio;

    @Column(name="inn", length=12, nullable=false, unique=true)
    private String inn;

}
