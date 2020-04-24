<template>
  <div id="active-games">
    <p v-for="game in activeGames" :key="game.gameId">
      {{game.name}}
    </p>
  </div>
</template>

<script>
import auth from '../auth';

export default {
  name: 'active-games',
  data() {
    return {
      activeGames: []
    };
  },
  created() {
  const authToken = auth.getToken();
  fetch(`${process.env.VUE_APP_REMOTE_API}/api/activeGames`,{
      method: 'GET',
      headers:{
      Authorization: `Bearer ${authToken}`
      }
  })
    .then(response => {
      return response.json();
    })
    .then((games) => {
      this.activeGames = games;
    })
    .catch(err => console.log(`Error fetching reviews ${err}`));
  }
}
</script>

<style>

</style>
