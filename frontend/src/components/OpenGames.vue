<template>
  <div id="open-games">
    <div class="header">
      <h2 class = "open-title">Open Games</h2>
      <button class="button-small">Create New Game</button>
    </div>
    <ul class="open-games-list">
      <li class = "game-row" v-for="game in openGames" :key="game.gameId">
        {{game.name}} - Organizer: {{game.organizerId}}
        - Players: ((# of players)/(max # of players)) -
        {{game.lengthInDays}} Days
        <button class="button-small">Join</button>
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

#open-games {
  color: var(--color-grey-light-1);
  background-color: var(--color-primary-2);
  margin: 3%;
  padding: 2%;
  width: 70%;
  font-size: 3rem;
  font-weight: 500;
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
