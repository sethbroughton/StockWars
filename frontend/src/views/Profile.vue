<template>
  <div id="profile">
    <h1>{{ user.name }}</h1>
    <routes/>
    <router-link v-bind:to="{name: 'create-game'}" tag="button">Create a New Game</router-link>
    <router-link v-bind:to="{name: 'lobby'}" tag="button">Join New Game</router-link>
    <leader-board/>
    <active-games/>
    <user-statistics/>
   <ul>
     <li v-for="game in games" v-bind:key="game.gameId">
       {{game.name}}
  
       </li>
   </ul>
   
  </div>
</template>

<script>
import Routes from '@/components/Routes';
import LeaderBoard from '@/components/LeaderBoard';
import ActiveGames from '@/components/ActiveGames';
import UserStatistics from '@/components/UserStatistics';
import auth from '../auth';

export default {
  name: 'profile',
  components: {
    Routes,
    LeaderBoard, 
    ActiveGames,
    UserStatistics
  },
  data() {
    return {
      user: {
          name: ''
        },
      games: []
    }
  },
  created() {
    const authToken = auth.getToken();

    fetch(`${process.env.VUE_APP_REMOTE_API}/currentUser`,{
      method: 'GET',
      headers:{
      Authorization: `Bearer ${authToken}`
      }
    })
      .then((response) => {
        return response.json();
      })
      .then((currentUser) => {
        this.user.name = currentUser.username;
      });

fetch(`${process.env.VUE_APP_REMOTE_API}/api/game`,{
      method: 'GET',
      headers:{
      Authorization: `Bearer ${authToken}`,

        'Accept': 'application/json'
      }
    })
      .then((response) => {
         console.log(response)
        //return response.json();
      })
      .then((games) => {
        console.log(games);
        this.games = games;


      });


  }
}

</script>

<style scoped>

#profile {
  color: var(--color-grey-light-1);
  font-size: 4rem;
}

</style>