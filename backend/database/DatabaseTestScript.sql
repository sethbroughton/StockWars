
--Pending Games
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (1, 'Game1', 7, 5);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (1, 'Game2', 5, 20);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (1, 'Game3', 10, 45);
INSERT INTO game (organizer_id, name, number_of_players, length_in_days) VALUES (1, 'Game4', 20, 90);

--Active Games
UPDATE game SET start_date = '2020-04-25', end_date = '2020-05-02' WHERE game.game_id = 1;
UPDATE game SET start_date = '2020-04-18', end_date = '2020-04-28' WHERE game.game_id = 3;

