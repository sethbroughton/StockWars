<template>
  <div id="profile">
    <user-header></user-header>
    <div class="container">
      <active-games class="u-margin-bottom"/>
      <div class="data">
        <invites/>
        <leader-board/>
      </div>
    </div>
   
  </div>
</template>

<script>
import LeaderBoard from '@/components/LeaderBoard';
import ActiveGames from '@/components/ActiveGames';
import Invites from '@/components/Invites';
import UserHeader from '@/components/UserHeader';

export default {
  name: 'profile',
  components: {
    LeaderBoard, 
    ActiveGames,
    Invites,
    UserHeader
  },
  data() {
    return {
      user: this.$parent.user
    }
  }
}

</script>

<style scoped>

.container {
  color: var(--color-grey-light-1);

  padding: 2% 4%;

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