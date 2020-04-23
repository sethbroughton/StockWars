<template>
  <div id="trade-history">
    <user-header></user-header>
    <h1>Trade History</h1>
<<<<<<< HEAD
    <div class="trade-history">
      <button class="return-to-game-btn">Return New Game</button>
      <ul class="trade-history-list">
        <li>
          (name of stock) - ticker symbol - buy or sell 
          - num of shares - purchase price - date purchases
        </li>       
      </ul>
    </div>
=======
    <table> 
      <tr>
        <td> Stock Name </td>
        <td> Stock Code </td>
         <td> Buy/Sell </td>
          <td> Shares </td>
          <td> Value </td>
          <td> Trade Date</td>

      </tr>
      
    </table>
    <routes/>
>>>>>>> 2fa73fb38320b877da75c8cb93642ddc2f02eea5
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'

export default {
  name: 'trade-history',
  components: {
    Routes,
    UserHeader
  }
}
</script>

<style>
#trade-history {
  color: var(--color-grey-light-1);
  font-size: 2rem;
}
</style>