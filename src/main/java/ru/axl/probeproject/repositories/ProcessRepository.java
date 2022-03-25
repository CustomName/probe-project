package ru.axl.probeproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.axl.probeproject.model.entities.Process;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {

    @Query("select p from Process p where p.client.idClient = ?1")
    List<Process> findAllByIdClient(UUID idClient);

}
