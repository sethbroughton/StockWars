<template>
  <div id="search">
    <user-header></user-header>
    <routes/>

<h1>Search</h1>
 <form @submit.prevent="searchStocks" class="form u-margin-bottom">
        <div class="input-fields">
          <div class="form-group u-margin-bottom-small">
            <label for="search" class="label">Search by Ticker</label>
            <input
              type="text"
              id="search"
              v-model="query"
              autofocus
              required
            />
          </div>
        </div>
      <button type="submit" id="search" class="button">Search</button>
      </form>

      <form @submit.prevent="findCompany" class="form u-margin-bottom">
        <div class="input-fields">
          <div class="form-group u-margin-bottom-small">
            <label for="search" class="label">Find a Company</label>
            <input
              type="text"
              id="search"
              v-model="companyQuery"
              autofocus
              required
            />
          </div>
          <div class="form-group u-margin-bottom-small">
            <label for="search" class="label">Select Exchange</label>
            <input
              type="text"
              id="search"
              v-model="stockExchange"
              autofocus
              required
            />
          </div>
        </div>
      <button type="submit" id="search" class="button">Search</button>
      </form>

    

    {{quote.latestPrice}}
    {{quote.companyName}} {{quote.symbol}}

    {{bestMatches[0].name}}
   




  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'

export default {
  name: 'search',
  components: {
    Routes,
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
          query: '',
          companyQuery: '',
          stockExchange: 'NASDAQ',
          bestMatches: [{
            name: '',
            
          }
          ]
        }
    },
  methods: {
    searchStocks(){
      const query = this.query
      console.log(query);
      const apikey = 'sk_3fbcd0adaf6a487c88f78c0027a39813';
     
      fetch(`https://cloud.iexapis.com/stable/stock/${query}/quote?token=sk_3fbcd0adaf6a487c88f78c0027a39813`)
        .then((response) => {
          return response.json();
        })
        .then((quote) => {
          this.quote = quote;
          console.log(quote.latestPrice)
        })
  }, 
    findCompany(){
      console.log('hi')
      const companyQuery = this.companyQuery;
      const stockExchange = this.stockExchange;
      const apikey = 'a7f7aa92b83ad64df4f2f540028a9880';
    fetch(`https://fmpcloud.io/api/v3/search?query=${companyQuery}&limit=10&exchange=${stockExchange}&apikey=a7f7aa92b83ad64df4f2f540028a9880`)
        .then((response) => {
          return response.json();
        })
        .then((bestMatches) => {
          this.bestMatches = bestMatches
        })

    }
   },
  }
</script>