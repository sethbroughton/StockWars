<template>
  <div class="container">
    <user-header></user-header>
    <div id="create">
      <h1 class="u-margin-bottom">Create Game</h1>
      <div class="new-game">
        <form class="form" @submit.prevent="login">
          <div class="form-group u-margin-bottom">
            <label for="name" class="label">Game Name</label>
            <input
            type="text"
            id="name"
            v-model="game.name"
            required
            />
          </div>
          <div class="form-group u-margin-bottom">
            <label for="numberOfPlayer" class="label">Number of Players</label>
            <input
            type="number"
            v-model="game.numberOfPlayers"
            required
            />
          </div>
          <div class="form-group u-margin-bottom">
            <label for="lengthInDays" class="label">Game Length (in days)</label>
            <input
            type="number"
            v-model="game.lengthInDays"
            required          
            />
          </div>
          <router-link :to="{ name: 'game' }" id="create-game" class="button">Create Game</router-link>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'
import auth from '../auth.js'
export default {
  name: 'create',

  data() {
    return {
      game: {
        name: '',
        lengthInDays: '',
        numberOfPlayers: ''
      },
      header: true
    }
  },
 
  components: {
    Routes,
    UserHeader    
  },
  methods: {
    createGame() {
      const authToken = auth.getToken();

      fetch(`${process.env.VUE_APP_REMOTE_API}/api/game`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          Authorization: `Bearer ${authToken}`
        },
        body: JSON.stringify(this.game)
      })
      .then((response) => {
        if(response.ok) {
           this.$router.push('/lobby');
        }
      })
      .catch((err) => console.error(err));
    }
  }
};

</script>

<style scoped>

.container {
  height: 100vh;
}

#create {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#create-game { background-color: var(--color-complementary-2); }

#create-game:hover { background-color: var(--color-complementary-1); }

h1 {
  color: var(--color-grey-light-1);
  font-size: 13rem;
  font-weight: 300;
}

</style>