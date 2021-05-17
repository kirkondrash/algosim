FROM python:alpine
WORKDIR app
COPY sliding_averages.py .
EXPOSE 9002
CMD python3 /app/sliding_averages.py
HEALTHCHECK --interval=5s --timeout=1s --start-period=30s CMD nc -z localhost 9002