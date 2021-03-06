        
--===================================================
--I have set the game up for us to run the program as Batman for demo
--This way we have a populated leaderboard, and games
--The game with a populated portfolio is **The Real Gordan Gekko**
--user: jake is 2nd most populated with data
--===================================================

--Password: "password"
INSERT INTO users (username, password, salt, role) VALUES ('jake', 'Isv0ZInVPcF2ZDKcrhltSw==', 'Gid+gJga/PggeuFhxYsqBvRXStwRrw/qt22elyZQwbkPMMSo2bcbc8VE26f9JZOucXH96J+S8UW6I6Paxj3GwHIRSBBM/y7qIK+nIxIIKk6Rfdbx+rpDnWq8arwdxKuMo1R+/RhLnSwNraAm8hQPaGxzYQ1V7Sj6ZXZBY8mynpE=', user);
INSERT INTO users (username, password, salt, role) VALUES ('kevin', 'EKF4ZepfTgVP/qjktyKhxQ==', '52N/YS8FilCKROxiKMfIpbb++4Fma9LLEmrESvwPl2zYcRfVoBey3n73hrtd2DtcNF/5he+LjFboc5IqfPKrqSWBZceQjFAbFxuWGFUnS/z7ZQqLUTIdkwOPuuDdf8s/aWa5/aBB/KDHYp0Gm/khQFq0MKiHbLjZ2qH8fgByrq0=', user);
INSERT INTO users (username, password, salt, role) VALUES ('Atticusf', '86mOqPF3Vi9JKSJGLEcmvw==', 'hO0HZz8xOMr504hYNkWdApTeS/gKm+cP/iXsx9SqUrpunFebywgY0d3krRLJAs+ShgHXfn1agHT+/h71DLVXjbJg0mQpEEB/JYdeR7esRd0Ffh7VG7qV20vKppz1EP6VWtEc/WIbrcmqX6EKX4OE/Cuy+6vUrmsjYMAWz30k3do=', user);
INSERT INTO users (username, password, salt, role) VALUES ('Kanye', 'Z9xARadavGi0in3VVEGVdA==', 'LYtDjKFnT+i506T7B4jZKJ8kObLL0KD/oF5cJR1GOvhhyFYtR6L5jCoQ2FsA90lFiL1NV/3IGcUkZhbFCuvELUxtDHD0ZV05Y69dCO9jI9Q1lB3Kvof9qEIrrlJXdUv887LSRt32Pzv4b+mqx0HWGPF2v7LMj4iD+PCbzb+AJFw=', user);

--Password: "martha"
INSERT INTO users (username, password, salt, role) VALUES ('Batman', 'DoaAGHuSo1yMhhld7cSNkg==', '9D0ueFsVc8kDbXEov+gIRQFLEM8iyJuqICVieu37fyKnMXTEUeGcjM6ESiP6A5wmDj8nrcv0REqTi0GcGP/F3TaTg88jApBkVU8z/8+OufGk8TGXlOntMqW913ihTOfnocWvt1XM57IWdO5U1+iEC85aQ+kKBKq3GPh4xaQwAF4=', user);
INSERT INTO users (username, password, salt, role) VALUES ('Superman', 'H1LuKvgRrfAEO8xoyvZYCA==', 'sxPeq9q9MqUKJN5cRFSjXs0cTYJko7TACdktIt/diXaGYa6QOvgoLXjnPjijEhGuqsTXfJcF/UFnQvK60AMeJdLL7mMyU0apswNlRPjcPQPi6+JohtCDFEO7P3OjiW7hsrL3Kod5Zw3HPfYXqJfIyKTYLJfpt2Zccfoj7i8RiZ0=', user);
INSERT INTO users (username, password, salt, role) VALUES ('Spiderman', '5BQBtsq0j8dCPdR++ZcX/w==', 'gnDISFp7CRsHLdywkzVqQf/MGMK7MmYaN7SK9aNMKC0CvAX6j67wNEZ8FoQ/baqgM8frcdOlILdzWBfROVNU5zkcvzNUuUEqiAdcKV5Q4LL6R9sgVSSEY0UDtC/U97GfzZ3/uzodNdMLiuReJ0nbXoFHJ99MaRGO7Ucgpu15p9g=', user);

