<template>
  <div id="game">
    <user-header></user-header>
    <div class="container">
      <div class="game-stats u-margin-bottom">
        <p class="stat">{{game.name}}</p>
        <p class="stat">Balance: ${{allPortfolios[0]["cash"]}}</p>
        <p class="stat">XX Days Left</p>
      </div>

      <stock-buy-sell v-on:hide-scoreboard="hide" class="u-margin-bottom"/>

      <div class="link-boxes">
        <router-link v-bind:to="{ name: 'portfolio', params: {portfolioId: portfolio.portfolioId} }" id="portfolio" class="link-box">
          Portfolio
        </router-link>
        <router-link :to="{ name: 'trade-history', params: {portfolioId: portfolio.portfolioId} }" id="history" class="link-box">
          History
        </router-link>
      </div>
    </div>
    
    <button v-on:click="currentAccountBalance">Update</button>
    <div v-if="this.hideScoreboard === false" class="scoreboard">
      <div v-for="portfolio in portfoliosWithTotalBalance" :key="portfolio.portfolioId" class="player-card">
        {{portfolio.userId}}: {{portfolio.accountBalance}}
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
      hideScoreboard: false,
      tickerArray: [],
      tickerDate: '',
      portfoliosWithTotalBalance: [],
      quotes: {
      },

    }
  },
  methods: {
    hide() {
      this.hideScoreboard = true;
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
              console.log(this.quotes["AAPL"])   
    },

    getDateToday(){
      let currentDate = new Date();
      this.tickerDate = currentDate;  //TODO: Add a check for if the current date is after game finish date
    },

    currentAccountBalance(){
      console.log('hi')
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
      //TODO: Sort array
            portfoliosWithTotalBalance.push(portfolioWithTotal);   
      }
      this.portfoliosWithTotalBalance = portfoliosWithTotalBalance;
    
    },

// TODO: This is for the game over mechanism...
    // gameOverPrice(ticker){
    //   fetch(`https://cloud.iexapis.com/stable/stock/${ticker}/chart/date/${this.tickerDate}?chartByDay=true&token=${process.env.VUE_APP_API_KEY}`)
    //           .then((response) => {
    //             return response.json();
    //           })
    //           .then((quote) => {
    //             let price = quote;
    //             return price;
    //           })
    // }, 

  },

  created() {

    const authToken = auth.getToken();
    const fetchConfigGet = {
      method: 'GET',
      headers:{
        Authorization: `Bearer ${authToken}`
      }
    }

    fetch(`${process.env.VUE_APP_REMOTE_API}/currentUser`, fetchConfigGet)
    .then((response) => {
      return response.json();
    })
    .then((currentUser) => {
      this.user = currentUser;
    });    

    fetch(`${process.env.VUE_APP_REMOTE_API}/api/game/`, fetchConfigGet)
    .then(response => {
      return response.json();
    })
    .then((games) => {
      this.game = games.find(p => p.gameId == this.$route.params.gameId);
    })
    .catch(err => console.log(`Error fetching games ${err}`));

    fetch(`${process.env.VUE_APP_REMOTE_API}/api/portfolios`, fetchConfigGet)
    .then(response => {
      return response.json();
    })
    .then((portfolios) => {
      this.portfolio = portfolios.find(p => p.userId == this.user.id && p.gameId == this.$route.params.gameId);
    })
    .catch(err => console.log(`Error fetching portfolios ${err}`));

    //Create Portfolio Array
    const gameId = 1; //TODO: This will need to be dynamic
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
                        let type = trades[i].type;
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
    console.log(allPortfolios[0]["portfolioId"])
    
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
  }
}

</script>

<style scoped>

#game {
  position: relative;
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

  display: flex;
  justify-content: space-evenly;
}

.link-box {
  color: var(--color-grey-light-1);
  font-weight: 700;
  font-size: 5rem;
  text-align: center;

  width: 25%;
  background-size: cover;
  background-position: center;
  box-shadow: var(--shadow);

  padding: 2% 3%;

  transition: all .2s;
}

.link-box:hover {
  transform: translateY(-.3rem);
  box-shadow: var(--shadow-large);
}

.scoreboard {
  width: 8%;

  position: absolute;
  top: 50vh;
  left: 0;
}

.player-card {
  color: var(--color-grey-light-1);
  background-color: var(--color-tertiary-2);
  text-align: right;
  font-size: 1.5rem;
  font-weight: 700;

  padding: 3% 8%;
  margin-bottom: 5%;
}

#portfolio { background-image: linear-gradient(var(--portfolio-image-overlay)), url(../assets/img/portfolio.jpg); }
#history { background-image: linear-gradient(var(--history-image-overlay)), url(../assets/img/history.jpg); }

</style>