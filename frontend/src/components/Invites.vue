<template>
  <div id="invites">
    <h3 class="invite-headline u-margin-bottom-small">Open Invites</h3>
    <div v-for="invite in invites" :key="invite.gameId" class="invite u-margin-bottom-small">
      <div class="details u-margin-bottom-small">
        <p class="detail">{{invite.name}}</p>
      </div>
      <div class="reply-buttons">
        <button v-on:click="acceptInvite(invite.gameId)" class="button-small accept">Accept</button>
        <button v-on:click="rejectInvite(invite.gameId)" class="button-small reject">Reject</button>
      </div>
    </div>
  </div>
</template>

<script>
import auth from '../auth';

export default {
  name: 'invites',
  data() {
    return {
      invites: [],
      user: this.$parent.user
    }
  },
  methods: {
    acceptInvite(gameId) {
      const authToken = auth.getToken();
      
      const fetchConfigPut = {
        method : "PUT",
        headers: {
          Authorization: `Bearer ${authToken}`,
          'Content-Type' : 'application/json',
          'Accept': 'application/json'
        }
      }

      fetch(`${process.env.VUE_APP_REMOTE_API}/api/invite/${this.user.id}/` + gameId, fetchConfigPut)
      .then((response) => {
        if(response.ok) {
          console.log("success");
          location.reload();
        }
      })
      .catch((err) => console.error(err));
    },

    rejectInvite(gameId) {
      const authToken = auth.getToken();
      
      const fetchConfigDelete = {
        method : "DELETE",
        headers: {
          Authorization: `Bearer ${authToken}`,
          'Content-Type' : 'application/json',
          'Accept': 'application/json'
        }
      }

      console.log(this.user.id, gameId);

      fetch(`${process.env.VUE_APP_REMOTE_API}/api/rejectInvite/${this.user.id}/` + gameId, fetchConfigDelete)
      .then((response) => {
        if(response.ok) {
          console.log("success");
          location.reload();
        }
      })
      .catch((err) => console.error(err));
    }
  },
  created() {

    const authToken = auth.getToken();

    fetch(`${process.env.VUE_APP_REMOTE_API}/api/invites`,{
      method: 'GET',
      headers:{
      Authorization: `Bearer ${authToken}`
      }
    })
    .then(response => {
      return response.json();
    })
    .then((games) => {
      this.invites = games;
    })
    .catch(err => console.log(`Error fetching reviews ${err}`));
  }
}
</script>

<style scoped>

#invites {
  background-image: linear-gradient(to bottom right, var(--color-secondary-1), var(--color-secondary-2));
  margin-right: 2%;
  padding: 1%;
  box-shadow: var(--shadow);

  flex: 1 1 0;
}

.invite-headline {
  text-align: center;
  font-size: 3rem;
  font-weight: 500;
}

.invite {
  background-color: var(--color-tertiary-2);
  padding: 3% 6%;
  box-shadow: var(--shadow-small);

  display: flex;
  flex-direction: column;
  align-items: center;
}

.details {
  width: 100%;

  display: flex;
  justify-content: space-between;
}

.detail {
  width: 100%;
  font-size: 2rem;
  font-weight: 700;
  text-align: center;
}

.accept {background-color: var(--color-green-2);}
.accept:hover {background-color: var(--color-green-1);}

.reject {background-color: var(--color-red-2);}
.reject:hover {background-color: var(--color-red-1);}

</style>
