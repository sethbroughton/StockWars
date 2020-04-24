<template>
  <div id="portfolio">
    <user-header/>

    <div class = "return-to-game-btn">
        <button>Return to Game</button>
    </div>

    <div class = "portfolio-information">
      <h1>Portfolio</h1>
      <h1>Balance</h1>
        <p class = "stock-information"> 
          <ul>
            <li>Stock # 1 - Ticker - Shares - {{quotes.AAPL.price}} - Total$</li>
            <li>Stock # 2 - Ticker - Shares - Current$ - Total$</li>
            <li>Stock # 3 - Ticker - Shares - Current$ - Total$</li>
            <li>Stock # 4 - Ticker - Shares - Current$ - Total$</li>
          </ul>
        </p>
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

</style>