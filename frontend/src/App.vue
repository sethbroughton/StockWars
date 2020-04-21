<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>
    </div>
    <router-view/>
  </div>
</template>


// VARIABLES AND UNIVERSAL STYLES
<style>
  /* VARIABLES */
  :root {

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

    /* SHADOWS */
    --shadow: 0 2rem 2rem rgba(0,0,0,0.2);
    --shadow-small: 0 1rem 1rem rgba(0,0,0,0.2);
    --shadow-large: 0 3rem 3rem rgba(0,0,0,0.2);

  }
</style>
