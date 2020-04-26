--Users -- Password is "password" for both entries
INSERT INTO users (username, password, salt, role) VALUES ('jake', 'Isv0ZInVPcF2ZDKcrhltSw==', 'Gid+gJga/PggeuFhxYsqBvRXStwRrw/qt22elyZQwbkPMMSo2bcbc8VE26f9JZOucXH96J+S8UW6I6Paxj3GwHIRSBBM/y7qIK+nIxIIKk6Rfdbx+rpDnWq8arwdxKuMo1R+/RhLnSwNraAm8hQPaGxzYQ1V7Sj6ZXZBY8mynpE=', user)
INSERT INTO users (username, password, salt, role) VALUES ('kevin', 'EKF4ZepfTgVP/qjktyKhxQ==', '52N/YS8FilCKROxiKMfIpbb++4Fma9LLEmrESvwPl2zYcRfVoBey3n73hrtd2DtcNF/5he+LjFboc5IqfPKrqSWBZceQjFAbFxuWGFUnS/z7ZQqLUTIdkwOPuuDdf8s/aWa5/aBB/KDHYp0Gm/khQFq0MKiHbLjZ2qH8fgByrq0=', user)

--Pending Games
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (1, 'Game1', 7, 5);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (1, 'Game2', 5, 20);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (1, 'Game3', 10, 45);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (1, 'Game4', 20, 90);

--Active Games
UPDATE game SET start_date = '2020-04-25', end_date = '2020-05-02' WHERE game.game_id = 1;
UPDATE game SET start_date = '2020-04-18', end_date = '2020-04-28' WHERE game.game_id = 3;

--Portfolios
INSERT INTO portfolio (user_id, game_id, total_value) VALUES (1, 1, 120000);
INSERT INTO portfolio (user_id, game_id, total_value) VALUES (1, 2, 90000);
INSERT INTO portfolio (user_id, game_id, total_value) VALUES (1, 3, 200000);
INSERT INTO portfolio (user_id, game_id, total_value) VALUES (1, 4, -10000);

--Trades - Buy
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'BUY', 'AAPL', 100, 1000, 0, '2020-04-25')


