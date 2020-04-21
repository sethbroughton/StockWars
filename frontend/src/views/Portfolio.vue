<template>
  <div id="portfolio">
    <h1>Portfolio</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'

export default {
  name: 'portfolio',
  components: {
    Routes
  }
}
</script>