INSERT INTO PROCESS_STATUSES (ID_PROCESS_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'NEW', 'Новый процесс');
INSERT INTO PROCESS_STATUSES (ID_PROCESS_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'COMPLIANCE_SUCCESS', 'Комплаенс проверка успешно выполнена');
INSERT INTO PROCESS_STATUSES (ID_PROCESS_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'COMPLIANCE_ERROR', 'Комплаенс проверка завершилось ошибкой');
INSERT INTO PROCESS_STATUSES (ID_PROCESS_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'OPENING', 'Открытие счета');
INSERT INTO PROCESS_STATUSES (ID_PROCESS_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'END_SUCCESS', 'Процесс завершен успешно');
INSERT INTO PROCESS_STATUSES (ID_PROCESS_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'END_ERROR', 'Процесс завершен с ошибкой');

INSERT INTO CLIENTS (ID_CLIENT, FIO, INN) VALUES (random_uuid(), 'Пупов Пуп Пупович', '111111111111');
INSERT INTO CLIENTS (ID_CLIENT, FIO, INN) VALUES (random_uuid(), 'Пупова Пупа Пуповна', '111111111112');
INSERT INTO CLIENTS (ID_CLIENT, FIO, INN) VALUES (random_uuid(), 'Иванов Иван Иванович', '111111111113');