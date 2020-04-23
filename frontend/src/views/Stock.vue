<template>
  <div id="stock">
    <user-header></user-header>
    <h1>Stock</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'

export default {
  name: 'stock',
  components: {
    Routes,
    UserHeader
  }
}
</script>