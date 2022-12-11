package ru.axl.probeproject.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность валюты.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CURRENCIES")
public class Currency {

    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_CURRENCY")
    private UUID idCurrency;

    /**
     * Код.
     */
    @Column(name = "CODE", length = 3, nullable = false, unique = true)
    private String code;

    /**
     * ISO.
     */
    @Column(name = "ISO", length = 3, nullable = false, unique = true)
    private String iso;

    /**
     * Имя.
     */
    @Column(name = "NAME", length = 30, nullable = false)
    private String name;

}
