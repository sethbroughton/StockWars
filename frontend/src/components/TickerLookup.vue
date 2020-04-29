<template>
  <div id="ticker-lookup">

    <form @submit.prevent="tickerSearch" class="form u-margin-bottom-small">
      <div class="input-fields">
          <div class="form-group u-margin-bottom-small">
              <label for="search" class="label">Company</label>
              <input
              type="text"
              id="search"
              v-model="companyFragment"
              autofocus
              required
              />
          </div>
      </div>
      <button type="submit" id="search-button" class="button">Search</button>
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

<style scoped>


ul {
  text-align: center;
}

li {
  list-style: none;
  color: var(--color-grey-light-1);
  font-size: 1.5rem;
  font-weight: 600;
}

#search-button {background-color: var(--color-complementary-2);}
#search-button:hover {background-color: var(--color-complementary-1);}

</style>
