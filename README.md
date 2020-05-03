![sequence-diagram](algosim-sequence.png "Взаимодействие сервисов")
***
TODO:
- как разобраться с maven-зависимостями?
- перейти на единый формат сериализации (gson/jackson) в клиентах и серверах, сделать единый артефакт с моделями
- profit&loss по всем валютам с приведением к базовой по курсу закрытия дня
- ордера - постгрес
- Учёт спреда 
- Принудительное закрытие ордеров по текущей цене
- Компиляции/исполнение алгоритма c коллбэками и закрытиям по таймауту
- Индикаторы
- пояснить что пока что без кредитных плечей итд
Optional TODO:
- configurationProperties на каждый сервис
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
  + Обращение к компонентам только через захардкоженно переданный как параметр хост и порт.
  + `curl -X POST "http://localhost:8080/api/algoCode" -H "accept: application/json" -H "Content-Type: multipart/form-data" -F "code=@framework/src/main/java/TradingAlgorithmImpl.java" -F "userId=kirko" -F "userAlgoName=right" -u "user:password"`
  + `curl "http://localhost:8080/api/getTop" -u "user:password" | jq`
3. `docker-compose down -v`
***
Несолько compiler- и executor- worker'ов:
1. `mvn clean package -P docker`
2. `docker-compose -f docker-compose-multiworker.yml up --scale compiler=2 --scale executor=2`. 
  + В таком случае на хостнеймах компонент платформы должен стоять loab-balancer. В docker-compose его роль исполняет образ envoy. Envoy обчеспечивает роутинг на все компоненты через соответствующий префикс.
  + `curl -X POST "http://localhost:8000/api/algoCode" -H "accept: application/json" -H "Content-Type: multipart/form-data" -F "code=@framework/src/main/java/TradingAlgorithmImpl.java" -F "userId=kirko" -F "userAlgoName=right" -u "user:password"`
  + `curl "http://localhost:8000/api/getTop" -u "user:password" | jq`
  + `curl "http://localhost:8000/repo/api/algoStatus/kirko_right" -u "user:password"`
4. `docker-compose -f docker-compose-multiworker.yml down -v`
***
Запуск jar-артефактов:
1. `mvn clean package -P boot` 
2. 
   + `java -Xverify:none -jar repo/target/repo-server-1.1.0-SNAPSHOT.jar --server.port=8081`
   + `java -Xverify:none -jar compiler/target/compiler-server-1.1.0-SNAPSHOT.jar --server.port=8082 --framework.project.path=./framework --user.username=user --user.password=password`
   + `java -Xverify:none -jar executor/target/executor-server-1.1.0-SNAPSHOT.jar --server.port=8083 --framework.quotes.path=./quotes --user.username=user --user.password=password`
   + `java -Xverify:none -jar gateway/target/gateway-server-1.1.0-SNAPSHOT.jar --server.port=8080 --user.username=user --user.password=password`
***
Аргументы артефактов (параметры):

Oбщие:
+ `--server.port=8080`
+ `--server.address=0.0.0.0`
+ `--user.username=user`
+ `--user.password=password`

В compiler'e:
+ ` --framework.project.path=./framework`
+ `--repo.basePath=http://repo:8080/api`

В executor'e:
+ `--framework.quotes.path=./quotes`
+ `--repo.basePath=http://repo:8080/api`

В gateway'e:
+ `--repo.basePath=http://repo:8080/api`
+ `--compiler.basePath=http://compiler:8080/api`
+ `--executor.basePath=http://executor:8080/api`
