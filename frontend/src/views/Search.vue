<template>
  <div class="search">
    <h1>Search</h1>
    <p>You must be authenticated to see this</p>
    <routes/>
  </div>
</template>

<script>
import Routes from '@/components/Routes'

export default {
  name: 'search',
  components: {
    Routes
  }
}
</script>