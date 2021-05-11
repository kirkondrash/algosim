FROM python:alpine
WORKDIR app
COPY . .
EXPOSE 9000
CMD python3 /app/random_boolean.py
HEALTHCHECK --interval=5s --timeout=1s --start-period=30s CMD wget -q --spider localhost:9000