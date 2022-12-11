package ru.axl.probeproject.utils;

import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Random;

/**
 * Общий утилитный класс.
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@UtilityClass
public class Utils {

    private static final Random RAND = new Random();

    /**
     * Метод возвращает текущую дату в таймзоне <strong>Europe/Moscow</strong>.
     *
     * @return Дата
     */
    public static OffsetDateTime getNowOffsetDateTime() {
        return OffsetDateTime.now(ZoneId.of("Europe/Moscow"));
    }

    /**
     * Метод возвращает случайное число в заданном диапазоне.
     *
     * @param left Левая граница диапазона
     * @param right Правая граница диапазона
     * @return Случайное число
     */
    public static int randNumberWithBounds(final int left, final int right) {
        return RAND.nextInt(right - left) + left;
    }

}
