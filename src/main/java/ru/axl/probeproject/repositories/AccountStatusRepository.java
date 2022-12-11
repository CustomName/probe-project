package ru.axl.probeproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.axl.probeproject.model.entities.AccountStatus;

import java.util.Optional;

/**
 * Репозиторий статусов счета.
 */
@Repository
public interface AccountStatusRepository extends JpaRepository<AccountStatus, Long> {

    Optional<AccountStatus> findByName(String name);

}
