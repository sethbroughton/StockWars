<template>
  <div id="register">
    <h1 class="u-margin-bottom">Stock Wars</h1>
    <form @submit.prevent="register" class="form u-margin-bottom">
      <div class="alert alert-danger u-margin-bottom-small" role="alert" v-if="registrationErrors">
        There were problems registering this user.
      </div>
      <div class="form-group u-margin-bottom-small">
        <label for="username" class="label">Username</label>
        <input
          type="text"
          id="username"
          v-model="user.username"
          required
          autofocus
        />        
      </div>
      <div class="form-group u-margin-bottom-small">
        <label for="password" class="label">Password</label>
        <input
          type="password"
          id="password"
          v-model="user.password"
          required
        />
      </div>
      <div class="form-group u-margin-bottom-small">
        <label for="password" class="label">Confirm Password</label>
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
#register {
  height: 100vh;
  
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

h1 {
  color: var(--color-grey-light-1);
  font-size: 13rem;
  font-weight: 300;
}

#create-account {
  background-color: var(--color-complementary-2);
}

#create-account:hover {
  background-color: var(--color-complementary-1);
}

#login {
  background-color: var(--color-tertiary-2);
}

#login:hover {
  background-color: var(--color-tertiary-1);
}
</style>
