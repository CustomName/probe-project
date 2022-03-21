# Getting Started

### Probe Project
Проект для тестового тестирования rest сервиса.

### Запуск проекта
В проекте используется openapi (swagger) для генерирования интерфейсов
и dto. Необходимые файлы будут доступны в следующих packages:

* ru.axl.probeproject.api
* ru.axl.probeproject.model

после выполнения команды мавен:

`mvn compile`

В проекте используется база данных H2, после запуска к ней можно обратиться так:

http://localhost:8080/h2-console