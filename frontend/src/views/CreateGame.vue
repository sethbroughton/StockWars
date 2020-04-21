<template>
  <div class="create">
    <h1>Create Game</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'

export default {
  name: 'create',
  components: {
    Routes
  }
}
</script>

<style>

</style>