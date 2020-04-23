<template>
  <div id="search">
    <user-header></user-header>
    <h1>Search</h1>
    
    <p>{{data[0].name}}</p>

    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'

export default {
  name: 'search',
    data() {
        return {
          data: [{
            name: ''
          }],
          query: 'AAPL',
          apikey: 'a7f7aa92b83ad64df4f2f540028a9880'
        }
    },
  components: {
    Routes,
    UserHeader
  }, 
    created() {
      const query = this.query
      const apikey = this.apikey
      console.log(apikey);
      fetch(`https://fmpcloud.io/api/v3/quote/${query}?apikey=${apikey}`
      )
        .then((response) => {
          return response.json();
        })
        .then((data) => {
          this.data = data
          console.log('hi')

        });
    }
}
</script>