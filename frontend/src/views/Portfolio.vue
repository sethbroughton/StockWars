<template>
  <div id="portfolio">
    <user-header/>

    <div class="container">
      <table class ="table u-margin-bottom">
        <tr class="table-header">
          <h3 class="table-title">Portfolio - ${{portfolioValue}}</h3>
          <div class="table-buttons">
            <router-link v-bind:to="{name: 'game', params: {gameId: portfolio.gameId} }" id="return-to-game" class="button-small">Return to Game</router-link>
          </div>
        </tr>
        <tr class="table-header">
          <th class="table-item">Ticker</th>
          <th class="table-item">Shares</th>
          <th class="table-item">Price</th>
          <th class="table-item">Total Value</th>
        </tr>
        <tr v-for="stock in stocks" v-bind:key="stock.ticker" class="table-row">
          <!-- <router-link  v-bind:to="{name: 'game', params: {gameId: portfolio.gameId}, query: {ticker: stock.ticker}}"
           class="button-small buysell-button">Buy/Sell</router-link> -->
          <td class="table-item">{{stock.ticker}}</td>
          <td class="table-item">{{stock.quantity}}</td>
          <td class="table-item">{{(quotes[stock.ticker].price)}}</td>
          <td class="table-item">{{formatMoney(quotes[stock.ticker].price * stock.quantity)}}</td>
        </tr>
      </table>
      <h3 id="cash-balance">Cash Balance:  {{formatMoney(portfolio.cash)}}</h3>
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
        portfolioId: this.$route.params.portfolioId,
        userId: '',
        gameId: '',
        cash: null
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
      stocks: [],
      portfolioValue: ''
    }
  }, 

  methods: {
    formatMoney(number){
       const formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
        minimumFractionDigits: 2
        })
        return formatter.format(number);
    },
    setTickerSymbol(){
      this.$emit('setTickerSymbol')
    }, 
    displayPortfolio() {

      const authToken = auth.getToken();
      const fetchConfigGet = {
        method: 'GET',
        headers: {
          Authorization: `Bearer ${authToken}`
        }
      }
       const portfolioId = this.portfolio.portfolioId
       
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/trades/${portfolioId}`, fetchConfigGet)
      .then((response)=> {
        return response.json();
      })
      .then((stocks)=> { 
        this.stocks = stocks;

        // let stocks = {};
        // for (var i = 0; i < trades.length; i++) {
        //   let ticker = trades[i].ticker;
        //   let num = trades[i].quantity;
        //     stocks[ticker] = stocks[ticker] ? stocks[ticker] + num : num;
        // }
        
        // this.tickerArray = Object.keys(stocks); //BUILDS AN ARRAY OF TICKERS FOR THE PUBLIC API CALL
        // console.log(this.tickerArray)

        //BUILDS AN ARRAY OF OBJECTS TO RENDER EACH LINE ITEM IN THE PORTFOLIO
        //this.stockArray = [];

        // for(let i = 0; i<this.tickerArray.length; i++){
        //   let object = {};
        //   object.ticker = this.tickerArray[i];
        //   object.quantity = stocks[this.tickerArray[i]];
        //   this.stockArray.push(object);
        // }

        this.updateStockPrices();
      })

      fetch(`${process.env.VUE_APP_REMOTE_API}/api/portfolio/${this.portfolio.portfolioId}`, fetchConfigGet)
      .then((response) => {
        return response.json();
      })
      .then ((data) => {
        this.portfolio = data;

      })
    },

    updateStockPrices(){
          let query = ''
          for(let i = 0; i<this.stocks.length; i++){
              query += this.stocks[i].ticker + ','
          }
          console.log(query)
            fetch(`https://cloud.iexapis.com/stable/stock/market/batch?&types=price&symbols=${query}&token=${process.env.VUE_APP_API_KEY}`)
              .then((response) => {
                return response.json();
              })
              .then((quotes) => {
                this.quotes = quotes;
                this.calculatePortfolioValue();

              })
              console.log(this.quotes[0])
    }, 
    calculatePortfolioValue(){
      let totalStockDollar = 0;
      for(let i = 0; i<this.stocks.length; i++){
        let stock = this.stocks[i];
        let value = this.quotes[stock.ticker].price * stock.quantity
        totalStockDollar+=value;
      }
    this.portfolioValue = (this.portfolio.cash + totalStockDollar).toLocaleString()
    }

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

th {
  font-size: 2.5rem;
  width: 25%;
  border: none !important; 
}

.table-row {
  width: 100%;
}

#cash-balance {
  color: var(--color-grey-light-1);
  font-size: 3rem;

  display: flex;
  align-items: center;
}

#return-to-game { background-color: var(--color-complementary-2); }
#return-to-game:hover { background-color: var(--color-complementary-1); }

.buysell-button { background-color: var(--color-tertiary-2); }
.buysell-button:hover { background-color: var(--color-tertiary-1); }

</style>