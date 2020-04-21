<template>
  <div id="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'

export default {
  name: 'home',
  components: {
    Routes
  }
}
</script>
