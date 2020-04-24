<template>
  <div id="pending-games" class="table">
    <div class="table-header">
      <h3 class="table-title">Pending Games</h3>
    </div>
    <div v-for="game in pendingGames" :key="game.gameId" class="table-row">
      <span class="table-item">{{game.name}}</span>
      <span class="table-item">Organizer: {{game.organizerId}}</span>
      <span class="table-item">Players: X/{{game.numberOfPlayers}}</span>
      <span class="table-item">{{game.lengthInDays}} Days</span>
    </div>
  </div>
</template>

<script>
import auth from '../auth'

export default {
  name: 'pending-games',
  data() {
    return {
      pendingGames: []
    };
  },
  created() {
    const authToken = auth.getToken();
    fetch(`${process.env.VUE_APP_REMOTE_API}/api/pendingGames`,{
       method: 'GET',
       headers:{
        Authorization: `Bearer ${authToken}`
        }
    })
      .then(response => {
        return response.json();
      })
      .then((games) => {
        this.pendingGames = games;
      })
      .catch(err => console.log(`Error fetching reviews ${err}`));
  }

}
</script>

<style>

</style>
