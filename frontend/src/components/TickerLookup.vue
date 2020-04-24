<template>
  <div>
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
  </div>
</template>

<script>
export default {
  name: 'ticker-lookup',
  data() {
        return {
        companyFragment: '',
          companySearch: [{
            symbol: '',
            securityName: ''
          }]
        }
    },
  methods: {
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

<style>

</style>
