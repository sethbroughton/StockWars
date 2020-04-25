<template>
  <div id="game">
    <user-header></user-header>
      <div class="container">
        <div class="game-stats u-margin-bottom">
          <p class="stat">Balance: $XX,XXX</p>
          <p class="stat">XX Days Left</p>
        </div>
        <div class="progress-graph u-margin-bottom-small">
          [PROGRESS GRAPH]
        </div>
        <div class="legend u-margin-bottom">
          <div class="legend-player">
            <div class="player-color player1"></div>
            <p>PLAYER 1</p>
          </div>
          <div class="legend-player">
            <div class="player-color player2"></div>
            <p>PLAYER 2</p>
          </div>
          <div class="legend-player">
            <div class="player-color player3"></div>
            <p>PLAYER 3</p>
          </div>
        </div>
        <div class="link-boxes">
          <router-link to="/portfolio" id="portfolio" class="link-box">
            Portfolio
          </router-link>
          <router-link to="/search" id="search" class="link-box">
            Search
          </router-link>
          <router-link to="/tradeHistory" id="history" class="link-box">
            History
          </router-link>
        </div>
      </div>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'

export default {
  name: 'game',
  components: {
    Routes,
    UserHeader
  }
}
</script>

<style scoped>

.container {
  padding: 2% 7%;
}

.game-stats {
  width: 100%;
  font-size: 3rem;
  font-weight: 700;
  color: var(--color-grey-light-1);

  display: flex;
  justify-content: space-between;
}

.progress-graph {
  width: 100%;
  padding: 10rem;
  background-color: var(--color-secondary-2);
}

.legend {
  color: var(--color-grey-light-1);
  font-size: 2rem;
  font-weight: 600;

  display: flex;
  justify-content: space-evenly;
}

.legend-player {
  display: flex;
  align-items: center;
}

.player-color {
  width: 2rem;
  height: 2rem;

  margin-right: 1rem;
}

.player1 {
  background-color: var(--color-red-2);
}

.player2 {
  background-color: var(--color-purle-2);
}

.player3 {
  background-color: var(--color-green-2);
}

.link-boxes {
  width: 100%;

  display: flex;
  justify-content: space-evenly;
}

.link-box {
  color: var(--color-grey-light-1);
  font-weight: 700;
  font-size: 5rem;
  text-align: center;

  width: 25%;
  background-size: cover;
  background-position: center;
  box-shadow: var(--shadow);

  padding: 2% 3%;
}

#portfolio { background-image: linear-gradient(var(--portfolio-image-overlay)), url(../assets/img/portfolio.jpg); }
#search { background-image: linear-gradient(var(--search-image-overlay)), url(../assets/img/search.jpg); }
#history { background-image: linear-gradient(var(--history-image-overlay)), url(../assets/img/history.jpg); }

</style>