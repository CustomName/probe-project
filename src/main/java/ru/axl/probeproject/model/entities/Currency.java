package ru.axl.probeproject.model.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="CURRENCIES")
public class Currency {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_CURRENCY")
    private UUID idCurrency;

    @Column(name="CODE", length=3, nullable=false, unique=true)
    private String code;

    @Column(name="ISO", length=3, nullable=false, unique=true)
    private String iso;

    @Column(name="NAME", length=30, nullable=false)
    private String name;

}
