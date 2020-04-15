Like this:

- client:

`docker run --rm -v /Users/kirkondrash/Desktop/algosim:/local openapitools/openapi-generator-cli generate -i /local/repo/repo-OAS.yaml -o /local/new-repo-client -g java -c /local/generator-configs/repo-client.yaml`

- server:

`docker run --rm -v /Users/kirkondrash/Desktop/algosim:/local openapitools/openapi-generator-cli generate -i /local/repo/repo-OAS.yaml -o /local/new-repo-server -g spring -c /local/generator-configs/repo-server.yaml`
