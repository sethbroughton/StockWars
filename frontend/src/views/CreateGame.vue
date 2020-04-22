<template>
  <div id="create">
    <user-header></user-header>
    <h1>Create Game</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'
import CreateGameForm from '@/components/CreateGameForm'


export default {
  name: 'create',

  props: {
    apiURL: String,
  },
  components: {
    Routes,
    UserHeader,
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
    },
  }
};

</script>

<style>

</style>