<template>
  <div id="user-statistics">
    <h3 class="stats-header u-margin-bottom-small">Your Statistics</h3>
    <div class="stats">
      <div class="stat">[PIE CHART]</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'user-statistics'
}
</script>

<style>

#user-statistics {
  background-image: linear-gradient(to bottom right, var(--color-secondary-1), var(--color-secondary-2));
  margin-right: 2%;
  padding: 1%;

  flex: 1 1 0;
}

.stats-header {
  text-align: center;
  font-size: 3rem;
  font-weight: 500;
}

.stats {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.stat {
  background-color: var(--color-primary-2);
  width: 80%;
  padding: 2% 3%;
  font-size: 4rem;
  text-align: center;
}

</style>
