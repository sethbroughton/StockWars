<template>
  <div id="register">
    <h1 class="u-margin-bottom">Stock Wars</h1>
    <form @submit.prevent="register" class="u-margin-bottom">
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        There were problems registering this user.
      </div>
      <div class="formgroup u-margin-bottom-small">
        <label for="username" class="sr-only">Username</label>
        <input
          type="text"
          id="username"
          v-model="user.username"
          required
          autofocus
        />        
      </div>
      <div class="formgroup u-margin-bottom-small">
        <label for="password" class="sr-only">Password</label>
        <input
          type="password"
          id="password"
          v-model="user.password"
          required
        />
      </div>
      <div class="formgroup u-margin-bottom-small">
        <label for="password" class="sr-only">Confirm Password</label>
        <input
          type="password"
          id="confirmPassword"
          v-model="user.confirmPassword"
          required
        />
      </div>
      <button id="create-account" class="button" type="submit">
        Create account
      </button>      
    </form>

    <router-link :to="{ name: 'login' }" id="login" class="button">
      Have an account?
    </router-link>    
  </div>
</template>

<script>
export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
    };
  },
  methods: {
    register() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/register`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            this.$router.push({ path: '/login', query: { registration: 'success' } });
          } else {
            this.registrationErrors = true;
          }
        })

        .then((err) => console.error(err));
    },
  },
};
</script>

<style scoped>

form {
  background-color: var(--color-grey-dark-2);
  padding: 3% 5%;
  color: var(--color-grey-light-1);


  display: flex;
  flex-direction: column;
  align-items: center;
}

h1 {
  color: var(--color-grey-light-1);
  font-size: 13rem;
  font-weight: 300;
}

#register {
  height: 100vh;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#create-account {
  background-color: var(--color-green-2);

  transition: all .2s;
}

#create-account:hover {
  background-color: var(--color-green-1);
  transform: translateY(-.3rem);
  box-shadow: var(--shadow-small);
}

#login {
  background-color: var(--color-purple-2);
  color: var(--color-grey-light-1);

  transition: all .2s;
}

#login:hover {
  background-color: var(--color-purple-1);
  transform: translateY(-.3rem);
  box-shadow: var(--shadow-small);
}

label {
  font-size: 2rem;
  font-weight: 600;
}

input {
  margin-left: 2rem;
}

</style>
