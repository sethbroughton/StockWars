<template>
  <div id="stock">
    <user-header></user-header>
      <h1>stock name(ticker symbol)</h1>
      <h3>you own (XX) shares</h3>
        <ul>          
          <li> (Graph) </li>
          <li> current: (stock price) </li>
        </ul>  
      <div class="buy-sell-buttons">
        <input type="text" id="buy-shares" name="buy-shares"/>
        <label for="num-shares">Shares</label>
        <p><button class = "buy-btn">BUY</button></p>
        <input type="text" id="sell-shares" name="sell-shares"/>
        <label for="num-shares">Shares</label>        
        <p><button class = "sell-btn"> SELL</button></p>
    </div>
  <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'

export default {
  name: 'stock',
  components: {
    Routes,
    UserHeader
  }
}
</script>