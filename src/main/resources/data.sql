--PROCESS_STATUSES
INSERT INTO PROCESS_STATUSES (ID_PROCESS_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'NEW', 'Новый процесс. Идет комплаенс проверка (занимает 10-20 секунд)');
INSERT INTO PROCESS_STATUSES (ID_PROCESS_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'COMPLIANCE_SUCCESS', 'Комплаенс проверка успешно выполнена. Можно резервировать счета');
INSERT INTO PROCESS_STATUSES (ID_PROCESS_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'COMPLIANCE_ERROR', 'Комплаенс проверка завершилось ошибкой. Попробуйте начать новый процесс');
INSERT INTO PROCESS_STATUSES (ID_PROCESS_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'ACCOUNT_PROCESSING', 'Идет открытие зарезервированных счетов');
INSERT INTO PROCESS_STATUSES (ID_PROCESS_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'DONE', 'Процесс завершен');

--ACCOUNT_STATUSES
INSERT INTO ACCOUNT_STATUSES (ID_ACCOUNT_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'RESERVED', 'Счет зарезервирован');
INSERT INTO ACCOUNT_STATUSES (ID_ACCOUNT_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'OPENING', 'Счет на открытии (занимает 10-20 секунд)');
INSERT INTO ACCOUNT_STATUSES (ID_ACCOUNT_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'REJECTED', 'Счет отклонен банком. На данный момент доступно только открытие рублевых счетов');
INSERT INTO ACCOUNT_STATUSES (ID_ACCOUNT_STATUS, NAME, DESCRIPTION)
    VALUES (random_uuid(), 'OPENED', 'Счет открыт');

--CURRENCIES
INSERT INTO CURRENCIES (ID_CURRENCY, CODE, ISO, NAME)
    VALUES (random_uuid(), '810', 'RUB', 'Российский рубль');
INSERT INTO CURRENCIES (ID_CURRENCY, CODE, ISO, NAME)
    VALUES (random_uuid(), '840', 'USD', 'Доллар США');
INSERT INTO CURRENCIES (ID_CURRENCY, CODE, ISO, NAME)
    VALUES (random_uuid(), '978', 'EUR', 'Евро');

--CLIENTS
--Тестовые клиенты
INSERT INTO CLIENTS (ID_CLIENT, FIO, INN) VALUES (random_uuid(), 'Пупов Пуп Пупович', '111111111111');
INSERT INTO CLIENTS (ID_CLIENT, FIO, INN) VALUES (random_uuid(), 'Пупова Пупа Пуповна', '111111111112');