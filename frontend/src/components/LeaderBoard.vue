<template>
  <table id="leaderboard" class="table">
    <tr class="table-header">
      <th class="table-title">Leader Board</th>
    </tr>
    <tr v-for="(wins, user) in leaderboard" :key="user" class="table-row">
      <td>Player: {{user}}</td>
      <td>Wins: {{wins}}</td>
    </tr>
  </table>
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

td {
  text-align: left;
  width: 50%;
}
</style>
