<template>
  <div id="pending-games">
    <h2 class = "pending-title">Pending Games</h2>
    <ul class="pending-games-list">
      <li v-for="game in pendingGames" :key="game.gameId">
        {{game.name}} - Organizer: {{game.organizerId}}
        - Players: ((# of players)/(max # of players)) -
        {{game.lengthInDays}} Days
        <button class="start-game-btn">Start Game</button>
      </li>
    </ul>
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

  #pending-games {
    color: var(--color-grey-light-1);
    background-color: var(--color-grey-dark-1);
    margin: 3%;
    padding: 2%;
    width: 500px;
    height: 200px;
  }

  .header {
    display: flex;
    justify-content: space-between;
  }
  .pending-games-list {
    border:solid;
    height: 80%;
  }

  ul {
    list-style: none;
  }
  .pending-title {
    border:solid;
  }

</style>
