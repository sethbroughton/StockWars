<template>
  <div id="trade-history">
    <h1>Trade History</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'

export default {
  name: 'trade-history',
  components: {
    Routes
  }
}
</script>