<template>
  <div id="trade-history">
    <user-header></user-header>
    <div class="container">
      <h1 class="u-margin-bottom">Trade History</h1>

      <table class="trade-history table u-margin-bottom">
        <tr class="table-header">
          <!-- <span class="table-header-item">Stock Name</span> -->
          <td class="table-header-item">Ticker</td>
          <td class="table-header-item">Buy/Sell </td>
          <td class="table-header-item">Shares</td>
          <td class="table-header-item">Value</td>
          <td class="table-header-item">Date</td>
        </tr>

        <tr v-for="trade in trades" v-bind:key="trade.tradeId" class="table-row">
          <!-- <td class="table-item">Name</td> -->
          <td class="table-item">{{trade.ticker}}</td>
          <td class="table-item">{{trade.type}} </td>
          <td class="table-item">{{trade.quantity}}</td>
          <td class="table-item">${{trade.stockValue}}</td>
          <td class="table-item">{{trade.dateOfPurchase.month}}, {{trade.dateOfPurchase.dayOfMonth}} {{trade.dateOfPurchase.year}} </td>
        </tr>
      </table>

       <router-link v-bind:to="{name: 'game', params: {gameId: gameId} }" class="button return-to-game">Return to Game</router-link>
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
      trades: [],
      gameId: '',
      portfolio: {
        portfolioId : this.$route.params.portfolioId,
      }
    }
  },

  methods: {
    
    returnToGame() {

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

    fetch(`${process.env.VUE_APP_REMOTE_API}/api/trades/${this.portfolio.portfolioId}`, fetchConfig)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        this.trades= data;
        console.log('hi')
      })

    fetch(`${process.env.VUE_APP_REMOTE_API}/api/gameByPortfolio/${this.portfolio.portfolioId}`, fetchConfig)
       .then((response) => {
        return response.json();
      })
        .then((data) => {
          this.gameId = data;
          console.log(this.gameId);
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

.table-header > * {
  font-size: 2.5rem;
}

.table-row {
  width: 100%;
  padding: 1%;
}

td {
  text-align: center;
  padding: 1%;
}

.return-to-game { background-color: var(--color-complementary-2); }
.return-to-game:hover { background-color: var(--color-complementary-1); }

</style>