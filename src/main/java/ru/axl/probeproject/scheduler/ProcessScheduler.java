package ru.axl.probeproject.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.axl.probeproject.exceptions.ApiException;
import ru.axl.probeproject.model.entities.Process;
import ru.axl.probeproject.model.entities.ProcessStatus;
import ru.axl.probeproject.model.enums.ProcessStatusEnum;
import ru.axl.probeproject.repositories.ProcessRepository;
import ru.axl.probeproject.repositories.ProcessStatusRepository;
import ru.axl.probeproject.services.ComplianceService;

import javax.transaction.Transactional;
import java.util.List;

import static ru.axl.probeproject.exceptions.ApiError.PROCESS_STATUS_NOT_FOUND;
import static ru.axl.probeproject.model.enums.ProcessStatusEnum.*;
import static ru.axl.probeproject.utils.Utils.getNowOffsetDateTime;

@Slf4j
@Component
@AllArgsConstructor
public class ProcessScheduler {

    private final ProcessRepository processRepo;
    private final ProcessStatusRepository processStatusRepo;
    private final ComplianceService complianceService;

    @Transactional
    @Scheduled(fixedDelayString = "${scheduler-config.fixed-delay}",
            initialDelayString = "${scheduler-config.initial-delay}")
    public void scheduleProcessStatus(){
        ProcessStatus newStatus = processStatusRepo.findByName(NEW.name()).orElseThrow(() ->
                new ApiException(PROCESS_STATUS_NOT_FOUND,
                        String.format("Не найден статус процесса с name = '%s'", NEW.name())));

        List<Process> processes = processRepo.findAllWithStatusAndLastUpdateDateBefore(newStatus,
                getNowOffsetDateTime().minusSeconds(10));

        processes.forEach(process -> {
            if(complianceService.checkClient(process.getClient())){
                setStatus(process, COMPLIANCE_SUCCESS);
            } else{
                setStatus(process, COMPLIANCE_ERROR);
            }
        });
    }

    private void setStatus(Process process, ProcessStatusEnum processStatusEnum){
        ProcessStatus complianceSuccess = processStatusRepo.findByName(processStatusEnum.name()).orElseThrow(() ->
                new ApiException(PROCESS_STATUS_NOT_FOUND,
                        String.format("Не найден статус процесса с name = '%s'", processStatusEnum.name())));
        process.setProcessStatus(complianceSuccess);
        process.setLastUpdateDate(getNowOffsetDateTime());
        processRepo.save(process);

        log.info("Обновлен статус процесса {} в {}", process.getIdProcess(), processStatusEnum.name());
    }

}
