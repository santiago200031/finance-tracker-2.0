CREATE SEQUENCE deka_finance_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE finance.deka_finance
(
    id              BIGINT,
    price           DECIMAL(10, 2),
    priceChange     DECIMAL(10, 2),
    displayName     VARCHAR(255),
    timeLastUpdated TIMESTAMP,
    differencePrice DECIMAL(10, 2),
    localDateChange DATE
);

INSERT INTO finance.deka_finance
(id, price, priceChange, displayName, timeLastUpdated, differencePrice, localDateChange)
VALUES (nextval('deka_finance_seq'), 287.44, 287.44, 'Deka-GlobalChampions CF', '2023-11-24T10:00:12.4808006Z', 287.44, '2023-11-24'),
       (nextval('deka_finance_seq'), 285.41, -2.03, 'Deka-GlobalChampions CF', '2023-11-29T20:00:11.9802851Z', -1.08, '2023-11-29'),
       (nextval('deka_finance_seq'), 286.53, 1.12, 'Deka-GlobalChampions CF', '2023-12-01T12:01:49.6531467Z', 1.25, '2023-12-01'),
       (nextval('deka_finance_seq'), 288.3, 1.77, 'Deka-GlobalChampions CF', '2023-12-04T20:00:13.0713596Z', 1.14, '2023-12-04'),
       (nextval('deka_finance_seq'), 286.53, -1.77, 'Deka-GlobalChampions CF', '2023-12-06T10:00:41.1558401Z', -1.77, '2023-12-06'),
       (nextval('deka_finance_seq'), 288.98, 2.45, 'Deka-GlobalChampions CF', '2023-12-07T10:00:02.3459106Z', 2.45, '2023-12-07'),
       (nextval('deka_finance_seq'), 290.92, 1.94, 'Deka-GlobalChampions CF', '2023-12-13T10:00:39.4609738Z', -0.22, '2023-12-13'),
       (nextval('deka_finance_seq'), 291.29, 0.37, 'Deka-GlobalChampions CF', '2023-12-14T10:00:02.0576565Z', 0.37, '2023-12-14'),
       (nextval('deka_finance_seq'), 292.72, 1.43, 'Deka-GlobalChampions CF', '2023-12-19T12:01:18.6388256Z', 1.31, '2023-12-19'),
       (nextval('deka_finance_seq'), 293.89, 1.17, 'Deka-GlobalChampions CF', '2023-12-20T12:01:11.8938156Z', 1.17, '2023-12-20'),
       (nextval('deka_finance_seq'), 295.13, 1.24, 'Deka-GlobalChampions CF', '2023-12-21T10:00:06.8078891Z', 1.24, '2023-12-21'),
       (nextval('deka_finance_seq'), 293.22, -1.91, 'Deka-GlobalChampions CF', '2023-12-22T23:00:30.196392Z', 0.41, '2023-12-22'),
       (nextval('deka_finance_seq'), 293.62, 0.4, 'Deka-GlobalChampions CF', '2023-12-30T09:00:20.3764079Z', -0.57, '2023-12-30'),
       (nextval('deka_finance_seq'), 294.81, 1.19, 'Deka-GlobalChampions CF', '2023-12-31T02:01:43.2925275Z', 1.19, '2023-12-31'),
       (nextval('deka_finance_seq'), 294.17, -0.64, 'Deka-GlobalChampions CF', '2024-01-04T12:00:05.4707138Z', -0.47, '2024-01-04'),
       (nextval('deka_finance_seq'), 293.18, -0.99, 'Deka-GlobalChampions CF', '2024-01-05T20:00:56.3062034Z', -0.37, '2024-01-05'),
       (nextval('deka_finance_seq'), 292.65, -0.53, 'Deka-GlobalChampions CF', '2024-01-09T12:00:15.8541873Z', -0.53, '2024-01-09'),
       (nextval('deka_finance_seq'), 295.37, 2.72, 'Deka-GlobalChampions CF', '2024-01-10T11:05:08.827018Z', 2.72, '2024-01-10'),
       (nextval('deka_finance_seq'), 296.91, 1.54, 'Deka-GlobalChampions CF', '2024-01-12T10:00:05.0137095Z', 1.43, '2024-01-12'),
       (nextval('deka_finance_seq'), 297.36, 0.45, 'Deka-GlobalChampions CF', '2024-01-13T10:01:12.1713013Z', 0.45, '2024-01-13'),
       (nextval('deka_finance_seq'), 298.24, 0.88, 'Deka-GlobalChampions CF', '2024-01-16T12:00:46.5400039Z', 0.88, '2024-01-16'),
       (nextval('deka_finance_seq'), 300.14, 1.9, 'Deka-GlobalChampions CF', '2024-01-23T10:00:01.0821241Z', 2.08, '2024-01-23'),
       (nextval('deka_finance_seq'), 300.74, 0.6, 'Deka-GlobalChampions CF', '2024-01-24T06:00:33.417708Z', 0.6, '2024-01-24'),
       (nextval('deka_finance_seq'), 303.82, 3.08, 'Deka-GlobalChampions CF', '2024-01-25T20:00:03.986293Z', 3.08, '2024-01-25'),
       (nextval('deka_finance_seq'), 304.8, 0.98, 'Deka-GlobalChampions CF', '2024-01-26T12:01:51.8455649Z', 0.98, '2024-01-26'),
       (nextval('deka_finance_seq'), 307.64, 2.84, 'Deka-GlobalChampions CF', '2024-01-27T10:01:22.9714398Z', 2.84, '2024-01-27'),
       (nextval('deka_finance_seq'), 308.35, 0.71, 'Deka-GlobalChampions CF', '2024-01-30T10:00:53.930598Z', 0.71, '2024-01-30'),
       (nextval('deka_finance_seq'), 309.82, 1.47, 'Deka-GlobalChampions CF', '2024-01-31T09:01:01.6507059Z', 1.47, '2024-01-31'),
       (nextval('deka_finance_seq'), 305.53, -4.29, 'Deka-GlobalChampions CF', '2024-02-02T18:00:27.0235337Z', -3.43, '2024-02-02'),
       (nextval('deka_finance_seq'), 306.59, 1.06, 'Deka-GlobalChampions CF', '2024-02-03T18:00:29.4463846Z', 1.06, '2024-02-03'),
       (nextval('deka_finance_seq'), 313.31, 6.72, 'Deka-GlobalChampions CF', '2024-02-06T10:00:29.9207868Z', 6.72, '2024-02-06'),
       (nextval('deka_finance_seq'), 315.4, 2.09, 'Deka-GlobalChampions CF', '2024-02-07T12:00:26.8022343Z', 2.09, '2024-02-07'),
       (nextval('deka_finance_seq'), 317.82, 1.57, 'Deka-GlobalChampions CF', '2024-02-14T12:00:53.1150295Z', -0.16, '2024-02-14'),
       (nextval('deka_finance_seq'), 319.42, 1.6, 'Deka-GlobalChampions CF', '2024-02-17T10:00:38.5834896Z', 0.64, '2024-02-17'),
       (nextval('deka_finance_seq'), 318.0, -1.42, 'Deka-GlobalChampions CF', '2024-02-20T10:00:05.9357393Z', -1.42, '2024-02-20'),
       (nextval('deka_finance_seq'), 311.05, -7.07, 'Deka-GlobalChampions CF', '2024-02-22T12:00:02.9677735Z', -7.07, '2024-02-22'),
       (nextval('deka_finance_seq'), 317.3, 6.25, 'Deka-GlobalChampions CF', '2024-02-24T10:00:53.5432895Z', 6.79, '2024-02-24'),
       (nextval('deka_finance_seq'), 316.6, -0.7, 'Deka-GlobalChampions CF', '2024-02-26T20:00:23.7051317Z', -0.7, '2024-02-26'),
       (nextval('deka_finance_seq'), 319.98, 3.38, 'Deka-GlobalChampions CF', '2024-03-14T10:00:27.4400987Z', 3.48, '2024-03-14'),
       (nextval('deka_finance_seq'), 320.5, 0.52, 'Deka-GlobalChampions CF', '2024-03-20T10:00:08.0447198Z', 2.91, '2024-03-20'),
       (nextval('deka_finance_seq'), 321.49, 0.99, 'Deka-GlobalChampions CF', '2024-03-21T10:02:46.2714573Z', 0.99, '2024-03-21'),
       (nextval('deka_finance_seq'), 324.98, 3.49, 'Deka-GlobalChampions CF', '2024-03-23T10:00:12.3706583Z', 1.53, '2024-03-23'),
       (nextval('deka_finance_seq'), 324.15, -0.83, 'Deka-GlobalChampions CF', '2024-03-27T10:00:05.0737184Z', -0.87, '2024-03-27'),
       (nextval('deka_finance_seq'), 331.49, 7.34, 'Deka-GlobalChampions CF', '2024-05-11T04:00:42.0193521Z', 0.5, '2024-05-11'),
       (nextval('deka_finance_seq'), 335.23, 3.74, 'Deka-GlobalChampions CF', '2024-05-18T10:00:21.7011845Z', 0.44, '2024-05-18'),
       (nextval('deka_finance_seq'), 334.03, -1.2, 'Deka-GlobalChampions CF', '2024-05-27T21:00:39.0727952Z', -1.62, '2024-05-27'),
       (nextval('deka_finance_seq'), 341.09, 7.06, 'Deka-GlobalChampions CF', '2024-06-12T12:00:13.8884648Z', 0.21, '2024-06-12'),
       (nextval('deka_finance_seq'), 350.95, 9.86, 'Deka-GlobalChampions CF', '2024-06-27T10:00:41.7320497Z', 3.51, '2024-06-27'),
       (nextval('deka_finance_seq'), 345.87, -5.08, 'Deka-GlobalChampions CF', '2024-07-20T10:01:23.1899959Z', -2.55, '2024-07-20'),
       (nextval('deka_finance_seq'), 351.84, 5.97, 'Deka-GlobalChampions CF', '2024-09-27T12:00:15.7967748Z', 3.39, '2024-09-27'),
       (nextval('deka_finance_seq'), 357.39, 5.55, 'Deka-GlobalChampions CF', '2024-10-02T20:00:10.2779165Z', 1.57, '2024-10-02'),
       (nextval('deka_finance_seq'), 361.78, 4.39, 'Deka-GlobalChampions CF', '2024-10-11T10:00:22.090042Z', 2.28, '2024-10-11'),
       (nextval('deka_finance_seq'), 361.13, -0.65, 'Deka-GlobalChampions CF', '2024-10-17T10:01:27.0487763Z', -3.25, '2024-10-17'),
       (nextval('deka_finance_seq'), 362.54, 1.41, 'Deka-GlobalChampions CF', '2024-10-18T15:00:03.2889645Z', 1.41, '2024-10-18');