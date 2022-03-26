package ru.axl.probeproject.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="CLIENTS")
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID_CLIENT")
    private UUID idClient;

    @Column(name="FIO", length=100, nullable=false)
    private String fio;

    @Column(name="INN", length=12, nullable=false, unique=true)
    private String inn;

}
