<template>
  <header class="header">
    <h1 class="title">Stock Wars</h1>
    <div class="user-info">
      <router-link :to="{ name: 'profile' }" class="user-name">{{ user.name }}</router-link>
      <img src="../assets/img/sample-user-avi.jpg" alt="user avi">
    </div>
  </header>
</template>

<script>
export default {
    name: 'user-header',
    data() {
      return {
        user: {
          name: ''
        }
      }
    },
    created() {

      fetch(`${process.env.VUE_APP_REMOTE_API}/currentUser`, {
        headers: {
          "Authorization": `Bearer ${authToken}`
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
.header {
    background-image: linear-gradient(to right, var(--color-blue-6), var(--color-blue-5));
    height: 7rem;
    color: var(--color-grey-light-1);

    padding: 1%;

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

  margin-left: 2rem;

  border-radius: 50%;  
}

.title {
  font-size: 4rem;
}

a {
  text-decoration: none;
  color: var(--color-grey-light);
  font-size: 2rem;
  font-weight: 500;
}
</style>