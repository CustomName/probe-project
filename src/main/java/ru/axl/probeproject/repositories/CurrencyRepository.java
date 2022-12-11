package ru.axl.probeproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.axl.probeproject.model.entities.Currency;

import java.util.Optional;

/**
 * Репозиторий справочника валют.
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Optional<Currency> findByCode(String code);

}
