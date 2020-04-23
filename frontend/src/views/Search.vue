<template>
  <div id="search">
    <user-header></user-header>
    <routes/>

<h1>Search</h1>
 <form @submit.prevent="search" class="form u-margin-bottom">
        <div class="input-fields">
          <div class="form-group u-margin-bottom-small">
            <label for="search" class="label">Ticker Symbol</label>
            <input
              type="text"
              id="search"
              v-model="query"
              autofocus
            />
          </div>
        </div>
      <button type="submit" id="search" class="button">Search</button>
      </form>

    <h3>{{data[0].name}}</h3>


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
          query: '',
          apikey: 'a7f7aa92b83ad64df4f2f540028a9880'
        }
    },
  methods: {
    search(){
      const query = this.query
      console.log(query);
      const apikey = this.apikey
      console.log(apikey);
      fetch(`https://fmpcloud.io/api/v3/quote/${query}?apikey=${apikey}`)
        .then((response) => {
          return response.json();
        })
        .then((data) => {
          this.data = data;
        })
  }
  },
  }
</script>