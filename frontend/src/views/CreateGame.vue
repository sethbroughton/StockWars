<template>
  <div id="create">
    <user-header></user-header>
    <h1>Create Game</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'

export default {
  name: 'create',
  components: {
    Routes,
    UserHeader
  }
}
</script>

<style>

</style>