--Games
INSERT INTO game (organizer_id, name, number_of_players, length_in_days, start_date, end_date, public_game) VALUES (3, 'Glengarry Glen Ross', 4, 15, '2020-04-02', '2020-04-17', true); --Jeff's name
INSERT INTO game (organizer_id, name, number_of_players, length_in_days, start_date, end_date, public_game) VALUES (2, 'The Real Gordan Gekko', 4, 15, '2020-04-15', '2020-04-30', true); --Seth's name
INSERT INTO game (organizer_id, name, number_of_players, length_in_days, start_date, end_date, public_game) VALUES (2, 'Money Never Sleeps', 4, 30, '2020-04-22', '2020-05-22', true); --Jake's name
INSERT INTO game (organizer_id, winner_id, name, number_of_players, length_in_days, start_date, end_date, public_game) VALUES (4, 2, 'Game4', 10, 30, '2020-03-01', '2020-04-01', true);
INSERT INTO game (organizer_id, winner_id, name, number_of_players, length_in_days, public_game) VALUES (4, 2, 'Game1', 7, 5, true);
INSERT INTO game (organizer_id, winner_id, name, number_of_players, length_in_days, public_game) VALUES (6, 6, 'Game2', 5, 20, true);
INSERT INTO game (organizer_id, winner_id, name, number_of_players, length_in_days) VALUES (1, 6, 'Game3', 10, 45);
INSERT INTO game (organizer_id, winner_id, name, number_of_players, length_in_days) VALUES (1, 3, 'Game4', 20, 90);
INSERT INTO game (organizer_id, winner_id, name, number_of_players, length_in_days, start_date, end_date, public_game) VALUES (6, 6, 'Batman vs Superman', 2, 14, '2020-03-01', '2020-03-14', true);
INSERT INTO game (organizer_id, winner_id, name, number_of_players, length_in_days, start_date, end_date, public_game) VALUES (5, 8, 'Buy Low Sell High', 2, 7, '2020-03-01', '2020-03-07', true);
INSERT INTO game (organizer_id, winner_id, name, number_of_players, length_in_days) VALUES (1, 7, 'Game17', 20, 90);
INSERT INTO game (organizer_id, winner_id, name, number_of_players, length_in_days) VALUES (1, 6, 'Game18', 20, 90);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days, start_date, end_date, public_game) VALUES (2, 'Run time Terror', 2, 30, '2020-02-10', '2020-05-10', true); --Charles's name
INSERT INTO game (organizer_id, name, number_of_players, length_in_days, start_date, end_date, public_game) VALUES (6, 'Enron', 2, 182, '2020-01-10', '2020-06-10', true); -- Kevin's name

BEGIN TRANSACTION; 
--Portfolios
INSERT INTO portfolio (user_id, game_id, cash) VALUES (1, 1, 7290);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (2, 1, 3270);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (3, 1, 22700);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (4, 1, 16200);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (1, 2, 15700);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (2, 2, 70620);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (3, 2, 25278);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (4, 2, 59250);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (2, 10, 24250);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (3, 10, 33574);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (4, 10, 74549);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (4, 10, 125);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (6, 9, 50705);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (7, 9, 31630);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (8, 10, 55310);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (5, 10, 930);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (6, 14, 50705);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (6, 2, 50405);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (6, 1, 59705);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (6, 10, 70705);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (6, 3, 170000);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (6, 13, 90705);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (5, 13, 101905);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (2, 13, 71305);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (3, 13, 63105);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (5, 3, 90000);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (3, 2, 105000);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (7, 3, 99000);
INSERT INTO portfolio (user_id, game_id, cash) VALUES (4, 3, 109000);

