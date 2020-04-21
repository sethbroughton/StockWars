<template>
  <div class="game">
    <h1>Game</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'

export default {
  name: 'game',
  components: {
    Routes
  }
}
</script>