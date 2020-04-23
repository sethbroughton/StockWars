<template>
  <div id="game">
    <user-header></user-header>
    <h1>Game</h1>
    <p>You must be authenticated to see this</p>
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