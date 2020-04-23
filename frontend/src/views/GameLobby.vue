<template>
  <div id="lobby">
    <user-header></user-header>
<<<<<<< HEAD
    <div class = "lobby-of-game">
    <h1>New Stock Wars</h1>

    <game-lobby/>
=======
    <h1>New Stock Wars</h1>
    <div class="open-games">
      <h2>Open Games</h2>
      <button class="new-game-btn">Create New Game</button>
      <ul class="open-games-list">
        <li>
          (Game 1) - Organizer: (Username)
          - Players: ((# of players)/(max # of players)) -
          (length of game) Days
        </li>
        <button class="join-game-btn">Join</button>
        <li>
          (Game 2) - Organizer: (Username)
          - Players: ((# of players)/(max # of players)) -
          (length of game) Days
        </li>
        <button class="join-game-btn">Join</button>
        <li>
          (Game 3) - Organizer: (Username)
          - Players: ((# of players)/(max # of players)) -
          (length of game) Days
        </li>
        <button class="join-game-btn">Join</button>
      </ul>
    </div>
    <div class="pending-games">
      <h2>Pending Games</h2>
      <ul class="pending-games-list">
        <li class="game-not-started">
          (Game 1) - Organizer: (Username)
          - Players: ((# of players)/(max # of players)) -
          (length of game) Days
        </li>
        <button class="start-game-btn">Start Game</button>
        <li class="game-started">
          (Game 1) - Organizer: (Username)
          - Players: ((# of players)/(max # of players)) -
          (length of game) Days
        </li>
      </ul>
    </div>

    <routes />
    <game-lobby />
>>>>>>> 07d874209f9f44954072b9d63bcdf056fa957a0d
  </div>
</template>

<script>
import Routes from "@/components/Routes";
import GameLobby from "@/components/GameLobby";
import UserHeader from "@/components/UserHeader";

export default {
  name: "lobby",
  components: {
    Routes,
    GameLobby,
    UserHeader
  }
};
</script>

<style>
#lobby {
  color: var(--color-grey-light-1);
  font-size: 4rem;
}
</style>