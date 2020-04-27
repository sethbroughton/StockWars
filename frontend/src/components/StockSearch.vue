<template>
  <div id="stock">
    <div class="search">
      <form @submit.prevent="stockQuote" class="form u-margin-bottom-small">
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
        <button type="submit" id="search-button" class="button">Search</button>
      </form>

      <div id="stock-info">
        <h3 class="stock-name u-margin-bottom-small">{{quote.companyName}} ({{quote.symbol}})</h3>
        <ul>          
          <li> Current Price: ${{quote.latestPrice}}</li>
          <li> Company Name: {{company.companyName}} ({{company.symbol}})</li>
          <li> Website: {{company.website}} </li>
          <li> Primary Exchange: {{company.exchange}} </li>
          <li> Industry: {{company.industry}} </li>
          <!-- JEFF: THERE'S A LOT OF TEXT HERE, NOT SURE IF/HOW TO WORK IT IN -->
          <!-- <li> Description: {{company.description}} </li> -->
        </ul> 
      </div>

    </div>
    <form @submit.prevent="tradeStock" id="buy-sell" class="form u-margin-bottom">
      <div class="table-header u-margin-bottom-small">
        You own (XX) shares
      </div>
      <div class="input-fields">
        <div class="form-group u-margin-bottom-small trade-input">
          <input
            type="number"
            v-model="trade.quantity"
            required
            autofocus
          />
          <select v-model="trade.type">
            <option disabled value="">Select</option>
            <option>BUY</option>
            <option>SELL</option>
          </select>
        </div>
      </div>
      <button v-if="trade.type==='BUY'" type="submit" id="" class="button trade-button">BUY [${{stockValue}}]</button>
      <button v-if="trade.type==='SELL'" type="submit" id="" class="button trade-button">SELL [${{stockValue}}]</button>
    </form>

  </div>
</template>

<script>
import auth from '../auth';

export default {
  name: 'stock',
  components: {

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
      query: '',
      portfolio: this.$parent.portfolio
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
          this.$router.push('/portfolio/' + this.portfolio.portfolioId);
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

<style scoped>

ul {
  text-align: center;
}

li {
  list-style: none;
  color: var(--color-grey-light-1);
  font-size: 2rem;
  font-weight: 600;
}

#stock {
  display: flex;
  align-items: center;
}

.search {
  margin: 1rem;

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

.form-group > * {
  padding: .5rem 1rem;
}

.trade-input {
  display: flex;
  justify-content: center;
}

.trade-input > * {
  width: 30%;
  margin: 0 2%;
  text-align: center;
}

#buy-sell {
  margin: 1rem;
}

#search-button { background-color: var(--color-complementary-2); }
#search-button:hover { background-color: var(--color-complementary-1); }

.trade-button { background-color: var(--color-tertiary-2); }
.trade-button:hover { background-color: var(--color-tertiary-1); }

</style>