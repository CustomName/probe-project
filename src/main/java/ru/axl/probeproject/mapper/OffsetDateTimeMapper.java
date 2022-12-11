package ru.axl.probeproject.mapper;

import org.mapstruct.Mapper;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

@Mapper
public interface OffsetDateTimeMapper {

    default String asString(OffsetDateTime offsetDateTime) {
        if (isNull(offsetDateTime)) {
            return "";
        }

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        return formatter.format(offsetDateTime);
    }

}
