<template>
  <div id="pending-games" class="table">
    <div class="table-header">
      <h3 class="table-title">Pending Games</h3>
    </div>
    <div v-for="game in pendingGames" :key="game.gameId" class="table-row">
      <button class="button-small start-game" v-on:click="startGame(game.gameId)">Start</button>
      <router-link class="button-small invite" :to="{ name: 'invite' }" >Invite</router-link>
      <span class="table-item">{{game.name}}</span>
      <span class="table-item">Organizer: {{game.organizerName}}</span>
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
  methods: {

    startGame() {
      //my attempt at a start game button, I don't think it works but I
      //did what I could, I started the v-if statment above too, it's definitely incomplete - Kevin
      //tried the button again up above, not sure why it isn't clickable
      const authToken = auth.getToken();
      const fetchConfigPut = {
        method : "PUT",
        headers: {
          Authorization: `Bearer ${authToken}`,
          'Content-Type' : 'application/json',
          'Accept': 'application/json'
        },
       body: JSON.stringify(this.game)
      }
    //creating a new portfolio for the start game button
      const fetchConfigPost = {
        method: "POST",
        headers: {
          Authorization: `Bearer ${authToken}`,
          'Content-Type' : 'application/json',
          'Accept': 'application/json'
        },
        body: JSON.stringify(this.game)
      }
      
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/game/${this.pendingGames.gameId}`, fetchConfigPut)
      .then((response) => {
        if(response.ok) {
          return response.json();
        }
      })
     .catch((err) => console.error(err));

      fetch(`${process.env.VUE_APP_REMOTE_API}/api/portfolio/`, fetchConfigPost)
      .then(response => {
        if (response.ok) {
          this.router.push('/activeGames')
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

<style scoped>

.start-game { background-color: var(--color-complementary-2); }
.start-game:hover { background-color: var(--color-complementary-1); }

.invite { background-color: var(--color-tertiary-2); }
.invite:hover { background-color: var(--color-tertiary-1); }

</style>
