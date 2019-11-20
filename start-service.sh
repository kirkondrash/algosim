#!/bin/sh
java -jar /app/app.jar &
envoy -c /etc/envoy-config.yaml --service-cluster ${SERVICE_NAME}
