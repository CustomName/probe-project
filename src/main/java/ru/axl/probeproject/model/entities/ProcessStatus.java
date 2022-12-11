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
 * Сущность процесса.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROCESS_STATUSES")
public class ProcessStatus {

    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_PROCESS_STATUS")
    private UUID idProcessStatus;

    /**
     * Имя.
     */
    @Column(name = "NAME", length = 30, nullable = false)
    private String name;

    /**
     * Описание.
     */
    @Column(name = "DESCRIPTION", length = 200, nullable = false)
    private String description;

}
