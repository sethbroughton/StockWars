<template>
  <div id="search">
    <user-header></user-header>
    <h1>Search</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'

export default {
  name: 'search',
  components: {
    Routes,
    UserHeader
  }
}
</script>