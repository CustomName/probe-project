package ru.axl.probeproject.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Сущность статуса счета
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ACCOUNT_STATUSES")
public class AccountStatus {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_ACCOUNT_STATUS")
    private UUID idAccountStatus;

    /**
     * Имя
     */
    @Column(name="NAME", length=30, nullable=false)
    private String name;

    /**
     * Описание
     */
    @Column(name="DESCRIPTION", length=200, nullable=false)
    private String description;

}
