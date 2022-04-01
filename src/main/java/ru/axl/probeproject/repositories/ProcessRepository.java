package ru.axl.probeproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.axl.probeproject.model.entities.Process;
import ru.axl.probeproject.model.entities.ProcessStatus;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {

    @Query("select p from Process p where p.client.idClient = :idClient")
    List<Process> findAllByIdClient(@Param("idClient") UUID idClient);

    @Query("select p from Process p where p.client.idClient = :idClient" +
            " and p.processStatus.name in :statuses")
    List<Process> findAllByIdClientInStatuses(@Param("idClient") UUID idClient,
                                              @Param("statuses") Set<String> statuses);

    @Query("select p from Process p where p.processStatus = :status and p.lastUpdateDate <= :lastUpdateDate")
    List<Process> findAllWithStatusAndLastUpdateDateBefore(@Param("status") ProcessStatus status,
                                                           @Param("lastUpdateDate") OffsetDateTime lastUpdateDate);

}
