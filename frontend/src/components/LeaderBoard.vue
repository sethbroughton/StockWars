<template>
  <div id="leaderboard" class="table">
    <div class="table-header">
      <h3 class="table-title">Leader Board</h3>
    </div>
    <div v-for="(wins, user) in leaderboard" :key="user" class="table-row">
      <span class="table-item">{{user}}</span>
      <span class="table-item">{{wins}}</span>
    </div>
  </div>
</template>

<script>
import auth from '../auth'
export default {
  name: 'leader-board',
  data() {
    return {
      leaderboard: []
    }
  },
  created() {
    const authToken = auth.getToken();
    fetch(`${process.env.VUE_APP_REMOTE_API}/api/leaderboard`,{
      method: 'GET',
      headers:{
      Authorization: `Bearer ${authToken}`
      }
    })
    .then(response => {
      return response.json();
    })
    .then((leaderboard) => {
      this.leaderboard = leaderboard;
    })
    .catch(err => console.log(`Error fetching reviews ${err}`));
  }
}
</script>

<style>

#leaderboard {
  flex: 3 1 0;
}

</style>
