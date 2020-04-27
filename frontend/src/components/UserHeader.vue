<template v-if="header">
  <header class="header">
    <h1 class="title">Stock Wars</h1>
    <div class="user-info">
      <router-link :to="{ name: 'profile' }" class="header-link">{{ user.username }}</router-link>
      <form v-on:click="logout" class="header-link">Sign out</form>
      <img src="../assets/img/sample-user-avi.jpg" alt="user avi">
    </div>
  </header>
</template>

<script>
import auth from '../auth';

export default {
  name: 'user-header',
  data() {
    return {
      user: this.$parent.user
    }
  },
  methods: {
    logout() {
      const authToken = auth.getToken();
      const fetchConfig = {
        method : "DELETE",
        headers:{
          Authorization : `Bearer ${authToken}`
        }
      }
      fetch(`${process.env.VUE_APP_REMOTE_API}/login`, fetchConfig)
      .then(response => {
        if(response.ok) {
          auth.logout();
          this.$router.push('/login');
        }
      })
       .catch((err) => console.error(err));
      }
  } 
}

</script>

<style scoped>
.header {
    background-image: linear-gradient(to right, var(--color-primary-1), var(--color-primary-2));
    height: 5rem;
    color: var(--color-grey-light-1);

    padding: 1%;

    position: sticky;
    top: 0;

    display: flex;
    justify-content: space-between;
    align-items: center;
}

.user-info {  
  height: 100%;

  display: flex;
  align-items: center;
}

img {
  height: 100%;
  width: auto;

  border-radius: 50%;  
}

.title {
  font-size: 3rem;
}

.header-link {
  text-decoration: none;
  font-size: 2rem;
  color: var(--color-grey-light);
  font-weight: 500;
  margin-right: 2rem;
  cursor: pointer;
}

</style>