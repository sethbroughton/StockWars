<template>
  <div id="open-games" class="table">
    <div class="table-header">
      <h2 class = "table-title">Open Games</h2>
      <div class="table-buttons">
        <router-link v-bind:to="{name: 'create-game'}" id="create-game" class="button-small">Create a New Game</router-link>
      </div>
    </div>
    <div v-for="game in activeGames" :key="game.gameId" class="table-row">
      <span class="table-item">{{game.name}}</span>
      <span class="table-item">{{game.organizer_id}}</span>
      <button class="button-small">Join</button>
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
  }
}
</script>

<style scoped>
.game-row {
border-bottom: solid;
padding-bottom: 1rem;
border-color: steelblue;
}

.button-small {
  margin-left: 8.5rem;
}


.header {
  display: flex;
  justify-content: space-between;
  border: solid 5px;
  border-color: steelblue;
 
}
.open-games-list {
  height: 80%;
}

.join-game-btn {
  border-radius: 15px 50px 30px 5px;
  
}
.new-game-btn {
  border-radius: 15px 50px 30px 5px;
}

ul {
  list-style: none;
  
} 

</style>
