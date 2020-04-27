<template>
  <div id="stock">
    <user-header></user-header>
     <routes/>  
     <!-- <ticker-lookup></ticker-lookup> -->

      <form @submit.prevent="stockQuote" class="form u-margin-bottom">
        <div class="input-fields">
          <div class="form-group u-margin-bottom-small">
            <label for="search" class="label">Search by Ticker</label>
            <input
              type="text"
              id="search"
              v-model="trade.ticker"
              autofocus
              required
            />
          </div>
        </div>
      <button type="submit" id="search" class="button">Search</button>
      </form>

      <h1>{{quote.companyName}} ({{quote.symbol}})</h1>
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

      <form @submit.prevent="tradeStock" class="form u-margin-bottom">
        <div class="input-fields">
          <div class="form-group u-margin-bottom-small">
            <label for="quantityOfShares" class="label">Quantity of Shares</label>
            <input
              type="number"
              v-model="trade.quantity"
              required
              autofocus
            />
            <select v-model="trade.type">
             <option disabled value="">Please select one</option>
              <option>BUY</option>
              <option>SELL</option>
            </select>
          </div>
        </div>
          <button v-if="trade.type==='BUY'" type="submit" id="" class="button">BUY [${{stockValue}}]</button>
          <button v-if="trade.type==='SELL'" type="submit" id="" class="button">SELL [${{stockValue}}]</button>
      </form>
     

      <!-- <div class="buy-sell-buttons">
        <input type="text" id="buy-shares" name="buy-shares"/>
        <label for="num-shares">Shares</label>
        <p><button class = "buy-btn">BUY</button></p>
        <input type="text" id="sell-shares" name="sell-shares"/>
        <label for="num-shares">Shares</label>        
        <p><button class = "sell-btn"> SELL</button></p>
    </div> -->
    
    <!-- <stock-chart></stock-chart> -->
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
            latestPrice: 0,
            symbol: '',
            companyName: ''
          },
          company: {
            symbol: '',
            companyName: '',
            exchange: '', 
            industry: '',
            website: '',
            description: '', 
          },
          trade: {
            portfolioId: 1,  //Change routing so you must go to portfolio page to then navigate to buy/sell
            type: '',
            ticker: '', //From search stock query
            quantity: 0, //Form input
            stockValue: 0,
            commission: 19.99 //Standard commission
          }, 
          timePeriod: '1m',
          priceDataPoints: [{
            close: ''
          }],
          query: ''
        }
    },
  computed: {
    stockValue() {
      return this.quote.latestPrice * this.trade.quantity;
    }

  },
  created(){
    
    },
    methods: {
      //POST a trade
      tradeStock(){
        this.trade.stockValue = this.quote.latestPrice * this.trade.quantity; 
        const authToken = auth.getToken();
        fetch(`${process.env.VUE_APP_REMOTE_API}/api/trade`,{
          method: 'POST',
          headers:{
          Authorization: `Bearer ${authToken}`,
          'Content-Type': 'application/json',
          'Accept': 'application/json'
          },
          body: JSON.stringify(this.trade)
        })
        .then((response) => {
          if(response.ok){
            this.$router.push('/portfolio')
          }
        })
        .catch((err) => console.error(err));
      },

      //Get latest stock price and related company information
      stockQuote(){
        const query = this.trade.ticker;
        fetch(`https://cloud.iexapis.com/stable/stock/${query}/quote?token=${process.env.VUE_APP_API_KEY}`)
          .then((response) => {
            return response.json();
          })
        .then((quote) => {
          this.quote = quote;
        })
      fetch(`https://cloud.iexapis.com/stable/stock/${query}/quote?token=${process.env.VUE_APP_API_KEY}`)
        .then((response) => {
          return response.json();
        })
        .then((quote) => {
          this.quote = quote;
        })

      //GET /stock/{symbol}/company
      fetch(`https://cloud.iexapis.com/stable/stock/${query}/company?token=${process.env.VUE_APP_API_KEY}`)
        .then((response) => {
          return response.json();
        })
        .then((company) => {
          this.company = company;
        })

      // const timePeriod = this.timePeriod
      // fetch(`https://cloud.iexapis.com/v1/stock/AAPL/chart/${timePeriod}?token=${process.env.VUE_APP_API_KEY}`)
      //   .then((response) => {
      //     return response.json();
      //   })
      //   .then((priceDataPoints) => {
      //     this.priceDataPoints = priceDataPoints;
      //     console.log(this.priceDataPoints[0].close)
      //   })

    }
    }
}
</script>