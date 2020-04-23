<template>
  <div id="lobby">> 
    <h1>Lobby</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
    <game-lobby/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import GameLobby from '@/components/GameLobby'

export default {
  name: 'lobby',
  components: {
    Routes,
    GameLobby
  }
}
</script>

<style>
#lobby {
  color: var(--color-grey-light-1);
  font-size: 4rem;
}
</style>