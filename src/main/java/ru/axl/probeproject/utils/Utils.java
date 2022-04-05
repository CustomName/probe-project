package ru.axl.probeproject.utils;

import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Random;

@UtilityClass
public class Utils {

    private static final Random rand = new Random();

    public static OffsetDateTime getNowOffsetDateTime(){
        return OffsetDateTime.now(ZoneId.of("Europe/Moscow"));
    }

    public static int randNumberWithBounds(int left, int right){
        return rand.nextInt(right - left) + left;
    }

}
