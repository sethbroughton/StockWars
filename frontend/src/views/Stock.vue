<template>
  <div id="stock">
    <h1>Stock</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'

export default {
  name: 'stock',
  components: {
    Routes
  }
}
</script>