<template>
  <div id="search">
    <user-header></user-header>
    <h1>Search</h1>
    <p>{{quote["05. price"]}}</p>

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
        
            quote: ''
        }
    },
  components: {
    Routes,
    UserHeader
  }, 
    created() {
      fetch('https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=F&apikey=PM87ANKO1HGLIBFA')
        .then((response) => {
          return response.json();
        })
        .then((quote) => {
          this.quote = quote;
          console.log('hi');
        });
    }
}
</script>