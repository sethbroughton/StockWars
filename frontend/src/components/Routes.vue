<template>
  <div class="routes">
    <router-link to="/">Profile</router-link>
    <router-link to="/create">Create Game</router-link>
    <router-link to="/lobby">Lobby</router-link>
    <router-link to="/game">Game</router-link>
    <router-link to="/portfolio">Portfolio</router-link>
    <router-link to="/search">Search</router-link>
    <router-link to="/tradeHistory">History</router-link>
    <router-link to="/stock">Stock</router-link>
  </div>
</template>

<script>
export default {
  name: 'routes'
}
</script>

<style scoped>
  a {
    color: rgb(var(--color-grey-light-1));
    margin-right: 1rem;
  }
</style>