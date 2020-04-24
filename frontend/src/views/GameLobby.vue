<template>
  <div id="lobby">
    <user-header></user-header>
    <div class="container">
      <h1><strong>New Stock Wars</strong></h1>
      <open-games />
      <pending-games />
    </div>
  </div>
</template>

<script>
import OpenGames from "@/components/OpenGames";
import PendingGames from "@/components/PendingGames";
import UserHeader from "@/components/UserHeader";

export default {
  name: "lobby",
  components: {
    UserHeader,
    OpenGames,
    PendingGames
  }
};
</script>

<style scoped>

.container {
  padding: 2%;

  display: flex;
  flex-direction: column;
  align-items: center;
}

h1 {
  color: var(--color-grey-light-1);
  font-size: 6rem;
  font-weight: 300;
}

</style>