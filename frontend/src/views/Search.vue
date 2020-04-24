<template>
  <div id="search">
    <user-header></user-header>
    <routes/>

<h1>Search</h1>
 <form @submit.prevent="stockQuote" class="form u-margin-bottom">
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
         {{quote.latestPrice}}

<ticker-lookup></ticker-lookup>
  
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'
import TickerLookup from '@/components/TickerLookup'

export default {
  name: 'search',
  components: {
    Routes,
    UserHeader,
    TickerLookup
  }, 
  data() {
        return {
          data: [{
            name: '',
            price: ''
          }],
          quote: {
            latestPrice: '',
            symbol: '',
            companyName: ''
          },
          query: '',

        }
    },
  methods: {
    stockQuote(){
    const query = this.query
      fetch(`https://cloud.iexapis.com/stable/stock/${query}/quote?token=${process.env.VUE_APP_API_KEY}`)
        .then((response) => {
          return response.json();
        })
        .then((quote) => {
          this.quote = quote;
        })
  }, 

   },
  }
</script>