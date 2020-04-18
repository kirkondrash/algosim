![sequence-diagram](algosim-sequence.png "Взаимодействие сервисов")
***
TODO:
- synchronized hashmaps во всех сервисах
- profit&loss по всем валютам с приведением к базовой по курсу закрытия дня
- ??? нужна ли ??? оптимизация executeOrders - [concurrent?]map of sets:
  - для openOrderList - ключ stoploss/makeprofit цены, значение сеты ордеров им соответствующие;
  - для waitOrderList - ключ opening цены, значение сеты ордеров им соответствующие;
  - соответственно затем стрим + фильтр ключи между текущим и предыдущим уровнем цены
Optional TODO:
- поправить swagger-ui чтоьы выстраивались корректные запросы с учетом envoy-прокси
- BUG: запросы работают либо без Accept, либо с "Accept: application/json, application/octet-stream"; c "Accept: application/octet-stream, application/json" НЕ работают, разобраться почему
- перейти на единый формат сериализации (gson/jackson) в клиентах и серверах, сделать единый артефакт с моделями
***
Структура репо:
- docker-compose.yml
- algosim-sequnce.{png,txt} - последовательность взаимодействия сервисов 
- framework/ - непосредственно код логики трейдинга
- clients/ - сгенерированные  и допиленные клиенты ко всем серверам
- {gateway,repo,compiler,executor}/ - сервера-компоненты платформы
- models/ - модели и переиспользуемые классы всех серверов
- quotes/ - тестовый набор данных 
***
Запуск платформы в контейнерах:
1. `mvn clean package -P docker`
2. `docker-compose up`
3. Несолько compiler-worker'ов - `docker-compose up --scale compiler=3`. В таком случае на хостнеймах компонент платформы должен стоять loab-balancer.
***
Запуск jar-артефактов:
1. `mvn clean package -P boot` 
***
Аргументы артефактов (параметры):

Oбщие:
+ `--server.port=8080`
+ `--server.address=0.0.0.0`
+ `--repoUrl=http://repo:8080/api`

В compiler'e:
+ `--pathToFramework=/framework`

В executor'e:
+ `-pathToQuotes=/quotes`

В gateway'e:
+ `--compilerUrl=http://compiler:8080/api`
+ `--executorUrl=http://executor:8080/api`

