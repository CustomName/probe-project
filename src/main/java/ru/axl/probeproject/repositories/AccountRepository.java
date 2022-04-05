package ru.axl.probeproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.axl.probeproject.model.entities.Account;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select acc from Account acc where acc.client.idClient = :idClient")
    List<Account> findAllByIdClient(@Param("idClient")UUID idClient);

    @Query("select acc from Account acc where acc.accountStatus.name = :statusName and acc.openingDate <= :openingDate")
    List<Account> findAllInStatusOpeningAndOpeningDateBefore(@Param("statusName")String statusName,
                                                             @Param("openingDate") OffsetDateTime openingDate);

}
