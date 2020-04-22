BEGIN TRANSACTION;
INSERT INTO game (organizer_id, name, number_of_players, length_in_days, start_date, end_date, public_game) VALUES (1, 'Langley', 3, 30, '1990-03-07', '1990-03-12', true);
ROLLBACK;
COMMIT;

