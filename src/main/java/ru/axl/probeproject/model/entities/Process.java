package ru.axl.probeproject.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="process")
public class Process {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_process")
    private UUID idProcess;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

}
