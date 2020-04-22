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
  --color-blue-1: 0, 168, 255;
  --color-blue-2: 0, 151, 230;
  --color-blue-3: 72, 126, 176;
  --color-blue-4: 64, 115, 158;
  --color-blue-5: 39, 60, 117;
  --color-blue-6: 25, 42, 86;

  --color-purple-1: 156, 136, 255;
  --color-purple-2: 140, 122, 230;
  
  --color-red-1: 232, 65, 24;
  --color-red-2: 194, 54, 22;

  --color-yellow-1: 251, 197, 49;
  --color-yellow-2: 225, 177, 44;

  --color-green-1: 76, 209, 55;
  --color-green-2: 68, 189, 50;

  --color-grey-light-1: 245, 246, 250;
  --color-grey-light-2: 220, 221, 225;
  --color-grey-medium-1: 127, 143, 166;
  --color-grey-medium-2: 113, 128, 147;
  --color-grey-dark-1: 53, 59, 72;
  --color-grey-dark-2: 47, 54, 64;

  --hero-image-overlay: to bottom right, rgb(25, 42, 86, 0.8), rgb(194, 54, 22, 0.8);

  /* SHADOWS */
  --shadow: 0 2rem 2rem rgba(0,0,0,0.2);
  --shadow-small: 0 1rem 1rem rgba(0,0,0,0.2);
  --shadow-large: 0 3rem 3rem rgba(0,0,0,0.2);

}

/***************************************************
******* UNIVERSAL STYLES ***********************
****************************************************/

.button {
  padding: 4% 7%;
  text-decoration: none;
  color: var(--color-grey-light-2);
  border: none;
  font-size: 2rem;
  font-weight: 700;
  cursor: pointer;
}

/***************************************************
******* UTILITIES ***********************
****************************************************/

/* MARGINS */

.u-margin-bottom-small { margin-bottom: 2rem !important; }
.u-margin-bottom { margin-bottom: 3rem !important; }
.u-margin-bottom-large { margin-bottom: 5rem !important; }

</style>
