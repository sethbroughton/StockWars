<template>
  <div id="active-games">
    <div class="table-header">
      <h3 class="table-title">Active Games</h3>
      <div class="table-buttons">
        <router-link v-bind:to="{name: 'create-game'}" id="create-game" class="button-small">Create a New Game</router-link>
        <router-link v-bind:to="{name: 'lobby'}" id="join-game" class="button-small">Join New Game</router-link>
      </div>
    </div>
    <div v-for="game in activeGames" :key="game.gameId" class="table-row">
      {{game.name}}
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

<style>

.table-header {
  padding: 1% 2%;
  background-color: var(--color-blue-4);

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.table-buttons {
  display: flex;
}

.table-title {
  font-size: 3rem;
}

.table-row {
  
}

#create-game { background-color: var(--color-complementary-2);}
#create-game:hover { background-color: var(--color-complementary-1);}

#join-game { background-color: var(--color-tertiary-2); }
#join-game:hover { background-color: var(--color-tertiary-1); }

</style>
