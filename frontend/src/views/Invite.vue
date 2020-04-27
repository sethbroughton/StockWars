<template>
  <div id="invite">
    <user-header></user-header>
    <div class="container">
      <h1 class="u-margin-bottom">Invite to Game</h1>
      <div class="invite-to-game">
        <form class="form" @submit.prevent="inviteToGame">
          <div class="form-group u-margin-bottom">
            <label for="gameId" class="label">Game Id</label>
            <input
            type="text"
            id="gameId"
            v-model="game.gameId"
            placeholder="NEEDS DATA BINDING"
            required
            autofocus
            />
          </div>
          <div class="form-group u-margin-bottom">
            <label for="playerId" class="label">Player Id</label>
            <input
            type="number"
            id="playerId"
            v-model="game.playerId"
            placeholder="NEEDS DATA BINDING"
            required
            />
          </div>
          <!-- <div class="form-group u-margin-bottom">
            <label for="lengthInDays" class="label">Game Length (in days)</label>
            <input
            type="number"
            id="lengthInDays"
            v-model="game.lengthInDays"
            required          
            />
          </div> -->
          <router-link :to="{ name: 'lobby' }" id="invite-player" class="button" type="submit">
            Invite
          </router-link>              
        </form>
      </div>
    </div>
  </div>
</template>

<script>

import UserHeader from '@/components/UserHeader'
export default {
  name: 'invite',
  data() {
    return {
      game: {
        gameId: '',
        playerId: ''
      },
      user: this.$parent.user
    }
  },
  components: {
    UserHeader    
  }
};

</script>

<style scoped>

#invite {
  height: 100vh;
}

.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

h1 {
  color: var(--color-grey-light-1);
  font-size: 13rem;
  font-weight: 300;
}

#invite-player { background-color: var(--color-complementary-2); }
#invite-player:hover { background-color: var(--color-complementary-1); }

</style>