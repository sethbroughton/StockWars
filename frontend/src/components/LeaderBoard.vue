<template>
  <div id="leaderboard" class="table">
    <div class="table-header">
      <h3 class="table-title">Leader Board</h3>
    </div>
    <div v-for="user in highScorers" :key="user.id" class="table-row">
      <span class="table-item">{{user.username}}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'leader-board',
  data() {
    return {
      highScorers: []
    }
  }
}
</script>

<style>

#leaderboard {
  background-color: var(--color-tertiary-2);

  flex: 3 1 0;
}

</style>
