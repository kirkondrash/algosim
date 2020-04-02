![sequence-diagram](configs/algosim-sequence.png "Взаимодействие сервисов")
***
TODO:
- host и basepath сервисов как параметры/файлы пропертей
- поправить swagger-ui чтоьы выстраивались корректные запросы с учетом envoy-прокси
- единый источник quotes в framework: receiveTick: timestamp, пара, цена
- BUG: запросы работают либо без Accept, либо с "Accept: application/json, application/octet-stream"; c "Accept: application/octet-stream, application/json" НЕ работают, разобраться почему
- перейти на единый формат сериализации (gson/jackson) в клиентах и серверах, сделать единый артефакт с моделями
- для compiler/executor родительский worker-класс с настройками ThreadPool
- concurrent maps для хранения данных
***
Структура репо:
- Dockerfile-base, Dockerfile-dist-{jdk,jre}, start-service.sh - для сборки базовых образов. В base (он с jdk,maven, исходным кодом клиентов) собирается артефакт сервиса, в dist (тонкий образ, alpine+envoy+(jre|jdk)) он запускается.
- docker-compose.yml
- configs/:
  - algosim-sequnce.{png,txt} - последовательность взаимодействия сервисов 
  - envoy-configs/ - конфигурационные файлы маршрутизации
  - openapi-specs/ - openapi спецификации всех сервисов
  - generator-configs/ - конфиги для генерации заглушек серверов/клиентов сервисов. По факту всё уже сгенерировано и лежит в соответствующих папках
- framework/ - непосредственно код логики трейдинга
- clients/ - сгенерированные  и допиленные клиенты ко всем сервисам
- {gateway,repo,compiler,executor}/ - сгенерированные сервисы
***
Порядок действий (из корня проекта):
1. Сначала собрать algosim-base (там все клиенты), algosim-dist-{jre,jdk} (envoy+jre для финальных контейнеров). Почему-то не кэшируетсяв мультистейдже, приходится отдельно, потом разберусь. 
  - `docker build -t algosim-base -f Dockerfile-base .`
  - `docker build -t algosim-dist-jre -f Dockerfile-dist-jre .`
  - `docker build -t algosim-dist-jdk -f Dockerfile-dist-jdk .`
2. Потом уже собрать  и запустить сервисы по `docker-compose up --build`. Для того чтобы запустить сборку без использования закэшированных слоев (если вам кажется, что что-то не подхватывается при сборке) - `docker-compose build --no-cache`.
3. Несолько compiler-worker'ов - `docker-compose up --scale compiler=3`
