<template>
  <div id="app">
    <router-view/>
  </div>
</template>



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
  ******* VARIABLES ****************************
  ****************************************************/

  /* FONTS */
  --font-primary: 'Barlow', sans-serif;

  /* COLORS */
  --color-blue-1: #00a8ff;
  --color-blue-2: #0097e6;
  --color-blue-3: #487eb0;
  --color-blue-4: #40739e;
  --color-blue-5: #273c75;
  --color-blue-6: #192a56;

  --color-purple-1: #9c88ff;
  --color-purple-2: #8c7ae6;
  
  --color-red-1: #e84118;
  --color-red-2: #c23616;

  --color-yellow-1: #fbc531;
  --color-yellow-2: #e1b12c;

  --color-green-1: #4cd137;
  --color-green-2: #44bd32;

  --color-grey-light-1: #f5f6fa;
  --color-grey-light-2: #dcdde1;
  --color-grey-medium-1: #7f8fa6;
  --color-grey-medium-2: #718093;
  --color-grey-dark-1: #353b48;
  --color-grey-dark-2: #2f3640;

  --hero-image-overlay: to bottom right, rgb(25, 42, 86, 0.6), rgb(194, 54, 22, 0.6);

  /* SHADOWS */
  --shadow: 0 2rem 2rem rgba(0,0,0,0.2);
  --shadow-small: 0 1rem 1rem rgba(0,0,0,0.2);
  --shadow-large: 0 3rem 3rem rgba(0,0,0,0.2);

}

/***************************************************
******* UNIVERSAL STYLES ***********************
****************************************************/

.button {
  padding: 3% 6%;
  text-decoration: none;
  color: var(--color-grey-dark-2);
  background-color: var(--color-blue-2);
  border: none;
  font-size: 2rem;
  font-weight: 700;
}

</style>
