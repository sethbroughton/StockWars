<template>
  <div id="lobby">
    <user-header></user-header>
    <h1>Lobby</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
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