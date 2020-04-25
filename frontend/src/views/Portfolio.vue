<template>
  <div id="portfolio">
    <user-header/>

    <div class="container">
      <div class ="table">
        <div class="table-header">
          <h3 class="table-title">Portfolio - $XXX,XXX</h3>
          <div class="table-buttons">
            <router-link v-bind:to="{name: 'game'}" id="return-to-game" class="button-small">Return to Game</router-link>
          </div>
        </div>
        <div class="table-row"><span class="table-item">Stock # 1</span><span class="table-item">Ticker</span><span>Shares</span><span class="table-item">{{quotes.AAPL.price}}</span><span class="table-item">Total$</span><router-link v-bind:to="{name: 'stock'}" class="button-small buysell-button">Buy/Sell</router-link></div>
        <div class="table-row"><span class="table-item">Stock # 2</span><span class="table-item">Ticker</span><span>Shares</span><span class="table-item"></span><span class="table-item">Total$</span><router-link v-bind:to="{name: 'stock'}" class="button-small buysell-button">Buy/Sell</router-link></div>
        <div class="table-row"><span class="table-item">Stock # 3</span><span class="table-item">Ticker</span><span>Shares</span><span class="table-item"></span><span class="table-item">Total$</span><router-link v-bind:to="{name: 'stock'}" class="button-small buysell-button">Buy/Sell</router-link></div>
        <div class="table-row"><span class="table-item">Stock # 4</span><span class="table-item">Ticker</span><span>Shares</span><span class="table-item"></span><span class="table-item">Total$</span><router-link v-bind:to="{name: 'stock'}" class="button-small buysell-button">Buy/Sell</router-link></div>
      </div>
    </div>
  </div>

</template>

<script>
import UserHeader from '@/components/UserHeader'

export default {
  name: 'portfolio',
  components: {
    UserHeader
  },
  data() {
        return {
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
        }
    },
  methods: {
    
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