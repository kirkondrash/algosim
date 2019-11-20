Like this:

- client:

`docker run --rm -v /Users/kirkondrash/Desktop/algosim:/local openapitools/openapi-generator-cli generate -i /local/configs/openapi-specs/repo-OAS.yaml -o /local/repo/client -g java -c /local/configs/generator-configs/repo-client.yaml`

- server:

`docker run --rm -v /Users/kirkondrash/Desktop/algosim:/local openapitools/openapi-generator-cli generate -i /local/configs/openapi-specs/repo-OAS.yaml -o /local/repo/server -g spring -c /local/configs/generator-configs/repo-server.yaml`
