package ru.axl.probeproject.model.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="PROCESS_STATUSES")
public class ProcessStatus {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_PROCESS_STATUS")
    private UUID idProcessStatus;

    @Column(name="NAME", length=30, nullable=false)
    private String name;

    @Column(name="DESCRIPTION", length=200, nullable=false)
    private String description;

}
