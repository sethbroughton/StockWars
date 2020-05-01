<template>
  <div id="game">
    <user-header></user-header>
    <div class="container">
      <div class="game-stats u-margin-bottom-x-large">
        <p class="stat">{{game.name}}</p>
        <p v-if="this.game.winnerId == 0" class="stat">Available Cash: ${{this.portfolio.cash.toLocaleString()}}</p>
        <p v-if="!this.endgameCondition" class="stat">Game Ends: {{game.endDate.monthValue}}/{{game.endDate.dayOfMonth}}/{{game.endDate.year}}</p>
        <button v-if="this.endgameCondition && this.game.winnerId == 0" v-on:click="endGame" id="end-game-button" class="button">Game Over!<br>Click to See Winner</button>
      </div>
      <h2 class="game-over" v-if="this.game.winnerId != 0" >Game Over!<br>Winner: {{this.winner.username}}</h2>

      <stock-buy-sell ref="buysell" v-if="this.game.winnerId == 0" v-on:hide-scoreboard="hide" class="u-margin-bottom-x-large"/>

      <div v-if="this.game.winnerId == 0" class="link-boxes">
        <router-link v-bind:to="{ name: 'portfolio', params: {portfolioId: portfolio.portfolioId} }" id="portfolio" class="link-box">
          Portfolio
        </router-link>
        <router-link :to="{ name: 'trade-history', params: {portfolioId: portfolio.portfolioId} }" id="history" class="link-box">
          History
        </router-link>
      </div>
    </div>

    <button v-if="this.hideScoreboard == true && this.hideScoreboardButton == false && !this.game.winnerId" id="show-scoreboard" v-on:click="currentAccountBalance">Current Scores</button>
    <div v-if="this.hideScoreboard === false" class="scoreboard">
      <div v-for="portfolio in portfoliosWithTotalBalance" :key="portfolio.portfolioId" class="player-card">
        {{portfolio.username}}<br>${{portfolio.accountBalance.toLocaleString()}}
      </div>
    </div>
  </div>
</template>

<script>
import UserHeader from '@/components/UserHeader'
import StockBuySell from '@/components/StockBuySell'
import auth from '../auth'

