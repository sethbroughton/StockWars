<template>
  <div id="portfolio">
    <user-header/>

    <div class="container">
      <div class ="table">
        <div class="table-header">
          <h3 class="table-title">Portfolio - ${{portfolio.cash}}</h3>
          <div class="table-buttons">
            <router-link v-bind:to="{name: 'game', params: {gameId: portfolio.gameId} }" id="return-to-game" class="button-small">Return to Game</router-link>
          </div>
        </div>
        <div v-for="stock in stockArray" v-bind:key="stock.ticker" class="table-row">
          <router-link v-bind:to="{name: 'stock'}" class="button-small buysell-button">Buy/Sell</router-link>
          <!-- <span class="table-item">{{quotes.companyName}}</span> -->
          <span class="table-item">{{stock.ticker}}</span>
          <span class="table-item">{{stock.quantity}}</span>
          <!-- <span class="table-item">{{quotes.AAPL.price}}</span> -->
          <span class="table-item">Total$</span></div>
          
      </div>
    </div>
  </div>

</template>

<script>
import UserHeader from '@/components/UserHeader'
import auth from '../auth'

export default {
  name: 'portfolio',
  components: {
    UserHeader
  },
  data() {
    return {
      portfolio: { 
        portfolioId: this.$route.params.portfolioId
      },
      user: this.$parent.user,
      data: [{
        name: '',
        price: ''
      }],
      quotes: {
         price: '',
         symbol: '',
         companyName: ''
      },
      tickerArray: [],
      stockArray: []
    }
  },
  methods: {

    displayPortfolio() {
      const authToken = auth.getToken();
      const fetchConfigGet = {
      method: 'GET',
      headers: {
        Authorization: `Bearer ${authToken}`
       }
      }
       fetch(`${process.env.VUE_APP_REMOTE_API}/api/trades/1`, fetchConfigGet)
       .then((response)=> {
         return response.json();
       })
       .then((trades)=> { 
         let stocks = {};
            for (var i = 0; i < trades.length; i++) {
              let ticker = trades[i].ticker;
              let num = trades[i].quantity;
              let type = trades[i].type;
              if(type=='BUY'){
                stocks[ticker] = stocks[ticker] ? stocks[ticker] + num : num;
              } else {
                stocks[ticker] = stocks[ticker] ? stocks[ticker] - num : num;
              }
            }
            this.tickerArray = Object.keys(stocks); //BUILDS AN ARRAY OF TICKERS FOR THE PUBLIC API CALL
            console.log(this.tickerArray)

            //BUILDS AN ARRAY OF OBJECTS TO RENDER EACH LINE ITEM IN THE PORTFOLIO
            this.stockArray = [];
              for(let i = 0; i<this.tickerArray.length; i++){
                let object = {};
                object.ticker = this.tickerArray[i];
                object.quantity = stocks[this.tickerArray[i]];
                this.stockArray.push(object);
              }
              this.updateStockPrices();
       })
    },

    updateStockPrices(){
      let query = "";
        let tickerArray = this.tickerArray;
          for(let i = 0; i<tickerArray.length; i++){
              query += tickerArray[i] + ','
          }
          console.log(query)
            fetch(`https://cloud.iexapis.com/v1/stock/market/batch?&types=quote&symbols=${query}&token=${process.env.VUE_APP_API_KEY}`)
              .then((response) => {
                return response.json();
              })
              .then((quotes) => {
                this.quotes = quotes;
              })
              console.log(this.quotes[0])
    },

    // displayPortfolios() {

    //   const authToken = auth.getToken();
    //   const fetchConfigGet = {
    //   method: 'GET',
    //   headers: {
    //     Authorization: `Bearer ${authToken}`
    //    }
    //   }
    //   fetch(`${process.env.VUE_APP_REMOTE_API}/api/portfolio${this.portfolio.portfolioId}`, fetchConfigGet)
    //   .then((response) => {
    //     return response.json();
    //   })
    //   .then ((data) => {
    //       this.portfolio = data;
    //   })
    // },

  // returnToGame() {
  //     const authToken = auth.getToken();
  //     const fetchConfig = {
  //       method : "GET",
  //       headers:{
  //         Authorization : `Bearer ${authToken}`
  //       }
  //     }
  //     fetch(`${process.env.VUE_APP_REMOTE_API}/game/myGame/${this.portfolio.portfolioId}`, fetchConfig)
  //     .then(response => {
  //       if (response.ok){
  //         this.router.push(`/game/myGame/${this.portfolio.portfolioId}`)
  //       }
  //     })
  //      .catch((err) => console.error(err));
  //     }
    
  },
  created(){
    this.displayPortfolio();
  }
}

</script>

<style scoped>

.container {
  padding: 3%;

  display: flex;
  flex-direction: column;
  align-items: center;
}

#return-to-game { background-color: var(--color-complementary-2); }
#return-to-game:hover { background-color: var(--color-complementary-1); }

.buysell-button { background-color: var(--color-tertiary-2); }
.buysell-button:hover { background-color: var(--color-tertiary-1); }

</style>