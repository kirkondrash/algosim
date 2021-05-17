FROM tensorflow/tensorflow:latest-jupyter
COPY USDRUB_TRAIN.csv /quotes/train.csv
COPY USDRUB_TEST.csv /quotes/test.csv
COPY train_model.py .
RUN pip3 install pandas sklearn && \
python3 train_model.py

FROM python:alpine
WORKDIR /app
COPY --from=0 /tf/next_day.csv .
COPY run_model.py .
EXPOSE 9001
CMD python3 run_model.py
HEALTHCHECK --interval=5s --timeout=1s --start-period=30s CMD nc -z localhost 9001