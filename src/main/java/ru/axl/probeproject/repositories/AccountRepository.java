package ru.axl.probeproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.axl.probeproject.model.entities.Account;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select acc from Account acc where acc.client.idClient = ?1")
    List<Account> findAllByIdClient(UUID idClient);

}
