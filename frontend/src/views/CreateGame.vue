<template>
  <div id="create">
    <user-header></user-header>
    <div class="container">
      <h1 class="u-margin-bottom">Create Game</h1>
      <div class="new-game">
        <form class="form" @submit.prevent="createGame">
          <div class="form-group u-margin-bottom">
            <label for="name" class="label">Game Name</label>
            <input
            type="text"
            id="name"
            v-model="game.name"
            required
            autofocus
            />
          </div>
          <div class="form-group u-margin-bottom">
            <label for="numberOfPlayer" class="label">Number of Players</label>
            <input
            type="number"
            id="numberOfPlayers"
            v-model="game.numberOfPlayers"
            required
            />
          </div>
          <div class="form-group u-margin-bottom">
            <label for="lengthInDays" class="label">Game Length (in days)</label>
            <input
            type="number"
            id="lengthInDays"
            v-model="game.lengthInDays"
            required          
            />
          </div>
          <button id="create-game" class="button" type="submit">
            Create game
          </button>              
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import UserHeader from '@/components/UserHeader'
import auth from '../auth.js'
export default {
  name: 'create',
  data() {
    return {
      game: {
        organizerId: '',
        name: '',
        lengthInDays: '',
        numberOfPlayers: ''
      }
    }
  }, 
  components: {
    UserHeader    
  },
  created() {
    const authToken = auth.getToken();

    fetch(`${process.env.VUE_APP_REMOTE_API}/currentUser`,{
      method: 'GET',
      headers:{
      Authorization: `Bearer ${authToken}`
      }
    })
      .then((response) => {
        return response.json();
      })
      .then((user) => {
        this.game.organizerId = user.id;
      });
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

#create {
  height: 100vh;
}

.container {
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