import pandas as pd
import numpy as np
from sklearn.preprocessing import MinMaxScaler
from sklearn.metrics import mean_squared_error
import keras
from keras.models import Sequential
from keras.layers import Dense, LSTM
import tensorflow as tf
from http.server import BaseHTTPRequestHandler, HTTPServer


def get_data(data, look_back):
    data_x, data_y = [], []
    for i in range(len(data) - look_back - 1):
        data_x.append(data[i:(i + look_back), 0])
        data_y.append(data[i + look_back, 0])
    return np.array(data_x), np.array(data_y)


if __name__ == "__main__":
    train_set = pd.read_csv('/quotes/train.csv')
    df = train_set['<LAST>']
    df = np.array(df).reshape(-1, 1)
    scaler = MinMaxScaler()
    train = scaler.fit_transform(df)
    x_train, y_train = get_data(train, 1)

    test_set = pd.read_csv('/quotes/test.csv')
    df = test_set['<LAST>']
    df = np.array(df).reshape(-1, 1)
    test = scaler.fit_transform(df)
    x_test, y_test = get_data(test, 1)

    x_train = x_train.reshape(x_train.shape[0], x_train.shape[1], 1)
    x_test = x_test.reshape(x_test.shape[0], x_test.shape[1], 1)

    n_features = x_train.shape[1]
    model = Sequential()
    model.add(LSTM(100, activation='relu', input_shape=(1, 1)))
    model.add(Dense(n_features))

    model.compile(optimizer='adam', loss='mse')
    model.fit(x_train, y_train, epochs=5, batch_size=1)

    y_pred = model.predict(x_test)
    y_pred = scaler.inverse_transform(y_pred)

    y_test = np.array(y_test).reshape(-1, 1)
    y_test = scaler.inverse_transform(y_test)

    print("MSE: %s", mean_squared_error(y_test, y_pred))

    with open('next_day.csv', 'w') as f:
        for tick in y_pred:
            f.write('{0}\n'.format(tick[0]))
