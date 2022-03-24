package ru.axl.probeproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.axl.probeproject.model.entities.Process;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {
}
