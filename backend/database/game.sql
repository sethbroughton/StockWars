BEGIN TRANSACTION;

CREATE TABLE game
(
	game_id serial,
	organizer_id int not null,
	winner_id int,
	name varchar(64) not null,
	number_of_players int not null,
	length_in_days int not null,
	start_date date not null,
        end_date date not null,
        public_game boolean not null,

	constraint pk_game primary key (game_id)
	
);


CREATE TABLE users_game
(
        user_id int not null,
        game_id int not null,
        invite_accepted boolean not null,
        
        constraint fk_users_game_users foreign key (user_id) references users (id),
        constraint fk_users_game_game foreign key (game_id) references game (game_id)

);


CREATE TABLE portfolio
(
        portfolio_id serial,
        user_id int not null,
        game_id int not null,
        total_value money not null,
        
        constraint pk_portfolio primary key (portfolio_id),
        constraint fk_portfolio_users foreign key (user_id) references users (id),
        constraint fk_portfolio_game foreign key (game_id) references game (game_id)
             
);


CREATE TABLE stock
(
        stock_id serial,
        ticker varchar(64) not null,
        current_price money not null,
       
        constraint pk_stock primary key (stock_id)
);


CREATE TABLE portfolio_stock
(
        portfolio_id int not null,
        stock_id int not null,
        stock_quantity int not null,
        
        constraint fk_portfolio_stock_stock foreign key (stock_id) references stock (stock_id),
        constraint fk_portfolio_stock_portfolio foreign key (portfolio_id) references portfolio (portfolio_id)
);


CREATE TABLE trade
(
        trade_id serial,
        portfolio_id int not null,
        stock_id int not null,
        type varchar(64) not null,
        quantity int not null,
        date_of_purchase date not null,
        stock_value money not null,
        commission money not null,
        
        constraint pk_trade primary key (trade_id),
        constraint fk_trade_portfolio foreign key (portfolio_id) references portfolio (portfolio_id),
        constraint fk_trade_stock foreign key (stock_id) references stock (stock_id)        
);

COMMIT;
