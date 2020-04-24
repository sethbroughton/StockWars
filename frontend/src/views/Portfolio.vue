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
            <li>Stock # 1 - Ticker - Shares - {{quote.latestPrice}} - Total$</li>
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
          quote: {
            price: '',
            symbol: '',
            companyName: ''
          },
          query: 'AAPL',
        }
    },
  methods: {
    
  },
  created(){
    const query = this.query
      fetch(`https://cloud.iexapis.com/stable/stock/${query}/quote?token=${process.env.VUE_APP_API_KEY}`)
        .then((response) => {
          return response.json();
        })
        .then((quote) => {
          this.quote = quote;
        })
}
}

</script>

<style scoped>

</style>