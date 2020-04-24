BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS users_game;
DROP TABLE IF EXISTS user_game;
DROP TABLE IF EXISTS portfolio;
DROP TABLE IF EXISTS trade;

CREATE TABLE user (
  user_id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  password varchar(32) NOT NULL,      -- Password
  salt varchar(256) NOT NULL,          -- Password Salt
  role varchar(255) NOT NULL default('user')
);

CREATE TABLE game
(
	game_id serial,
	organizer_id int not null,
	winner_id int,
	name varchar(64) not null,
	number_of_players int not null,
	length_in_days int not null,
	start_date date,
        end_date date,
        public_game boolean,

	constraint pk_game primary key (game_id)
	
);

CREATE TABLE user_game
(
        user_id int not null,
        game_id int not null,
        invite_accepted boolean not null,
        
        constraint fk_user_game_user foreign key (user_id) references user (id),
        constraint fk_user_game_game foreign key (game_id) references game (game_id)

);

CREATE TABLE portfolio
(
        portfolio_id serial,
        user_id int not null,
        game_id int not null,
        total_value money not null,
        
        constraint pk_portfolio primary key (portfolio_id),
        constraint fk_portfolio_user foreign key (user_id) references user (id),
        constraint fk_portfolio_game foreign key (game_id) references game (game_id)
             
);

CREATE TABLE trade
(
        trade_id serial,
        portfolio_id int not null,
        company_name varchar(64) not null,
        ticker varchar(64) not null,
        type varchar(64) not null,
        quantity int not null,
        date_of_purchase date not null,
        stock_value money not null,
        commission money not null,
        
        constraint pk_trade primary key (trade_id),
        constraint fk_trade_portfolio foreign key (portfolio_id) references portfolio (portfolio_id)
);

COMMIT TRANSACTION;

;
-- COMMIT;
-- BEGIN TRANSACTION;

-- ALTER TABLE users ADD avatar varchar(2048);

-- ROLLBACK;
-- COMMIT;

-- BEGIN TRANSACTION;

-- DROP TABLE stock;