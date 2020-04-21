<template>
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
</template>

<script>
export default {
    data() {
        return {
            game: {           
                name: '',
                numberOfPlayer: '',
                lengthInDays: ''
              
            }
        }
    },
    methods: {
        createGame() {
      
        }
    }
}
</script>