BEGIN TRANSACTION;
----OVERALL TEST FOR WALT DISNEY GAME
--Test for user: Atticusf (who won the  game) in each table
INSERT INTO game (organizer_id, name, winner_id, number_of_players, length_in_days, start_date, end_date, public_game) VALUES (1, 'WaltDisneyCo', 1, 3, 30, '1990-03-07', '1990-03-12', true);
INSERT INTO users (username, password, salt, role) VALUES ('Atticusf', 'atticusisatticus', 'awdawdwaawdadwawdwawad', 'user')
INSERT INTO portfolio (portfolio_id, user_id, game_id, total_value) VALUES (1, 3, 7, 123000)
INSERT INTO trade (trade_id, portfolio_id, stock_id, type, quantity, date_of_purchase, stock_value, commission) VALUES (1, 1, 1, 'buy', 50, '1990-03-07', 35, 19.95)
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (3, 7, true)

--Test for user: Scar (who lost the game) in each table
--Dont need to insert into game, since this is data for the same game that's been created
INSERT INTO users (username, password, salt, role) VALUES ('Scarf', 'scarscarscar', 'whatnowhydidiloseholycrap!', 'user')
INSERT INTO portfolio (portfolio_id, user_id, game_id, total_value) VALUES (2, 4, 7, 100000)
INSERT INTO trade (trade_id, portfolio_id, stock_id, type, quantity, date_of_purchase, stock_value, commission) VALUES (2, 2, 1, 'buy', 30, '1990-03-07', 35, 19.95)
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (4, 7, true)
ROLLBACK;
COMMIT;
--===========================================================================================================================================
BEGIN TRANSACTION;
---OVERALL TEST FOR MARVEL GAME
--Test for user: TonyStark (who won the game) in each table
INSERT INTO game (organizer_id, name, winner_id, number_of_players, length_in_days, start_date, end_date, public_game) VALUES (2, 'Marvel', 2, 2, 15, '2020-04-21', '2020-05-05', true);
INSERT INTO users (username, password, salt, role) VALUES ('TonyStark', 'tonyruulllesss', 'infinittysstooonneee', 'user')
INSERT INTO portfolio (portfolio_id, user_id, game_id, total_value) VALUES (3, 5, 8, 2000000)
INSERT INTO trade (trade_id, portfolio_id, stock_id, type, quantity, date_of_purchase, stock_value, commission) VALUES (3, 3, 3, 'sell', 100, '2020-04-21', 60, 19.95)
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (5, 8, true)

--Test for user: Loki (who didn't accept the invite) in each table
--Dont need to insert into game, since this is data for the same game that's been created

--NOTE: Im wondering if we need an organizer_id column in USERS
--NOTE: If a user does NOT accept an invite, what columns need to be able to be NULL?
-- > >  portfolio_id? total_value?  The entire trade table?
INSERT INTO users (username, password, salt, role) VALUES ('Loki', 'lokiistrickkyy', 'HAHAHAHAHAH!', 'user')
INSERT INTO portfolio (portfolio_id, user_id, game_id, total_value) VALUES (2, 4, 7, 100000)
INSERT INTO trade (trade_id, portfolio_id, stock_id, type, quantity, date_of_purchase, stock_value, commission) VALUES (2, 2, 1, 'buy', 30, '1990-03-07', 35, 19.95)
INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (6, 8, false)

--Changing null constraints to account for invites not accepted / declined



ROLLBACK;
COMMIT;


