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
          data: []

        }
    },
  components: {
    Routes,
    UserHeader
  }, 
    created() {
      fetch('https://fmpcloud.io/api/v3/quote/AAPL?apikey=a7f7aa92b83ad64df4f2f540028a9880')
        .then((response) => {
          return response.json();
        })
        .then((data) => {
          this.data = data

        });
    }
}
</script>