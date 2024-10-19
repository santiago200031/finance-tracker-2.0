CREATE TABLE finance.deka_finance
(
    id              VARCHAR(255),
    price           DECIMAL(10, 2),
    priceChange     DECIMAL(10, 2),
    displayName     VARCHAR(255),
    timeLastUpdated TIMESTAMP,
    differencePrice DECIMAL(10, 2),
    localDateChange DATE
);

