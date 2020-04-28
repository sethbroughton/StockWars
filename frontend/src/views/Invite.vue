<template>
  <div id="invite">
    <user-header></user-header>
    <div class="container">
      <h1 class="u-margin-bottom">Invite to Game</h1>
      <div class="invite-to-game">
        <form class="form" @submit.prevent="inviteToGame(game.playerId)">
          <div class="form-group u-margin-bottom">

            <!-- This section is where username needs to be put -->
            <label for="playerId" class="label">Player Id</label>
            <input
            type="number"
            id="playerId"
            v-model="game.playerId"
            placeholder="Player Id"
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
         <button id="invite-player" class = "button" type = "submit">
            Invite
          </button>             
        </form>
      </div>
    </div>
  </div>
</template>

<script>

import UserHeader from '@/components/UserHeader'
import auth from '../auth'

export default {
  name: 'invite',
  data() {
    return {
      game: {
        gameId: this.$route.params.gameId,
        playerId: ''
      },
      user: this.$parent.user
    }
  },
  components: {
    UserHeader    
  },
  methods: {
    inviteToGame(userId) {
      const authToken = auth.getToken();
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/invite/${this.game.gameId}`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          Authorization: `Bearer ${authToken}`
        },
        body: JSON.stringify(userId, this.game.gameId)
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