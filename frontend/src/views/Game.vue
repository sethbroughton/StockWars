<template>
  <div id="game">
    <user-header></user-header>
      <div class = "game-statistics">
        <ul>
          <li> Balance: ($$) </li>
          <li> (#) Days Left </li>
            <div class = "graph">
              <li> (Graph) </li>
            </div>
        </ul>
      </div>
      <div class = "nav-right-chat">
        <li>Chat Box Form</li>
      </div>

      <div class = "users-playing">
        <ul>
          <li> (Me) - (User2) - (User3, etc..) </li>
        </ul>

      <div class = "current-user-tools">
        <ul>
          <li><button class = "portfolio-btn"> Portfolio</button></li>
          <li><button class = "search-stocks-btn"> Search Stocks</button></li>
          <li><button class = "history-btn">History</button></li>
        </ul>
        </div>
      </div>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'

export default {
  name: 'game',
  components: {
    Routes,
    UserHeader
  }
}
</script>

<style scoped>
.game-statistics {
  
}
</style>