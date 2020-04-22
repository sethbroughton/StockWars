<template>
  <div id="create">
    <h1>Create Game</h1>
    <p>You must be authenticated to see this</p>
    <div class="new-game">
       <form class="form-signin" @submit.prevent="login">
        <label for="name"></label>
        <input
        type="text"
        id="name"
        placeholder="Name"
        v-model="game.name"
        required
        />
        <label for="numberOfPlayer"></label>
        <input
        type="number"
        placeholder="Number Of Players"
        v-model="game.numberOfPlayers"
        required
        />
        <label for="lengthInDays"></label>
        <input
        type="number"
        v-model="game.lengthInDays"
        required          
        />
    <router-link :to="{ name: 'game' }" tag="button">Create Game</router-link>
       </form>
    </div>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
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
    }
  },
 
  components: {
    Routes,
    
    
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

<style>

</style>