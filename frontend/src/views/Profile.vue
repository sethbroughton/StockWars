<template>
  <div id="profile">
    <user-header></user-header>
    <div class="container">
      <active-games class="u-margin-bottom"/>
      <div class="data">
        <user-statistics/>
        <leader-board/>
      </div>
      <routes/>
    </div>
   
  </div>
</template>

<script>
import Routes from '@/components/Routes';
import LeaderBoard from '@/components/LeaderBoard';
import ActiveGames from '@/components/ActiveGames';
import UserStatistics from '@/components/UserStatistics';
import UserHeader from '@/components/UserHeader';
import auth from '../auth';

export default {
  name: 'profile',
  components: {
    Routes,
    LeaderBoard, 
    ActiveGames,
    UserStatistics,
    UserHeader
  },
  data() {
    return {
      user: {
          name: ''
        }
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
  }
}

</script>

<style scoped>

.container {
  color: var(--color-grey-light-1);

  padding: 4%;

  display: flex;
  flex-direction: column;
  align-items: center;
}

.data {
  width: 100%;

  display: flex;
  align-items: flex-start;
}

#create-game {background-color: var(--color-complementary-2);}
#create-game:hover {background-color: var(--color-complementary-1);}

#join-game {background-color: var(--color-tertiary-2);}
#join-game:hover {background-color: var(--color-tertiary-1);}


</style>