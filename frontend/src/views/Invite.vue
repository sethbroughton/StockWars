<template>
  <div id="invite">
    <user-header></user-header>
    <div class="container">
      <h1 class="u-margin-bottom">Invite to Game</h1>
      <div class="invite-to-game">
        <form class="form" @submit.prevent="inviteToGame">
          <div class="form-group u-margin-bottom">

            <!-- This section is where username needs to be put -->
            <label for="username" class="label">Who do you want to invite?</label>
            <input
            type="text"
            id="userame"
            v-model="invite.username"
            required
            autofocus
            />
          </div>
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
      invite: {
        gameId: this.$route.params.gameId,
        username: null
      },
      user: this.$parent.user
    }
  },
  components: {
    UserHeader    
  },
  methods: {
    inviteToGame() {
      const authToken = auth.getToken();
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/invite/${this.invite.gameId}`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          Authorization: `Bearer ${authToken}`
        },
        body: JSON.stringify(this.invite.username)
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