export default {
  name: 'game',
  components: {
    UserHeader,
    StockBuySell,
  },
  data() {
    return {
      user: this.$parent.user,
      game: {
        gameId: this.$route.gameId
      },
      portfolio: null,
      allPortfolios: [],
      hideScoreboard: true,
      hideScoreboardButton: false,
      tickerArray: [],
      tickerDate: '',
      portfoliosWithTotalBalance: [],
      quotes: {
      },
      winner: null,
      endgameCondition: false
    }
  },
  methods: {
    hide() {
      this.hideScoreboard = true;
      this.hideScoreboardButton = true;
    },
    getPricesForAllStocks(){
      let query = "";
      
        let tickerArray = this.tickerArray;
          for(let i = 0; i<tickerArray.length; i++){
              query += tickerArray[i] + ','
          }
          console.log(query)
            fetch(`https://cloud.iexapis.com/stable/stock/market/batch?&types=price&symbols=${query}&token=${process.env.VUE_APP_API_KEY}`)
              .then((response) => {
                return response.json();
              })
              .then((quotes) => {
                this.quotes = quotes;
              })
              // console.log(this.quotes["AAPL"])   
    },
    endGame() {
      console.log('calling portfolio totals');
      this.currentAccountBalance();

      const authToken = auth.getToken();
      
      const fetchConfigPut = {
        method : "PUT",
        headers: {
          Authorization: `Bearer ${authToken}`,
          'Content-Type' : 'application/json',
          'Accept': 'application/json'
        }
      }

      const fetchConfigDelete = {
        method : "DELETE",
        headers: {
          Authorization: `Bearer ${authToken}`,
          'Content-Type' : 'application/json',
          'Accept': 'application/json'
        }
      }

      console.log('liquidating portfolios');
      // LIQUIDATE THE PORTFOLIOS
      for (let i = 0; i < this.portfoliosWithTotalBalance.length; i++) {   
        let thePortfolio = this.portfoliosWithTotalBalance[i]; 
        console.log('current portfolio: ' + thePortfolio);
        
        // GET PORTFOLIO TOTAL AND ID
        let finalBalance = thePortfolio.accountBalance;
        let id = thePortfolio.portfolioId;

        // SET PORTFOLIO TOTAL TO CASH BALANCE
        fetch(`${process.env.VUE_APP_REMOTE_API}/api/balance/${finalBalance}/${id}`, fetchConfigPut)
        .then((response) => {
          if(response.ok) {
            return response.json();
          }
        })
        .catch((err) => console.error(err));

        // DELETE ALL TRADES
        fetch(`${process.env.VUE_APP_REMOTE_API}/api/deleteTrades/${id}`, fetchConfigDelete)
        .then((response) => {
          if(response.ok) {
            return response.json();
          }
        })
        .catch((err) => console.error(err));      

      }   

      console.log('setting winner id');
      let winnerId = this.portfoliosWithTotalBalance[0].userId;
      console.log('winnerId: ' + winnerId);
      console.log('gameId: ' + this.game.gameId);

      // SET WINNER ID
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/setWinner/${winnerId}/${this.game.gameId}`, fetchConfigPut)
      .then((response) => {
        if(response.ok) {
          return response.json();
        }
      })
      .catch((err) => console.error(err));

      location.reload();

    },
    getDateToday(){
      let currentDate = new Date();
      this.tickerDate = currentDate;
    },
    currentAccountBalance(){
      let portfoliosWithTotalBalance = [];
       for(let i = 0; i<this.allPortfolios.length; i++){
          let myPortfolio = this.allPortfolios[i];
          let accountBalance = myPortfolio["cash"];
          let object = myPortfolio["stocks"][0];
            for (const property in object){
              console.log(property)
              let stockValue = object[property]*this.quotes[property].price
              accountBalance += stockValue;
            }
            let portfolioWithTotal = {
               "portfolioId": myPortfolio["portfolioId"],
               "userId": myPortfolio["userId"],
               "accountBalance": accountBalance
             }

            // JEFF: LOOPS THROUGH ALL PLAYERS IN CURRENT GAME AND AND USERNAME TO SCOREBOARD DATA
            for (let j = 0; j < this.game.players.length; j++) {
              if ( this.allPortfolios[i].userId == this.game.players[j].id ) {
                portfolioWithTotal.username = this.game.players[j].username;
              }
            }
            portfoliosWithTotalBalance.push(portfolioWithTotal);   
      }

      //Sort Array
      portfoliosWithTotalBalance.sort(function(a,b){
        return b.accountBalance - a.accountBalance;
      })

      this.portfoliosWithTotalBalance = portfoliosWithTotalBalance;
      this.hideScoreboard = false;
    },
    setWinnerName() {
      const authToken = auth.getToken();
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/user/${this.game.winnerId}`, {
        method: 'GET',
        headers:{
          Authorization: `Bearer ${authToken}`
        }
      })
      .then(response => {
        return response.json();
      })
      .then((winnerName) => {
        this.winner = winnerName;
      })
      .catch(err => console.log(`Error fetching user ${err}`));  
      }
  },
  mounted(){

    const authToken = auth.getToken();
        const fetchConfigGet = {
          method: 'GET',
          headers:{
            Authorization: `Bearer ${authToken}`
          }
        }
    //Create Portfolio Array
    const gameId = this.$route.params.gameId;
        fetch(`${process.env.VUE_APP_REMOTE_API}/api/portfoliosInGame/${gameId}`, fetchConfigGet)
        .then(response => {
          return response.json();
        })
        .then((portfolios) => {
          let allPortfolios = [];
              for(let i = 0; i<portfolios.length; i++){
                let portfolio = portfolios[i];
                let stockArray = [];
                        let trades = portfolio.trades
                        let stocks = {};
                          for(let i = 0; i<trades.length; i++){
                            let ticker = trades[i].ticker;
                            let num = trades[i].quantity;
                            stocks[ticker] = stocks[ticker] ? stocks[ticker] + num : num;
                          }
                        stockArray.push(stocks);
                        let totalPortfolio = {
                          "portfolioId": portfolio["portfolioId"],
                          "userId": portfolio["userId"],
                          "stocks": stockArray,
                          "cash": portfolio["cash"]
                        }
                allPortfolios.push(totalPortfolio);
                }
                    
        this.allPortfolios = allPortfolios;
      // console.log(allPortfolios[0]["portfolioId"])
        
        //Get Array of tickers
        let myArr = [];
            for(let i = 0; i<allPortfolios.length; i++){
            let array = (Object.keys(allPortfolios[i].stocks[0]))
              array.forEach(el => {
                if(!myArr.includes(el)){
                myArr.push(el)
                }
              })
            }
            console.log(myArr)
            this.tickerArray = myArr;
            this.getDateToday();
            this.getPricesForAllStocks();
    })

    

  },
  created() {

    const gameId = this.$route.params.gameId;
    
    let todayRaw = new Date();
    let yyyy = todayRaw.getFullYear();
    let mm = todayRaw.getMonth()+1;
    let dd = todayRaw.getDate();
    if(dd<10) { dd='0'+dd; } 
    if(mm<10) { mm='0'+mm; } 
    const today = yyyy+'-'+mm+'-'+dd;
    console.log('today: ' + today);

    const authToken = auth.getToken();
    const fetchConfigGet = {
      method: 'GET',
      headers:{
        Authorization: `Bearer ${authToken}`
      }
    }

    console.log('setting correct game');
    fetch(`${process.env.VUE_APP_REMOTE_API}/api/game/${gameId}`, fetchConfigGet)
    .then(response => {
      return response.json();
    })
    .then((game) => {
      this.game = game;
      this.setWinnerName();
      console.log(this.game.winnerId, this.winner);
    })
    .catch(err => console.log(`Error fetching games ${err}`));

    console.log('getting correct portfolio');
    fetch(`${process.env.VUE_APP_REMOTE_API}/api/portfolios`, fetchConfigGet)
    .then(response => {
      return response.json();
    })
    .then((portfolios) => {
      this.portfolio = portfolios.find(p => p.userId == this.user.id && p.gameId == this.$route.params.gameId);
    })
    .catch(err => console.log(`Error fetching portfolios ${err}`));

    console.log('checking for endgame');
    fetch(`${process.env.VUE_APP_REMOTE_API}/api/endgameTest/${today}/${gameId}`, fetchConfigGet)
    .then(response => {
      return response.json();
    })
    .then((endGame) => {
      console.log(endGame);
      if (endGame) {
        this.endgameCondition = true;
      }
    })
    .catch(err => console.log(`Error fetching games ${err}`));  

  }
}

