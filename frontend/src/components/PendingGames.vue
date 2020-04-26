<template>
  <div id="pending-games" class="table">
    <div class="table-header">
      <h3 class="table-title">Pending Games</h3>
    </div>
    <div v-for="game in pendingGames" :key="game.gameId" class="table-row">
      <span class="table-item">{{game.name}}</span>
      <span class="table-item">Organizer: {{game.organizerName}}</span>
      <span class="table-item">Players: X/{{game.numberOfPlayers}}</span>
      <span class="table-item">{{game.lengthInDays}} Days</span>
      
     
      
      <form v-if="game.numberOfPlayers < 50" class="table-item" v-on:click="startGame">Start Game</form> 
      <form v-else> Waiting for Players.. </form>
  

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
  methods: {

    startGame() {
      //my attempt at a start game button, I don't think it works but I
      //did what I could, I started the v-if statment above too, it's definitely incomplete - Kevin
      //tried the button again up above, not sure why it isn't clickable
      const authToken = auth.getToken();
      const fetchConfig = {
        method : "POST",
        headers: {
          Accept: 'application/json',
          'Content-Type' : 'application/json',
          Authorization: `Bearer ${authToken}`
        },
       body: JSON.stringify(this.game)
      }
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/game/${this.pendingGames.gameId}`, fetchConfig)
      .then(response => {
      if (response.ok) {
        this.$router.push('/activeGames')
      }
      })
     .catch((err) => console.error(err));
    }

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
