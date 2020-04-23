<template>
  <div id="lobby">
    <user-header></user-header>
    <div class = "lobby-of-game">
    <h1>New Stock Wars</h1>

    <game-lobby/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import GameLobby from '@/components/GameLobby'
import UserHeader from '@/components/UserHeader'

export default {
  name: 'lobby',
  components: {
    Routes,
    GameLobby,
    UserHeader
  }
}
</script>

<style>
#lobby {
  color: var(--color-grey-light-1);
  font-size: 4rem;
}
</style>