package ru.axl.probeproject.mapper;

import org.mapstruct.Mapper;

import java.util.UUID;

import static java.util.Objects.isNull;

@Mapper
public interface UuidMapper {

    default String asString(UUID uuid) {
        if (isNull(uuid)) {
            return null;
        }

        return uuid.toString();
    }

}
