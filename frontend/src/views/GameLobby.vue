<template>
  <div class="lobby">
    <h1>Lobby</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'

export default {
  name: 'lobby',
  components: {
    Routes
  }
}
</script>
