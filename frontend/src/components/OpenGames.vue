<template>
  <div id="open-games">
    <div class="header">
      <h2>Open Games</h2>
      <button class="new-game-btn">Create New Game</button>
    </div>
    <ul class="open-games-list">
      <li v-for="game in openGames" :key="game.gameId">
        {{game.name}} - Organizer: {{game.organizerId}}
        - Players: ((# of players)/(max # of players)) -
        {{game.lengthInDays}} Days
        <button class="join-game-btn">Join</button>
      </li>
    </ul>
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
    fetch(`${process.env.VUE_APP_REMOTE_API}/api/games`,{
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

#open-games {
  color: var(--color-grey-light-1);
  background-color: var(--color-grey-dark-1);
  margin: 3%;
  padding: 2%;
}

.header {
  display: flex;
  justify-content: space-between;
  border: solid;
}
.open-games-list {
  border:solid;
}

ul {
  list-style: none;
}

</style>
