package ru.axl.probeproject.model.enums;

/**
 * Статус счета
 */
public enum AccountStatusEnum {

    /**
     * Счет зарезервирован
     */
    RESERVED,
    /**
     * Счет на открытии
     */
    OPENING,
    /**
     * Счет отклонен банком
     */
    REJECTED,
    /**
     * Счет открыт
     */
    OPENED;

}
