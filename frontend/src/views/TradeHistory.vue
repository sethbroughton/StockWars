<template>
  <div id="trade-history">
    <user-header></user-header>
    <h1>Trade History</h1>
    <div class="trade-history">
      <button class="return-to-game-btn">Return New Game</button>
      <ul class="trade-history-list">
        <li>
          (name of stock) - ticker symbol - buy or sell 
          - num of shares - purchase price - date purchases
        </li>       
      </ul>
    </div>
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