--Trades - Buy
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'BUY', 'AAPL', 100, 1000, 0, '2020-04-23');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'BUY', 'IBM', 100, 1000, 0, '2020-04-23');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'BUY', 'F', 100, 1000, 0, '2020-04-23');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'BUY', 'IBM', 100, 1000, 0, '2020-04-23');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'BUY', 'F', 100, 1000, 0, '2020-04-23');
--Jake - Buy
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (2, 'BUY', 'UAL', 10, 310, 0, '2020-04-29');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (2, 'BUY', 'NCLH', 100, 1500, 0, '2020-04-29');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (2, 'BUY', 'ADS', 53, 5300, 0, '2020-04-29');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (2, 'BUY', 'PAYC', 10, 2070, 0, '2020-04-27');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (5, 'BUY', 'UAL', 10, 310, 0, '2020-04-22');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (5, 'BUY', 'NCLH', 100, 1500, 0, '2020-04-29');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (5, 'BUY', 'ADS', 53, 5300, 0, '2020-04-29');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (5, 'BUY', 'PAYC', 10, 2070, 0, '2020-04-23');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (6, 'BUY', 'UAL', 10, 310, 0, '2020-04-29');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (6, 'BUY', 'NCLH', 100, 1500, 0, '2020-04-26');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (6, 'BUY', 'HAS', 60, 5300, 0, '2020-04-29');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (6, 'BUY', 'AMT', 10, 2070, 0, '2020-04-27');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (6, 'BUY', 'ZM', 75, 2070, 0, '2020-04-27');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (6, 'BUY', 'AAL', 100, 2170, 0, '2020-04-25');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (7, 'BUY', 'UAL', 10, 310, 0, '2020-04-29');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (7, 'BUY', 'SJM', 100, 1500, 0, '2020-04-28');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (8, 'BUY', 'ADS', 53, 5300, 0, '2020-04-28');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (8, 'BUY', 'COTY', 10, 2070, 0, '2020-04-27');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (18, 'BUY', 'BLK', 10, 5070, 0, '2020-04-27');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (18, 'BUY', 'ZM', 50, 7100, 0, '2020-04-27');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (18, 'BUY', 'FB', 100, 20000, 0, '2020-04-27');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (18, 'BUY', 'WMT', 100, 12200, 0, '2020-04-27');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (18, 'BUY', 'AAPL', 100, 19000, 0, '2020-04-27');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (18, 'BUY', 'HD', 68, 15000, 0, '2020-04-27');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (19, 'BUY', 'NFLX', 7, 2800, 0, '2020-04-24');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (19, 'BUY', 'TSLA', 10, 7600, 0, '2020-04-19');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (19, 'BUY', 'AMZN', 2, 4800, 0, '2020-04-21');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (19, 'BUY', 'AMZN', 2, 4800, 0, '2020-04-21');


--Trades - Sell - # of shares sold must be negative
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'SELL', 'IBM', -10, 110, 0, '2020-04-24');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'SELL', 'F', -10, 110, 0, '2020-04-24');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (1, 'SELL', 'AAPL', -10, 110, 0, '2020-04-24');
--Jake - Sell - # of shares sold must be negative
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (2, 'SELL', 'PAYC', -10, 2670, 0, '2020-04-29');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (2, 'SELL', 'ADS', -53, 5000, 0, '2020-04-29');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (2, 'SELL', 'UAL', -10, 2070, 0, '2020-04-28');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (5, 'SELL', 'PAYC', -10, 3100, 0, '2020-04-28');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (5, 'SELL', 'ADS', -53, 3000, 0, '2020-04-28');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (5, 'SELL', 'UAL', -10, 267, 0, '2020-04-28');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (5, 'SELL', 'NCLH', -100, 2670, 0, '2020-04-28');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (7, 'SELL', 'UAL', -10, 370, 0, '2020-04-28');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (7, 'SELL', 'SJM', -100, 2370, 0, '2020-04-28');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (8, 'SELL', 'ADS', -53, 6000, 0, '2020-04-28');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (8, 'SELL', 'COTY', -10, 2170, 0, '2020-04-28');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (6, 'SELL', 'AAL', -100, 2170, 0, '2020-04-25');
INSERT INTO trade (portfolio_id, type, ticker, quantity, stock_value, commission, date_of_purchase) VALUES (18, 'SELL', 'AAPL', -100, 29500, 0, '2020-04-29');

--Users_game
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (1, 1, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (2, 1, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (3, 1, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (4, 1, true);
--Jake
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (1, 2, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (2, 2, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (3, 2, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (4, 2, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (1, 3, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (2, 3, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (3, 4, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (4, 4, false);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (2, 13, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (3, 13, true);

INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (6, 13, true); 
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (6, 2, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (6, 1, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (6, 14, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (6, 3, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (6, 4, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (2, 4, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (3, 8, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (6, 8, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (5, 13, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (5, 3, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (3, 3, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (7, 3, true);
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (4, 3, true);

COMMIT TRANSACTION;
