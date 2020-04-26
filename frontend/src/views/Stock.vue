<template>
  <div id="stock">
    <user-header></user-header>
     <routes/>  <ticker-lookup></ticker-lookup>
      <h1>{{quote.companyName}} ({{ticker}})</h1>

 <form @submit.prevent="buyStock" class="form u-margin-bottom">
        <div class="input-fields">
          <div class="form-group u-margin-bottom-small">
            <label for="username" class="label">Quantity of Shares</label>
            <input
              type="number"
              v-model="quantity"
              required
              autofocus
            />
          </div>
        </div>

        <button type="submit" id="" class="button">BUY</button>
      </form>

      <h3>you own (XX) shares</h3>
        <ul>          
          <li> Current Price: ${{quote.latestPrice}}</li>
            <li> Company Name: {{company.companyName}} ({{company.symbol}})</li>
            <li> Website: {{company.website}} </li>
            <li> Primary Exchange: {{company.exchange}} </li>
            <li> Industry: {{company.industry}} </li>
            <li> Description: {{company.description}} 
            </li>
        </ul>  
      <div class="buy-sell-buttons">
        <input type="text" id="buy-shares" name="buy-shares"/>
        <label for="num-shares">Shares</label>
        <p><button class = "buy-btn">BUY</button></p>
        <input type="text" id="sell-shares" name="sell-shares"/>
        <label for="num-shares">Shares</label>        
        <p><button class = "sell-btn"> SELL</button></p>
    </div>
    <stock-chart></stock-chart>


  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'
import TickerLookup from '@/components/TickerLookup'
import StockChart from '@/components/StockChart'
import auth from '../auth';

export default {
  name: 'stock',
  components: {
    Routes,
    UserHeader,
    TickerLookup,
    StockChart
  },
 data() {
        return {
          quote: {
            lastestPrice: '',
            symbol: '',
            companyName: ''
          },
          ticker: 'F',
          company: {
            symbol: '',
            companyName: '',
            exchange: '', 
            industry: '',
            website: '',
            description: '', 
          },
          timePeriod: '1m',
          priceDataPoints: [{
            close: ''
          }],
          trade: {
            "portfolioId": 1,
            "type": "BUY",
            "ticker": "AAPL",
            "quantity": 10,
            "stockValue": 190,
            "commission": 10
          }


        }
    },
  created(){
    const ticker = this.ticker
      fetch(`https://cloud.iexapis.com/stable/stock/${ticker}/quote?token=${process.env.VUE_APP_API_KEY}`)
        .then((response) => {
          return response.json();
        })
        .then((quote) => {
          this.quote = quote;
        })

      //GET /stock/{symbol}/company
      fetch(`https://cloud.iexapis.com/stable/stock/${ticker}/company?token=${process.env.VUE_APP_API_KEY}`)
        .then((response) => {
          return response.json();
        })
        .then((company) => {
          this.company = company;
        })

      const timePeriod = this.timePeriod
      fetch(`https://cloud.iexapis.com/v1/stock/AAPL/chart/${timePeriod}?token=${process.env.VUE_APP_API_KEY}`)
        .then((response) => {
          return response.json();
        })
        .then((priceDataPoints) => {
          this.priceDataPoints = priceDataPoints;
          console.log(this.priceDataPoints[0].close)
        })
    },
    methods: {
      //Create a buy trade
      buyStock(){
        const authToken = auth.getToken();
        fetch(`${process.env.VUE_APP_REMOTE_API}/trade`,{
          method: 'POST',
          headers:{
          Authorization: `Bearer ${authToken}`,
          'Content-Type': 'applicatoin/json',
          'Accept': 'application/json'
          },
          body: JSON.stringify(this.trade)
        })
        .then((response) => {
          return response.json();
        })
        .then((success) => {
          console.log(success)
        });

      }



      

    }



}
</script>