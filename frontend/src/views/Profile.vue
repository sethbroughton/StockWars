<template>
  <div id="profile">
    <h1>Profile</h1>
    <routes/>
    <router-link v-bind:to="{name: 'create-game'}" tag="button">Create a New Game</router-link>
    <router-link v-bind:to="{name: 'lobby'}" tag="button">Join New Game</router-link>
    <leader-board/>
    <active-games/>
     
          {{name}}
    
    <user-statistics/>
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
        name: ""
      
    };
  },
  method: {
    created(){
      const authToken = auth.getToken();
      fetch(`${process.env.VUE_APP_REMOTE_API}/game`,{
       method: 'GET',
       headers:{
      Authorization: `Bearer ${authToken}`

      }}
      )
        .then((response) => {
               return response.json();
        })
        .then((name) => {
          this.name = name;
        })
        .catch((err) => console.error(err));
  } 

}
}
</script>

<style scoped>

#profile {
  color: var(--color-grey-light-1);
  font-size: 4rem;
}

</style>