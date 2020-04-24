<template>
  <div id="trade-history">
    <user-header></user-header>
    <h1>Trade History</h1>

    <div class="trade-history">
      <button class="return-to-game-btn">Return New Game</button>
      <table class="trade-history-list" >
        <table>
          <tr> 
            <th>Stock Name</th>
            <th>Ticker</th>
            <th>Buy/Sell </th>
            <th>Shares</th>
            <th>Value</th>
            <th>Date</th>
          </tr>
          <tr v-for="trade in trades" v-bind:key="trade.tradeId">
            <td> Name </td>
            <td> {{trade.ticker}}</td>
            <td> {{trade.type}} </td>
            <td>{{trade.quantity}}</td>
            <td>${{trade.stockValue}}</td>
            <td>{{trade.dateOfPurchase.month}}, {{trade.dateOfPurchase.dayOfMonth}}{{trade.dateOfPurchase.year}} </td>
             </tr>
          
        </table>       
      </table>
      <routes/>
    </div>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'
import auth from '../auth'

export default {
  name: 'trade-history',

  components: {
    Routes,
    UserHeader
  }, 

  data() {

    return {
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

<style>
#trade-history {
  color: var(--color-grey-light-1);
  font-size: 2rem;
}
</style>