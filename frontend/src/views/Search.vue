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

      <!-- <form @submit.prevent="findCompany" class="form u-margin-bottom">
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
        </div>
      <button type="submit" id="search" class="button">Search</button>
      </form> -->


  <form @submit.prevent="tickerSearch" class="form u-margin-bottom">
        <div class="input-fields">
          <div class="form-group u-margin-bottom-small">
            <label for="search" class="label">Find a Ticker</label>
            <input
              type="text"
              id="search"
              v-model="companyFragment"
              autofocus
              required
            />
          </div>
        </div>
      <button type="submit" id="search" class="button">Search</button>
      </form>

      <ul>
        <li v-for="company in companySearch"
          v-bind:key="company.symbol">
          {{company.symbol}}
          {{company.securityName}}
        </li>
      </ul>
         

    {{quote.latestPrice}}
    {{company.symbol}}
    {{company.companyName}}
    {{company.exchange}}
    {{company.industry}}
    {{company.website}}
    {{company.description}}
    
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
            latestPrice: '',
            symbol: '',
            companyName: ''
          },
          query: '',
          company: {
            symbol: '',
            companyName: '',
            exchange: '', 
            industry: '',
            website: '',
            description: '', 
          },
          companyFragment: '',
          companySearch: [{
            symbol: '',
            securityName: ''
          }]

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
    findCompany(){
      //GET /stock/{symbol}/company
      const query = this.companyQuery
    fetch(`https://cloud.iexapis.com/stable/stock/${query}/company?token=${process.env.VUE_APP_API_KEY}`)
        .then((response) => {
          return response.json();
        })
        .then((company) => {
          this.company = company;
        })
    },
    tickerSearch(){
      //Returns an array of symbols up to the top 10 matches
    const companyFragment = this.companyFragment;
      fetch(`https://cloud.iexapis.com/v1/search/${companyFragment}?token=${process.env.VUE_APP_API_KEY}`)
        .then((response) => {
          return response.json();
        })
        .then((companySearch) => {
          this.companySearch = companySearch;
        })

    }

   },
  }
</script>