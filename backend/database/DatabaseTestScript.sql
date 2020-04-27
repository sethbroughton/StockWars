
BEGIN TRANSACTION; 

--Password: "password"
INSERT INTO users (id, username, password, salt, role) VALUES (2, 'jake', 'Isv0ZInVPcF2ZDKcrhltSw==', 'Gid+gJga/PggeuFhxYsqBvRXStwRrw/qt22elyZQwbkPMMSo2bcbc8VE26f9JZOucXH96J+S8UW6I6Paxj3GwHIRSBBM/y7qIK+nIxIIKk6Rfdbx+rpDnWq8arwdxKuMo1R+/RhLnSwNraAm8hQPaGxzYQ1V7Sj6ZXZBY8mynpE=', user);
INSERT INTO users (id, username, password, salt, role) VALUES (3, 'kevin', 'EKF4ZepfTgVP/qjktyKhxQ==', '52N/YS8FilCKROxiKMfIpbb++4Fma9LLEmrESvwPl2zYcRfVoBey3n73hrtd2DtcNF/5he+LjFboc5IqfPKrqSWBZceQjFAbFxuWGFUnS/z7ZQqLUTIdkwOPuuDdf8s/aWa5/aBB/KDHYp0Gm/khQFq0MKiHbLjZ2qH8fgByrq0=', user);
INSERT INTO users (id, username, password, salt, role) VALUES (4, 'Atticusf', '86mOqPF3Vi9JKSJGLEcmvw==', 'hO0HZz8xOMr504hYNkWdApTeS/gKm+cP/iXsx9SqUrpunFebywgY0d3krRLJAs+ShgHXfn1agHT+/h71DLVXjbJg0mQpEEB/JYdeR7esRd0Ffh7VG7qV20vKppz1EP6VWtEc/WIbrcmqX6EKX4OE/Cuy+6vUrmsjYMAWz30k3do=', user);
INSERT INTO users (id, username, password, salt, role) VALUES (5, 'Kanye', 'Z9xARadavGi0in3VVEGVdA==', 'LYtDjKFnT+i506T7B4jZKJ8kObLL0KD/oF5cJR1GOvhhyFYtR6L5jCoQ2FsA90lFiL1NV/3IGcUkZhbFCuvELUxtDHD0ZV05Y69dCO9jI9Q1lB3Kvof9qEIrrlJXdUv887LSRt32Pzv4b+mqx0HWGPF2v7LMj4iD+PCbzb+AJFw=', user);

--Password: "martha"
INSERT INTO users (id, username, password, salt, role) VALUES (6, 'Batman', 'DoaAGHuSo1yMhhld7cSNkg==', '9D0ueFsVc8kDbXEov+gIRQFLEM8iyJuqICVieu37fyKnMXTEUeGcjM6ESiP6A5wmDj8nrcv0REqTi0GcGP/F3TaTg88jApBkVU8z/8+OufGk8TGXlOntMqW913ihTOfnocWvt1XM57IWdO5U1+iEC85aQ+kKBKq3GPh4xaQwAF4=', user);
INSERT INTO users (id, username, password, salt, role) VALUES (7, 'Superman', 'H1LuKvgRrfAEO8xoyvZYCA==', 'sxPeq9q9MqUKJN5cRFSjXs0cTYJko7TACdktIt/diXaGYa6QOvgoLXjnPjijEhGuqsTXfJcF/UFnQvK60AMeJdLL7mMyU0apswNlRPjcPQPi6+JohtCDFEO7P3OjiW7hsrL3Kod5Zw3HPfYXqJfIyKTYLJfpt2Zccfoj7i8RiZ0=', user);
INSERT INTO users (id, username, password, salt, role) VALUES (8, 'Spiderman', '5BQBtsq0j8dCPdR++ZcX/w==', 'gnDISFp7CRsHLdywkzVqQf/MGMK7MmYaN7SK9aNMKC0CvAX6j67wNEZ8FoQ/baqgM8frcdOlILdzWBfROVNU5zkcvzNUuUEqiAdcKV5Q4LL6R9sgVSSEY0UDtC/U97GfzZ3/uzodNdMLiuReJ0nbXoFHJ99MaRGO7Ucgpu15p9g=', user);

--Pending Games
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (1, 'Game1', 7, 5);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (5, 'Game2', 5, 20);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (2, 'Game3', 10, 45);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (3, 'Game4', 20, 90);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (4, 'Game1', 7, 5);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (6, 'Game2', 5, 20);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (1, 'Game3', 10, 45);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (1, 'Game4', 20, 90);

--Active Games
UPDATE game SET start_date = '2020-04-25', end_date = '2020-05-02' WHERE game.game_id = 1;
UPDATE game SET start_date = '2020-04-18', end_date = '2020-04-28' WHERE game.game_id = 3;

--Portfolios
INSERT INTO portfolio (portfolio_id, user_id, game_id, cash) VALUES (1, 1, 1, 120000);
INSERT INTO portfolio (portfolio_id, user_id, game_id, cash) VALUES (2, 2, 2, 90000);
INSERT INTO portfolio (portfolio_id, user_id, game_id, cash) VALUES (3, 3, 3, 200000);
INSERT INTO portfolio (portfolio_id, user_id, game_id, cash) VALUES (4, 4, 1, 100000);

--Trades - Buy
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'BUY', 'AAPL', 100, 1000, 0, '2020-04-23');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'BUY', 'IBM', 100, 1000, 0, '2020-04-23');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'BUY', 'F', 100, 1000, 0, '2020-04-23');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'BUY', 'IBM', 100, 1000, 0, '2020-04-23');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'BUY', 'F', 100, 1000, 0, '2020-04-23');

--Trades - Sell
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'SELL', 'IBM', 10, 110, 0, '2020-04-24');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'SELL', 'F', 10, 110, 0, '2020-04-24');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'SELL', 'AAPL', 10, 110, 0, '2020-04-24');

INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (1, 1, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (2, 2, false);


COMMIT;
END TRANSACTION;


