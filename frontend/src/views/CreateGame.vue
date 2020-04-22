<template>
  <div id="create">
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
    Routes
  },
  created() {

  }
}
</script>

<style>

</style>