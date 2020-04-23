<template>
  <div id="portfolio">
    <user-header/>

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
    </div>
  </div>
  </div>
</template>

<script>
import UserHeader from '@/components/UserHeader'

export default {
  name: 'portfolio',
  components: {
    UserHeader
  },
  data() {
    return {}
  }
}
</script>

<style scoped>

</style>