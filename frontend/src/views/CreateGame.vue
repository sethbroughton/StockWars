<template>
  <div id="create">
    <h1>Create Game</h1>
    <p>You must be authenticated to see this</p>
    <create-game-form></create-game-form>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import CreateGameForm from '@/components/CreateGameForm'


export default {
  name: 'create',

  props: {
    apiURL: String,
  },
  components: {
    Routes,
    CreateGameForm
  },
  methods: {
    createGame() {
      fetch(this.apiURL, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          
        },
        body: JSON.stringify(this.user)
        
      })
      .then((response) => {
        if(response.ok) {
          this.game
        }
      })
      .catch((err) => console.error(err));
    }
  }
};

</script>

<style>

</style>