</script>

<style scoped>

#game {
  position: relative;
}

.game-over {
  color: var(--color-grey-light-1);
  font-size: 10rem;
  font-weight: 300;
}

.container {
  padding: 2% 7%;

  display: flex;
  flex-direction: column;
  align-items: center;
}

.game-stats {
  width: 100%;
  font-size: 3rem;
  font-weight: 700;
  color: var(--color-grey-light-1);

  display: flex;
  justify-content: space-between;
}

.search-and-trade {
  display: flex;
  align-items: flex-start;
}

.link-boxes {
  width: 100%;
  margin: 0 2%;

  display: flex;
  justify-content: center;
}

.link-box {
  color: var(--color-grey-light-1);
  font-weight: 700;
  font-size: 5rem;
  text-align: center;

  background-size: cover;
  background-position: center;
  box-shadow: var(--shadow);

  margin: 0 1%;
  padding: 2% 3%;

  transition: all .2s;
}

.link-box:hover {
  transform: translateY(-.3rem);
  box-shadow: var(--shadow-large);
}

.scoreboard {
  width: 11%;
  position: absolute;
  top: 50vh;
  left: 0;
  transform: translatey(-50%);
}

.player-card {
  width: 100%;
  color: var(--color-grey-light-1);
  background-color: var(--color-tertiary-2);
  text-align: right;
  font-size: 2rem;
  font-weight: 700;

  padding: 4% 8%;
  margin-bottom: 15%;

  position: relative;
  left: -5%;

  transition: all .2s;
}

.player-card:hover {
  left: 0;
}

#show-scoreboard {
  padding: 1% 1% 1% 2%;

  color: var(--color-grey-light-1);
  font-size: 2rem;
  font-weight: 600;
  background-color: var(--color-complementary-2);
  border: none;
  box-shadow: var(--shadow);
  cursor: pointer;

  position: absolute;
  top: 50vh;
  left: -1%;
  transform: translatey(-50%);

  transition: all .2s;
}

#show-scoreboard:hover {
  background-color: var(--color-complementary-1);
  left: 0;
}

#end-game-button {background-color: var(--color-red-2);}
#end-game-button:hover {background-color: var(--color-red-1);}

#portfolio { background-image: linear-gradient(var(--portfolio-image-overlay)), url(../assets/img/portfolio.jpg); }
#history { background-image: linear-gradient(var(--history-image-overlay)), url(../assets/img/history.jpg); }


</style>