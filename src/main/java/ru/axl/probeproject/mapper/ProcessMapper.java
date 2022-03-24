package ru.axl.probeproject.mapper;

import org.mapstruct.Mapper;
import ru.axl.probeproject.model.ProcessResponse;
import ru.axl.probeproject.model.entities.Process;

import java.util.List;

@Mapper(uses = {UuidMapper.class})
public interface ProcessMapper {

    ProcessResponse toProcessResponse(Process process);

    List<ProcessResponse> toProcessResponseList(List<Process> process);

}
