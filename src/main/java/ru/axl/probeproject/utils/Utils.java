package ru.axl.probeproject.utils;

import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Random;

/**
 * Общий утилитный класс
 */
@UtilityClass
public class Utils {

    private static final Random rand = new Random();

    /**
     * Метод возвращает текущую дату в таймзоне <strong>Europe/Moscow</strong>
     *
     * @return Дата
     */
    public static OffsetDateTime getNowOffsetDateTime(){
        return OffsetDateTime.now(ZoneId.of("Europe/Moscow"));
    }

    /**
     * Метод возвращает случайное число в заданном диапазоне
     *
     * @param left Левая граница диапазона
     * @param right Правая граница диапазона
     * @return Случайное число
     */
    public static int randNumberWithBounds(int left, int right){
        return rand.nextInt(right - left) + left;
    }

}
