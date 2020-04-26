<template>
  <div id="open-games" class="table">
    <div class="table-header">
      <h3 class = "table-title">Open Games</h3>
      <div class="table-buttons">
        <router-link v-bind:to="{name: 'create-game'}" id="create-game" class="button-small">Create a New Game</router-link>
      </div>
    </div>
    <div v-for="game in openGames" :key="game.gameId" class="table-row">
      <span class="table-item">{{game.name}}</span>
      <span class="table-item">Organizer: {{game.organizerName}}</span>
      <span class="table-item">Players: X/{{game.numberOfPlayers}}</span>
      <span class="table-item">{{game.lengthInDays}} Days</span>
      <button class="button-small join-button" v-on:click="joinGame(game.gameId)">Join</button>
    </div>
  </div>
</template>

<script>
import auth from '../auth';

export default {
  name: 'open-games',
  data() {
    return {
      openGames: []
    };
  },
  created() {
    const authToken = auth.getToken();
    fetch(`${process.env.VUE_APP_REMOTE_API}/api/availableGames`,{
       method: 'GET',
       headers:{
        Authorization: `Bearer ${authToken}`
        }
    })
      .then(response => {
        return response.json();
      })
      .then((games) => {
        this.openGames = games;
      })      
      
      .catch(err => console.log(`Error fetching reviews ${err}`));
  },
  methods: {
    joinGame: function(gameId) {
      console.log(gameId);
      const authToken = auth.getToken();
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/joinGame`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          Authorization: `Bearer ${authToken}`
        },
        body: gameId
      })
      .then((response) => {
        if(response.ok) {
           this.$router.push('/lobby');
        }
      })
      .catch((err) => console.error(err));

      location.reload();
    }
  }
}
</script>

<style scoped>

.join-button { background-color: var(--color-tertiary-2); }
.join-button:hover { background-color: var(--color-tertiary-1); }

#create-game { background-color: var(--color-complementary-2); }
#create-game:hover { background-color: var(--color-complementary-1); }

</style>
