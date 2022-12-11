package ru.axl.probeproject.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Сущность счета.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNTS")
public class Account {

    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_ACCOUNT")
    private UUID idAccount;

    /**
     * Номер.
     */
    @Column(name = "NUMBER", length = 20, nullable = false)
    private String number;

    /**
     * Валюта.
     */
    @ManyToOne
    @JoinColumn(name = "ID_CURRENCY")
    private Currency currency;

    /**
     * Клиент.
     */
    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private Client client;

    /**
     * Дата резервирования.
     */
    @Column(name = "RESERVATION_DATE")
    private OffsetDateTime reservationDate;

    /**
     * Дата начала процесса открытия счета.
     */
    @Column(name = "OPENING_DATE")
    private OffsetDateTime openingDate;

    /**
     * Дата открытия счета.
     */
    @Column(name = "OPEN_DATE")
    private OffsetDateTime openDate;

    /**
     * Статус счета.
     */
    @ManyToOne
    @JoinColumn(name = "ID_ACCOUNT_STATUS")
    private AccountStatus accountStatus;

}
