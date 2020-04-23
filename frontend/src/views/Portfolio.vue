<template>
  <div id="portfolio">
    <div class = "header">
      <h3>Username</h3>
        <li>Avatar</li>
    </div>

    <div class = "return-to-game-btn">
        <button>Return to Game</button>
    </div>

    <div class = "portfolio-information">
      <h1>Portfolio</h1>
      <h1>Balance</h1>
        <p class = "stock-information">
          <ul>
            <li>Stock # 1 - Ticker - Shares - Current$ - Total$</li>
            <li>Stock # 2 - Ticker - Shares - Current$ - Total$</li>
            <li>Stock # 3 - Ticker - Shares - Current$ - Total$</li>
            <li>Stock # 4 - Ticker - Shares - Current$ - Total$</li>
          </ul>
        </p>


    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'

export default {
  name: 'portfolio',
  components: {
    Routes
  },
  data() {
    return {}
  }
}
</script>

<style scoped>

</style>