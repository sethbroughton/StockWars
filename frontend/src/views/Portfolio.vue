<template>
  <div id="portfolio">
    <user-header></user-header>
    <h1>Portfolio</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'

export default {
  name: 'portfolio',
  components: {
    Routes,
    UserHeader
  }
}
</script>