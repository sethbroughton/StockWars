<template>
  <div id="trade-history">
    <user-header></user-header>
    <h1>Trade History</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'
import UserHeader from '@/components/UserHeader'

export default {
  name: 'trade-history',
  components: {
    Routes,
    UserHeader
  }
}
</script>