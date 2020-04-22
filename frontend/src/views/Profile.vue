<template>
  <div id="profile">
    <h1>Profile</h1>
    <routes/>
    <router-link v-bind:to="{name: 'create-game'}" tag="button">Create a New Game</router-link>
    <router-link v-bind:to="{name: 'lobby'}" tag="button">Join New Game</router-link>
    <leader-board/>
    <active-games/>
    <user-statistics/>
  </div>
</template>

<script>
import Routes from '@/components/Routes';
import LeaderBoard from '@/components/LeaderBoard';
import ActiveGames from '@/components/ActiveGames';
import UserStatistics from '@/components/UserStatistics'

export default {
  name: 'profile',
  components: {
    Routes,
    LeaderBoard, 
    ActiveGames,
    UserStatistics
  }
}
</script>

<style scoped>

#profile {
  color: var(--color-grey-light-1);
  font-size: 4rem;
}

</style>