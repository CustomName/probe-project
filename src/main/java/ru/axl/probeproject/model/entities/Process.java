package ru.axl.probeproject.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
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

    @Column(name = "start_date")
    private OffsetDateTime startDate;

    @Column(name = "last_update_date")
    private OffsetDateTime lastUpdateDate;

}
