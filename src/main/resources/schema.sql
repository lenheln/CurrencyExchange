CREATE SCHEMA IF NOT EXISTS CurrencyConvertation;

-- Создание таблицы пользователей
CREATE TABLE IF NOT EXISTS USERS(
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    login   TEXT NOT NULL
);

-- Создание таблицы конвертаций
CREATE TABLE IF NOT EXISTS EXCHANGE(
    id                  INTEGER     PRIMARY KEY AUTO_INCREMENT,
    input_currency      ENUM('GBP','EUR','USD', 'RUB','BYN','CHF','CNY','JPY')        NOT NULL,
    output_currency     ENUM('GBP','EUR','USD', 'RUB','BYN','CHF','CNY','JPY')        NOT NULL,
    amount              INTEGER     NOT NULL,
    user_id             INT     NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS(id)
);