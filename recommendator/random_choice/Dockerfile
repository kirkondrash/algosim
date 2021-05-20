FROM python:alpine
WORKDIR app
COPY random_choice.py .
EXPOSE 9000
CMD python3 /app/random_choice.py
HEALTHCHECK --interval=5s --timeout=1s --start-period=30s CMD nc -z localhost 9000