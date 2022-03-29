package ru.axl.probeproject.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="CLIENTS")
public class Client {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_CLIENT")
    private UUID idClient;

    @Column(name="FIO", length=100, nullable=false)
    private String fio;

    @Column(name="INN", length=12, nullable=false, unique=true)
    private String inn;

}
