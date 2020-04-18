![sequence-diagram](algosim-sequence.png "Взаимодействие сервисов")
***
TODO:
- Spring Security
- synchronized hashmaps во всех сервисах
- перейти на единый формат сериализации (gson/jackson) в клиентах и серверах, сделать единый артефакт с моделями
- profit&loss по всем валютам с приведением к базовой по курсу закрытия дня
- ??? нужна ли ??? оптимизация executeOrders - [concurrent?]map of sets:
  - для openOrderList - ключ stoploss/makeprofit цены, значение сеты ордеров им соответствующие;
  - для waitOrderList - ключ opening цены, значение сеты ордеров им соответствующие;
  - соответственно затем стрим + фильтр ключи между текущим и предыдущим уровнем цены
Optional TODO:
- поправить swagger-ui чтоьы выстраивались корректные запросы с учетом envoy-прокси
- BUG: запросы работают либо без Accept, либо с "Accept: application/json, application/octet-stream"; c "Accept: application/octet-stream, application/json" НЕ работают, разобраться почему
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
  + `curl -X POST "http://localhost:8080/api/algoCode" -H "accept: application/json" -H "Content-Type: multipart/form-data" -F "code=@framework/src/main/java/TradingAlgorithmImpl.java"`
  + `curl "http://localhost:8080/api/getTop" | jq`
3. `docker-compose down -v`
***
Несолько compiler- и executor- worker'ов:
1. `mvn clean package -P docker`
2. `docker-compose -f docker-compose-multiworker.yml up --scale compiler=2 --scale executor=2`. 
  + В таком случае на хостнеймах компонент платформы должен стоять loab-balancer. В docker-compose его роль исполняет образ envoy. Envoy обчеспечивает роутинг на все компоненты через соответствующий префикс.
  + `curl -X POST "http://localhost:8000/api/algoCode" -H "accept: application/json" -H "Content-Type: multipart/form-data" -F "code=@framework/src/main/java/TradingAlgorithmImpl.java"`
  + `curl "http://localhost:8000/api/getTop" | jq`
  + `curl "http://localhost:8000/repo/api/algoStatus/b03b6c6d-a945-4986-970a-43a0c899ab09" `
4. `docker-compose -f docker-compose-multiworker.yml down -v`
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

