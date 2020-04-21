<template>
  <div id="login">
    <div class="titles">
      <h1>Stock War$</h1>
      <h2>Play the real market with fake money.  Challenge your friends.  Compete for investor supremacy</h2>
    </div>
    <div class="login-and-register">
      <form action="/profile" @submit.prevent="login">
        <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div class="alert alert-success" role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="credentials">
          <label for="username">Username</label>
          <input
            type="text"
            id="username"
            placeholder="Username"
            v-model="user.username"
            required
            autofocus
          />
          <label for="password">Password</label>
          <input
            type="password"
            id="password"
            placeholder="Password"
            v-model="user.password"
            required
          />          
        </div>
        <button type="submit" class="button">Sign in</button>
      </form>
      <router-link :to="{ name: 'register' }" class="button">Register</router-link>

      <!-- LOGOUT BUTTON FOR TESTING/DEV PURPOSES -->
      <a href="#"  v-on:click="logoff">LogOut</a>
    </div>
  </div>
</template>

<script>
import auth from '../auth';

export default {
  name: 'login',
  components: {},
  data() {
    return {
      user: {
        username: '',
        password: '',
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/login`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            return response.text();
          } else {
            this.invalidCredentials = true;
          }
        })
        .then((token) => {
          if (token != undefined) {
            if (token.includes('"')) {
              token = token.replace(/"/g, '');
            }
            auth.saveToken(token);
            this.$router.push('/');
          }
        })
        .catch((err) => console.error(err));
    },

  logoff() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/login`, {
        method : 'DELETE'
        }
      )
      .then(response => {
        if(response.ok){
          // the user has be logged off the server
          //Log user out of front end
          
          auth.logout;
          auth.destroyToken;
          
        }
      })
      .catch(err => console.error(err));
  }
  }
};
</script>

<style scoped>

/* LAYOUT */

#login {
  height: 100vh;
  padding: 4%;
  
  background: linear-gradient(var(--hero-image-overlay)), url(../assets/img/hero.jpg);
  background-position: center;
  background-size: cover;

  position: relative;

  display: flex;
  justify-content: center;
  align-items: center;
}

.titles {
  color: var(--color-grey-light-1);
  margin: 2%;

  flex: 3 1 0;
}

.login-and-register {
  padding: 1%;

  flex: 2 1 0;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

form {
  display: flex;
  flex-direction: column;

  align-content: center;
}

/* TYPOGRAPHY */

h1 {
  font-size: 13rem;
  font-weight: 300;
}

h2 {
  font-size: 3.5rem;
  font-weight: 600;
}

</style>
