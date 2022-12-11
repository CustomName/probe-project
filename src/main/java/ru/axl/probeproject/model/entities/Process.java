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
 * Сущность процесса.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROCESSES")
public class Process {

    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_PROCESS")
    private UUID idProcess;

    /**
     * Клиент.
     */
    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private Client client;

    /**
     * Дата начала процесса.
     */
    @Column(name = "START_DATE")
    private OffsetDateTime startDate;

    /**
     * Дата последнего обновления.
     */
    @Column(name = "LAST_UPDATE_DATE")
    private OffsetDateTime lastUpdateDate;

    /**
     * Статус процесса.
     */
    @ManyToOne
    @JoinColumn(name = "ID_PROCESS_STATUS")
    private ProcessStatus processStatus;

}
