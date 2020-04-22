<template>
  <div id="login">

    <div class="titles">
      <h1>Stock Wars</h1>      
      <h2>Play the real market with fake money. Challenge your friends. Compete for investor supremacy.</h2>
    </div>

    <div class="login-and-register">      
      <form @submit.prevent="login" class="u-margin-bottom">
        <div class="input-fields">
          <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
            Invalid username and password!
          </div>
          <div class="alert alert-success" role="alert" v-if="this.$route.query.registration">
            Thank you for registering, please sign in.
          </div>
          <div class="form-group u-margin-bottom-small">
            <label for="username">Username</label>
            <input
              type="text"
              id="username"
              v-model="user.username"
              required
              autofocus
            />
          </div>
          <div class="form-group u-margin-bottom-small">
            <label for="password">Password</label>
            <input
              type="password"
              id="password"
              v-model="user.password"
              required
            />
          </div>
        </div>

        <button type="submit" id="sign-in" class="button">Sign in</button>
      </form>

      <router-link :to="{ name: 'register' }" id="register" class="button">Need an account?</router-link>

      <router-link :to="{ name: 'about' }" id="about">About the team</router-link>
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
  },
};
</script>

<style scoped>

/* LAYOUT */

  #login {
    height: 100vh;

    display: flex;
    justify-content: center;
    align-items: center;
  }

  .titles {
    margin: 3%;
    color:var(--color-grey-light-1);

    flex: 3 1 0;
  }

  .login-and-register {
    color:var(--color-grey-light-1);

    margin: 1%;

    flex: 2 1 0;

    display: flex;
    flex-direction: column;
    align-items: center;
  }

  form {
    background-color: var(--color-grey-dark-2);

    padding: 6% 10%;  

    display: flex;
    flex-direction: column;
    align-items: center;
  }

  input {
    margin-left: 2rem;
  }

  #sign-in {
    background-color: var(--color-green-2);

    transition: all .2s;
  }  

  #sign-in:hover {
    background-color: var(--color-green-1);
    transform: translateY(-.3rem);
    box-shadow: var(--shadow-small);
  }

  #register {
    background-color: var(--color-purple-2);

    transition: all .2s;
  }

  #register:hover {
    background-color: var(--color-purple-1);
    transform: translateY(-.3rem);
    box-shadow: var(--shadow-small);
  }

  #about {
    text-decoration: none;
    color: var(--color-grey-light-1);
    font-size: 2.5rem;
    font-weight: 600;

    position: absolute;
    bottom: 2%;
    right: 3%;
  }

  /* TYPOGRAPHY */

  h1 {
    font-size: 13rem;
    font-weight: 300;
  }

  h2 {
    font-size: 3.5rem;
    font-weight: 500;
  }

  label {
    font-size: 2rem;
    font-weight: 600;
  }

</style>
