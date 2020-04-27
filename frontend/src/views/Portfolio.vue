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
        <!-- <div v-for="portfolioEntry in displayPortfolios" v-bind:key="portfolioEntry.portfolioId" class="table-row">
          <router-link v-bind:to="{name: 'stock'}" class="button-small buysell-button">Buy/Sell</router-link>
          <span class="table-item">Stock # 1</span>
          <span class="table-item">Ticker</span>
          <span class="table-item">Shares</span>
          <span class="table-item">{{quotes.AAPL.price}}</span>
          <span class="table-item">Total$</span></div> -->


       
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
        // price: '',
        // symbol: '',
        // companyName: ''
      },
      symbols: ['AAPL', 'fb', 'tsla'],
      stocks: {
        ticker: '',
        quantity: ''
      }
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
       fetch(`${process.env.VUE_APP_REMOTE_API}/api/trades/${this.portfolio.portfolioId}`, fetchConfigGet)
       .then((response)=> {
         return response.json();
       })
       .then((trades)=> {
         

         
       })
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

  returnToGame() {
      const authToken = auth.getToken();
      const fetchConfig = {
        method : "GET",
        headers:{
          Authorization : `Bearer ${authToken}`
        }
      }
      fetch(`${process.env.VUE_APP_REMOTE_API}/game/myGame/${this.portfolio.portfolioId}`, fetchConfig)
      .then(response => {
        if (response.ok){
          this.router.push(`/game/myGame/${this.portfolio.portfolioId}`)
        }
      })
       .catch((err) => console.error(err));
      }
    
  },
  created(){
    //Batch API call - Seth
    let query = "";
    let symbols = this.symbols;
    for(let i = 0; i<symbols.length; i++){
        query += symbols[i] + ','
    }
      fetch(`https://cloud.iexapis.com/v1/stock/market/batch?&types=price&symbols=${query}&token=${process.env.VUE_APP_API_KEY}`)
        .then((response) => {
          return response.json();
        })
        .then((quotes) => {
          this.quotes = quotes;
        })

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