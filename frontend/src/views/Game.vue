<template>
  <div id="game">
    <user-header></user-header>
    <div class="container">
      <div class="game-stats u-margin-bottom">
        <p class="stat">{{game.name}}</p>
        <p class="stat">Balance: ${{portfolio.totalValue}}</p>
        <p class="stat">XX Days Left</p>
      </div>

      <stock-buy-sell class="u-margin-bottom"/>

      <div class="link-boxes">
        <router-link v-bind:to="{ name: 'portfolio', params: {portfolioId: portfolio.portfolioId} }" id="portfolio" class="link-box">
          Portfolio
        </router-link>
        <router-link :to="{ name: 'trade-history' }" id="history" class="link-box">
          History
        </router-link>
      </div>
    </div>
    
    <div class="scoreboard">
      <div v-for="portfolio in allPortfolios" :key="portfolio.portfolioId" class="player-card">
        {{portfolio.portfolioId}}
      </div>
    </div>

  </div>
</template>

<script>
import UserHeader from '@/components/UserHeader'
import StockBuySell from '@/components/StockBuySell'
import auth from '../auth'

export default {
  name: 'game',
  components: {
    UserHeader,
    StockBuySell,
  },
  data() {
    return {
      user: this.$parent.user,
      game: null,
      portfolio: null,
      allPortfolios: []
    }
  },
  created() {

    const authToken = auth.getToken();

    const fetchConfigGet = {
      method: 'GET',
      headers:{
        Authorization: `Bearer ${authToken}`
      }
    }

    fetch(`${process.env.VUE_APP_REMOTE_API}/currentUser`, fetchConfigGet)
    .then((response) => {
      return response.json();
    })
    .then((currentUser) => {
      this.user = currentUser;
    });    

    fetch(`${process.env.VUE_APP_REMOTE_API}/api/games`, fetchConfigGet)
    .then(response => {
      return response.json();
    })
    .then((games) => {
      this.game = games.find(p => p.gameId == this.$route.params.gameId);
    })
    .catch(err => console.log(`Error fetching games ${err}`));

    fetch(`${process.env.VUE_APP_REMOTE_API}/api/portfolios`, fetchConfigGet)
    .then(response => {
      return response.json();
    })
    .then((portfolios) => {
      this.portfolio = portfolios.find(p => p.userId == this.user.id && p.gameId == this.$route.params.gameId);
    })
    .catch(err => console.log(`Error fetching portfolios ${err}`));

    fetch(`${process.env.VUE_APP_REMOTE_API}/api/portfoliosInGame/${this.$route.params.gameId}`, fetchConfigGet)
    .then(response => {
      return response.json();
    })
    .then((portfolios) => {
      this.allPortfolios = portfolios;
    })
    .catch(err => console.log(`Error fetching portfolios ${err}`));

  }
}
</script>

<style scoped>

  #game {
    position: relative;
  }

.container {
  padding: 2% 7%;

  display: flex;
  flex-direction: column;
  align-items: center;
}

.game-stats {
  width: 100%;
  font-size: 3rem;
  font-weight: 700;
  color: var(--color-grey-light-1);

  display: flex;
  justify-content: space-between;
}

.search-and-trade {
  display: flex;
  align-items: flex-start;
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

  transition: all .2s;
}

.link-box:hover {
  transform: translateY(-.3rem);
  box-shadow: var(--shadow-large);
}

.scoreboard {
  width: 8%;

  position: absolute;
  top: 50%;
  left: 0;
}

.player-card {
  color: var(--color-grey-light-1);
  background-color: var(--color-tertiary-2);
  text-align: right;
  font-size: 1.5rem;
  font-weight: 700;

  padding: 3% 8%;
  margin-bottom: 5%;
}

#portfolio { background-image: linear-gradient(var(--portfolio-image-overlay)), url(../assets/img/portfolio.jpg); }
#history { background-image: linear-gradient(var(--history-image-overlay)), url(../assets/img/history.jpg); }

</style>