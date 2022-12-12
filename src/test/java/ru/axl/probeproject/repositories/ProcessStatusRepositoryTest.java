package ru.axl.probeproject.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.axl.probeproject.model.entities.ProcessStatus;
import ru.axl.probeproject.repositories.base.BaseRepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

class ProcessStatusRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ProcessStatusRepository processStatusRepo;

    @Test
    void shouldFindByName() {
        final String processStatusName = "NEW";
        final ProcessStatus processStatus = processStatusRepo.findByName(processStatusName).orElseThrow();

        assertThat(processStatus).isNotNull();
        assertThat(processStatus.getName()).isEqualTo(processStatusName);
        assertThat(processStatus.getDescription())
                .isEqualTo("Новый процесс. Промежуточный статус. Идет комплаенс проверка (занимает 10-20 секунд)");
    }

}