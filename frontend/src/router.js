import Vue from 'vue'
import Router from 'vue-router'
import auth from './auth'
import Profile from './views/Profile.vue'
import Login from './views/Login.vue'
import Register from './views/Register.vue'
import CreateGame from './views/CreateGame.vue'
import GameLobby from './views/GameLobby.vue'
import Invite from './views/Invite.vue'
import Game from './views/Game.vue'
import Portfolio from './views/Portfolio.vue'
import TradeHistory from './views/TradeHistory.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'profile',
      component: Profile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/create',
      name: 'create-game',
      component: CreateGame,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/lobby',
      name: 'lobby',
      component: GameLobby,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/invite',
      name: 'invite',
      component: Invite,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/game/:gameId',
      name: 'game',
      component: Game,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/portfolio/:portfolioId',
      name: 'portfolio',
      component: Portfolio,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/tradeHistory',
      name: 'trade-history',
      component: TradeHistory,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  const user = auth.getUser();

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && !user) {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
