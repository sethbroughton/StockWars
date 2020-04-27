<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import auth from './auth';
export default {
  name: 'app',
  data() {
    return {
      user: {
        username: '',
        id: ''
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
      this.user.username = currentUser.username;
      this.user.id = currentUser.id;
    });

  }
}
</script>



<style>

/***************************************************
******* SETUP ****************************
****************************************************/
*,
*::after,
*::before {
    margin: 0;
    padding: 0;
    box-sizing: inherit;
}

html {
    font-size: 62.5%;
}

body {
    box-sizing: border-box;
    font-family: var(--font-primary);
}

:root {
  /***************************************************
  ******* rgbIABLES ****************************
  ****************************************************/

  /* FONTS */
  --font-primary: 'Barlow', sans-serif;

  /* COLORS */
  --color-blue-1: rgb(0, 168, 255);
  --color-blue-2: rgb(0, 151, 230);
  --color-blue-3: rgb(72, 126, 176);
  --color-blue-4: rgb(64, 115, 158);
  --color-blue-5: rgb(39, 60, 117);
  --color-blue-6: rgb(25, 42, 86);

  --color-purple-1: rgb(156, 136, 255);
  --color-purple-2: rgb(140, 122, 230);
  
  --color-red-1: rgb(232, 65, 24);
  --color-red-2: rgb(194, 54, 22);

  --color-yellow-1: rgb(251, 197, 49);
  --color-yellow-2: rgb(225, 177, 44);

  --color-green-1: rgb(76, 209, 55);
  --color-green-2: rgb(68, 189, 50);

  --color-grey-light-1: rgb(245, 246, 250);
  --color-grey-light-2: rgb(220, 221, 225);
  --color-grey-medium-1: rgb(127, 143, 166);
  --color-grey-medium-2: rgb(113, 128, 147);
  --color-grey-dark-1: rgb(53, 59, 72);
  --color-grey-dark-2: rgb(47, 54, 64);

  --color-primary-1: var(--color-blue-5);
  --color-primary-2: var(--color-blue-6);

  --color-secondary-1: var(--color-grey-medium-1);
  --color-secondary-2: var(--color-grey-medium-2);

  --color-tertiary-1: var(--color-purple-1);
  --color-tertiary-2: var(--color-purple-2);

  --color-complementary-1: var(--color-green-1);
  --color-complementary-2: var(--color-green-2);

  --color-table-header: var(--color-blue-4);
  --color-table-row: var(--color-grey-medium-2);

  /* OVERLAYS */
  --hero-image-overlay: to bottom right, rgb(39, 60, 117, 0.7), rgb(25, 42, 86, 0.7);
  --portfolio-image-overlay: to bottom right, rgb(47, 54, 64, 0.4), rgb(47, 54, 64, 0.4);
  --search-image-overlay: to bottom right, rgb(47, 54, 64, 0.4), rgb(47, 54, 64, 0.4);
  --history-image-overlay: to bottom right, rgb(47, 54, 64, 0.4), rgb(47, 54, 64, 0.4);


  /* SHADOWS */
  --shadow: 0 2rem 2rem rgba(0,0,0,0.2);
  --shadow-small: 0 1rem 1rem rgba(0,0,0,0.2);
  --shadow-large: 0 3rem 3rem rgba(0,0,0,0.2);

}

/***************************************************
******* UNIVERSAL STYLES ***********************
****************************************************/

body {
  min-height: 100vh;
  background-image: linear-gradient(var(--hero-image-overlay)), url(assets/img/hero.jpg);
  background-position: center;
  background-size: cover;
}

a {
  text-decoration: none;
}

.form {
  background-color: rgba(47, 54, 64, .7);
  color: var(--color-grey-light-1);

  padding: 3rem 5rem;

  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-group {
  display: flex;
}

.label {
  font-size: 2.5rem;
  font-weight: 600;
  margin-right: 2rem;
}

.table {
  color: var(--color-grey-light-1);
  width: 70%;

  display: flex;
  flex-direction: column;
  align-items: center;
}

.table-header {
  width: 100%;
  margin-bottom: 1%;
  padding: 1% 2%;
  background-image: linear-gradient(to right, var(--color-blue-3), var(--color-blue-4));

  display: flex;
  justify-content: space-between;
  align-items: center; 
}

.table-buttons {
  display: flex; 
}

.table-title {
  font-size: 3rem;
}

.table-row {
  width: 90%;
  background-image: linear-gradient(to right, var(--color-secondary-1), var(--color-secondary-2));
  font-size: 2rem;
  font-weight: 500;

  padding: 1.5% 2.5%;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.table-row:not(:last-child) {
  margin-bottom: .5%;
}

.table-item {
  text-align: center;

  flex-grow: 1;
}

.table-item:not(:last-child) {
  border-right: 1px solid var(--color-grey-light-1);
}

.button {
  padding: 1.5rem 3.5rem;
  text-decoration: none;
  color: var(--color-grey-light-2);
  border: none;
  font-size: 2rem;
  font-weight: 700;
  cursor: pointer;
  transition: all .2s;
}

.button:hover {
  transform: translateY(-.3rem);
  box-shadow: var(--shadow-small);
  color: var(--color-grey-dark-1);

}

.button-small {
  padding: 1rem 2rem;
  text-decoration: none;
  color: var(--color-grey-light-1);
  border: none;
  font-size: 1.5rem;
  font-weight: 700;
  cursor: pointer;
  border-radius: 500px;
  transition: all .2s;  
}

.button-small:not(:last-child) {
  margin-right: 2rem;
}

.alert {
  color: var(--color-grey-light);
  font-size: 3rem;
  text-align: center;
}

/***************************************************
******* UTILITIES ***********************
****************************************************/

/* MARGINS */

.u-margin-bottom-small { margin-bottom: 1rem !important; }
.u-margin-bottom { margin-bottom: 3rem !important; }
.u-margin-bottom-large { margin-bottom: 5rem !important; }

</style>
