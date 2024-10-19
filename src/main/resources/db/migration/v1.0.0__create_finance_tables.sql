CREATE DATABASE finances;

CREATE TABLE IF NOT EXISTS finance.deka_finance
(
    id              VARCHAR(255),
    price           DECIMAL(10, 2),
    priceChange     DECIMAL(10, 2),
    displayName     VARCHAR(255),
    timeLastUpdated TIMESTAMP,
    differencePrice DECIMAL(10, 2),
    localDateChange DATE
);

CREATE TABLE IF NOT EXISTS finance.btc_finance
(
    id              VARCHAR(255),
    price           DECIMAL(10, 2),
    priceChange     DECIMAL(10, 2),
    displayName     VARCHAR(255),
    timeLastUpdated TIMESTAMP,
    differencePrice DECIMAL(10, 2),
    localDateChange DATE
);
