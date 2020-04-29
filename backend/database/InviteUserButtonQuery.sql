BEGIN TRANSACTION;

UPDATE users_game SET invite_accepted = true WHERE game_id = 2 AND user_id = 2;
DELETE FROM users_game WHERE invite_accepted = false AND game_id = 2 AND user_id = 3;

ROLLBACK;
COMMIT;