package ru.axl.probeproject.utils;

import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@UtilityClass
public class Utils {

    public static OffsetDateTime getNowOffsetDateTime(){
        return OffsetDateTime.now(ZoneId.of("Europe/Moscow"));
    }

}
