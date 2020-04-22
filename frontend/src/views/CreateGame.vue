<template>
  <div id="create">
    <h1>Create Game</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'


export default {
  name: 'create',
  components: {
    Routes,
    
  }
  // methods: {
  //   createGame() {
  //     fetch(this.apiURL, {
  //       method: 'POST',
  //       headers: {
  //         ??????
  //       },
  //       body: JSON.stringify(this.user)
        
  //     })
  //     .then((response) => {
  //       if(response.ok) {
  //         this.???startgame????
  //       }
  //     })
  //     .catch((err) => console.error(err));
  //   },
  // }
};

</script>

<style>

</style>