package ru.axl.probeproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.axl.probeproject.model.entities.ProcessStatus;

import java.util.Optional;

/**
 * Репозиторий статусов процесса.
 */
@Repository
public interface ProcessStatusRepository extends JpaRepository<ProcessStatus, Long> {

    Optional<ProcessStatus> findByName(String name);

}
