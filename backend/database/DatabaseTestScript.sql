--Users -- Password is "password" for both entries
BEGIN TRANSACTION; 
INSERT INTO users (id, username, password, salt, role) VALUES (2, 'jake', 'Isv0ZInVPcF2ZDKcrhltSw==', 'Gid+gJga/PggeuFhxYsqBvRXStwRrw/qt22elyZQwbkPMMSo2bcbc8VE26f9JZOucXH96J+S8UW6I6Paxj3GwHIRSBBM/y7qIK+nIxIIKk6Rfdbx+rpDnWq8arwdxKuMo1R+/RhLnSwNraAm8hQPaGxzYQ1V7Sj6ZXZBY8mynpE=', user);
INSERT INTO users (id, username, password, salt, role) VALUES (3, 'kevin', 'EKF4ZepfTgVP/qjktyKhxQ==', '52N/YS8FilCKROxiKMfIpbb++4Fma9LLEmrESvwPl2zYcRfVoBey3n73hrtd2DtcNF/5he+LjFboc5IqfPKrqSWBZceQjFAbFxuWGFUnS/z7ZQqLUTIdkwOPuuDdf8s/aWa5/aBB/KDHYp0Gm/khQFq0MKiHbLjZ2qH8fgByrq0=', user);
INSERT INTO users (id, username, password, salt, role) VALUES (4, 'Atticusf', 'atticusisatticus', 'awdawdwaawdadwawdwawad', user);
INSERT INTO users (id, username, password, salt, role) VALUES (5, 'Kanye', 'iamagod', 'jfklfdkjldajfajdfdfkjdak', user);
INSERT INTO users (id, username, password, salt, role) VALUES (6, 'Kevin', 'kevin', 'fhdfdkajf;kadf', user);
INSERT INTO users (id, username, password, salt, role) VALUES (7, 'Batman', 'martha', 'eruqpruqoeqer', user);
INSERT INTO users (id, username, password, salt, role) VALUES (8, 'Superman', 'martha', 'fdafadklfj;kladjfa', user);
INSERT INTO users (id, username, password, salt, role) VALUES (9, 'Spiderman', 'martha', 'dfeiaufpoequf', user);
--Pending Games
INSERT INTO game (game_id, organizer_id, name, number_of_players, length_in_days) VALUES (1, 1, 'Game1', 7, 5);
INSERT INTO game (game_id, organizer_id, name, number_of_players, length_in_days) VALUES (2, 5, 'Game2', 5, 20);
INSERT INTO game (game_id, organizer_id, name, number_of_players, length_in_days) VALUES (3, 2, 'Game3', 10, 45);
INSERT INTO game (game_id, organizer_id, name, number_of_players, length_in_days) VALUES (4, 3, 'Game4', 20, 90);
INSERT INTO game (game_id, organizer_id, name, number_of_players, length_in_days) VALUES (5, 4, 'Game1', 7, 5);
INSERT INTO game (game_id, organizer_id, name, number_of_players, length_in_days) VALUES (6, 6, 'Game2', 5, 20);
INSERT INTO game (game_id, organizer_id, name, number_of_players, length_in_days) VALUES (7, 1, 'Game3', 10, 45);
INSERT INTO game (game_id, organizer_id, name, number_of_players, length_in_days) VALUES (8, 1, 'Game4', 20, 90);
--Active Games
UPDATE game SET start_date = '2020-04-25', end_date = '2020-05-02' WHERE game.game_id = 1;
UPDATE game SET start_date = '2020-04-18', end_date = '2020-04-28' WHERE game.game_id = 3;

--Portfolios
INSERT INTO portfolio (portfolio_id, user_id, game_id, cash) VALUES (1, 1, 1, 120000);
INSERT INTO portfolio (portfolio_id, user_id, game_id, cash) VALUES (2, 2, 2, 90000);
INSERT INTO portfolio (portfolio_id, user_id, game_id, cash) VALUES (3, 3, 3, 200000);
INSERT INTO portfolio (portfolio_id, user_id, game_id, cash) VALUES (4, 4, 1, 100000);

--Trades - Buy
INSERT INTO trade (trade_id, portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 1, 'BUY', 'AAPL', 100, 1000, 0, '2020-04-23');
INSERT INTO trade (trade_id, portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (2, 1, 'BUY', 'IBM', 100, 1000, 0, '2020-04-23');
INSERT INTO trade (trade_id, portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (3, 1, 'BUY', 'DIS', 100, 1000, 0, '2020-04-23');
INSERT INTO trade (trade_id, portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (4, 1, 'BUY', 'NASA', 100, 1000, 0, '2020-04-23');
INSERT INTO trade (trade_id, portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (5, 1, 'BUY', 'AAPL', 100, 1000, 0, '2020-04-23');

--Trades - Sell

INSERT INTO trade (trade_id, portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (7, 1, 'SELL', 'IBM', 10, 110, 0, '2020-04-24');
INSERT INTO trade (trade_id, portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (8, 1, 'SELL', 'DIS', 10, 110, 0, '2020-04-24');
INSERT INTO trade (trade_id, portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (9, 1, 'SELL', 'NASA', 10, 110, 0, '2020-04-24');

INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (1, 1, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (2, 2, false);



END TRANSACTION;


