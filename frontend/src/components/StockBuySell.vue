<template>
  <div id="stock-buy-sell">

    <ticker-lookup/>

    <div class="search">
      <form @submit.prevent="stockQuote" class="form u-margin-bottom-small">
        <div class="input-fields">
          <div class="form-group u-margin-bottom-small">
            <label for="search" class="label">Ticker</label>
            <input
              type="text"
              id="search"
              :value="trade.ticker.toUpperCase()" @input="trade.ticker = $event.target.value.toUpperCase()"
              autofocus
              required
            />
          </div>
        </div>
        <button type="submit" id="search-button" class="button">Details</button>
      </form>

      <div id="stock-info" v-if="lookup">
        <h3 class="stock-name u-margin-bottom-small">{{quote.companyName}} ({{quote.symbol}})</h3>
        <ul>          
          <!-- <li>Website: {{company.website}} </li> -->
          <!-- <li>Primary Exchange: {{company.exchange}} </li> -->
          <li class="u-margin-bottom-small">{{company.industry}} </li>
          <li>${{quote.latestPrice}}</li>
          <!-- JEFF: THERE'S A LOT OF TEXT HERE, NOT SURE IF/HOW TO WORK IT IN -->
          <!-- <li> Description: {{company.description}} </li> -->
        </ul> 
      </div>
    </div>

    <form @submit.prevent="tradeStock" v-if="lookup" id="buy-sell" class="form u-margin-bottom">
      <h3 class="label u-margin-bottom-small">You own {{sharesCount}} shares</h3>
      <div class="input-fields">
        <div class="alert alert-danger u-margin-bottom-small" role="alert" v-if="invalidTrade">
            Invalid Trade
          </div>
        <div class="form-group u-margin-bottom-small trade-input">
          <input
            type="number"
            min=1
            v-model="tradeNumber"
            required
            autofocus
          />
          <select v-model="dollarOrShares" v-on:click="calculateValue">
            <option disabled value="">Select</option>
            <option>Shares</option>
            <option>US Dollars</option>
          </select>
          <select v-model="trade.type">
            <option disabled value="">Select</option>
            <option>BUY</option>
            <option>SELL</option>
          </select>
        </div>
      </div>
      <button v-if="trade.type==='BUY'" type="submit" id="" class="button trade-button">BUY {{formatMoney(trade.stockValue)}}</button>
      <button v-if="trade.type==='SELL'" type="submit" id="" class="button trade-button">SELL {{formatMoney(trade.stockValue)}}</button>
    </form>

  </div>
</template>

<script>
import TickerLookup from '@/components/TickerLookup'
import auth from '../auth';

export default {
  name: 'stock-buy-sell',
  components: {
    TickerLookup
  },
 data() {
    return {
      lookup: false,
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
        portfolioId: this.$parent.portfolio.portfolioId,
        type: '',
        ticker: '', //From search stock query
        quantity: 0, //Form input
        stockValue: 0,
        commission: 19.99 //Standard commission
      }, 
      tradeNumber: '',
      dollarOrShares: '',
      //stockValue: '',
      timePeriod: '1m',
      priceDataPoints: [{
        close: ''
      }],
      query: '',
      portfolio: this.$parent.portfolio, 
      invalidTrade: false,
      sharesCount: 0
    }
  },
  computed: {
    // stockValue() {
    //   return (Math.round(this.quote.latestPrice * this.trade.quantity*100) / 100).toFixed(2)
    // },
  },
  created(){
    
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
    //Calculate Trade Value
    calculateValue(){
      if(this.dollarOrShares == 'Shares'){
        this.trade.quantity = this.tradeNumber;
      } else {
        this.trade.quantity = this.tradeNumber / this.quote.latestPrice;
      }
      this.trade.stockValue = (Math.round(this.quote.latestPrice * this.trade.quantity*100) / 100).toFixed(2)
    },
    
    //POST a trade
    tradeStock(){
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
          this.$router.push('/portfolio/' + this.portfolio.portfolioId);
        } else{
          this.invalidTrade = true;
        }
      })
      .catch((err) => console.error(err));
    },

    //Get latest stock price and related company information
    stockQuote(){

      const authToken = auth.getToken();

      const fetchConfigGet = {
        method: 'GET',
        headers:{
          Authorization: `Bearer ${authToken}`
        }
      }

      const query = this.trade.ticker;
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

      //GET current shares of selected stock
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/stockShares/${this.portfolio.portfolioId}/` + query, fetchConfigGet)
      .then((response) => {
        return response.json();
      })
      .then((shares) => {
        console.log(shares);
        this.sharesCount = shares;
      })

      //Display results and buy/sell options
      this.lookup = true;

      this.$emit('hide-scoreboard', this.lookup);

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

<style scoped>

#stock-buy-sell {
  display: flex;
  justify-content: space-evenly;
  align-items: flex-start;

  width: 70%;
}

#stock-buy-sell > * {
  margin-left: 1rem;
  margin-right: 1rem;
}

.search {
  display: flex;
  flex-direction: column;
}

.stock-name {
  text-align: center;
  color: var(--color-grey-light-1);
  font-size: 3.5rem;
  font-weight: 300;
}

.table-header {
  justify-content: center;
  padding: 1rem 2rem;
  font-size: 2rem;
  font-weight: 700;
}

.trade-input {
  display: flex;
  justify-content: center;
}

.trade-input > * {
  margin: 0 2%;
  text-align: center;
}

ul {
  text-align: center;
}

li {
  list-style: none;
  color: var(--color-grey-light-1);
  font-size: 2.5rem;
  font-weight: 600;
}

#search-button { background-color: var(--color-tertiary-2); }
#search-button:hover { background-color: var(--color-tertiary-1); }

.trade-button { background-color: var(--color-complementary-2); }
.trade-button:hover { background-color: var(--color-complementary-1); }

</style>