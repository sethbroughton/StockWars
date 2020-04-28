<template>
  <div id="trade-history">
    <user-header></user-header>
    <div class="container">
      <h1 class="u-margin-bottom">Trade History</h1>

      <div class="trade-history table u-margin-bottom">
        <div class="table-header">
          <span class="table-header-item">Stock Name</span>
          <span class="table-header-item">Ticker</span>
          <span class="table-header-item">Buy/Sell </span>
          <span class="table-header-item">Shares</span>
          <span class="table-header-item">Value</span>
          <span class="table-header-item">Date</span>
        </div>

        <div v-for="trade in trades" v-bind:key="trade.tradeId" class="table-row">
          <span class="table-item">Name</span>
          <span class="table-item">{{trade.ticker}}</span>
          <span class="table-item">{{trade.type}} </span>
          <span class="table-item">{{trade.quantity}}</span>
          <span class="table-item">${{trade.stockValue}}</span>
          <td>{{trade.dateOfPurchase.month}}, {{trade.dateOfPurchase.dayOfMonth}}{{trade.dateOfPurchase.year}} </td>
        </div>
      </div>

      <button class="button return-to-game">Return to Game</button>
    </div>

  </div>
</template>

<script>
import UserHeader from '@/components/UserHeader'
import auth from '../auth'

export default {
  name: 'trade-history',

  components: {
    UserHeader
  }, 
  data() {

    return {
      user: this.$parent.user,
      trades: []
    }

  },
  created() {
     const authToken = auth.getToken();
     const fetchConfig = {
       method: 'GET',
       headers:{
        Authorization: `Bearer ${authToken}`
        }
    }
    fetch(`${process.env.VUE_APP_REMOTE_API}/api/tradeHistory`, fetchConfig)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        this.trades= data;
      })
  }
  
}
</script>

<style scoped>

.container {
  padding: 2%;

  display: flex;
  flex-direction: column;
  align-items: center;
}

h1 {
  font-size: 5rem;
  color: var(--color-grey-light-1); 
}

.return-to-game { background-color: var(--color-complementary-2); }
.return-to-game:hover { background-color: var(--color-complementary-1); }

</style>