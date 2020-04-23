<template>
  <div id="app">
    <user-header v-if="header"></user-header>
    <router-view/>
  </div>
</template>

<script>
import UserHeader from '@/components/UserHeader'

export default {
  name: 'app',
  components: {
    UserHeader
  },
  data() {
    return {

    }
  },
  created() {
    
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

  --hero-image-overlay: to bottom right, rgb(72, 126, 176, 0.7), rgb(25, 42, 86, 0.7);

  /* SHADOWS */
  --shadow: 0 2rem 2rem rgba(0,0,0,0.2);
  --shadow-small: 0 1rem 1rem rgba(0,0,0,0.2);
  --shadow-large: 0 3rem 3rem rgba(0,0,0,0.2);

}

/***************************************************
******* UNIVERSAL STYLES ***********************
****************************************************/

body {
  background-image: linear-gradient(var(--hero-image-overlay)), url(assets/img/hero.jpg);
  background-position: center;
  background-size: cover;
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
}


/***************************************************
******* UTILITIES ***********************
****************************************************/

/* MARGINS */

.u-margin-bottom-small { margin-bottom: 2rem !important; }
.u-margin-bottom { margin-bottom: 3rem !important; }
.u-margin-bottom-large { margin-bottom: 5rem !important; }

</style>
