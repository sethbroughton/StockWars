<template>
  <div id="active-games" class="table">
    <div class="table-header">
      <h3 class="table-title">Active Games</h3>
      <div class="table-buttons">
        <router-link v-bind:to="{name: 'create-game'}" id="create-game" class="button-small">Create a New Game</router-link>
        <router-link v-bind:to="{name: 'lobby'}" id="join-game" class="button-small">New and Pending</router-link>
      </div>
    </div>
    <div v-for="game in activeGames" :key="game.gameId" class="table-row">
      <router-link v-bind:to="{name: 'game', params: {gameId: game.gameId}}" class="button-small go-to-game">{{game.name}}</router-link>      
      <span class="table-item">[CURRENT BALANCE]</span>
      <span class="table-item">Position: X/X</span>
      <span class="table-item">XX Days Left</span>
    </div>
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

<style scoped>

#create-game { background-color: var(--color-complementary-2);}
#create-game:hover { background-color: var(--color-complementary-1);}

#join-game { background-color: var(--color-tertiary-2); }
#join-game:hover { background-color: var(--color-tertiary-1); }

.go-to-game { background-color: var(--color-complementary-2);}
.go-to-game:hover { background-color: var(--color-complementary-1);}

</style>
