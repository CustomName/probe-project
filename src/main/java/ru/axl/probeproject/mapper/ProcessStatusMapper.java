package ru.axl.probeproject.mapper;

import org.mapstruct.Mapper;
import ru.axl.probeproject.model.ProcessStatusResponse;
import ru.axl.probeproject.model.entities.ProcessStatus;

import java.util.List;

@Mapper(uses = {UuidMapper.class})
public interface ProcessStatusMapper {

    ProcessStatusResponse toProcessStatusResponse(ProcessStatus processStatus);

    List<ProcessStatusResponse> toProcessStatusResponseList(List<ProcessStatus> processStatus);

}
