package ru.axl.probeproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.axl.probeproject.model.ProcessResponse;
import ru.axl.probeproject.model.entities.Process;

import java.util.List;

@Mapper(uses = {UuidMapper.class, OffsetDateTimeMapper.class})
public interface ProcessMapper {

    @Mapping(target = "processStatus", source = "processStatus.name")
    ProcessResponse toProcessResponse(Process process);

    List<ProcessResponse> toProcessResponseList(List<Process> process);

}
