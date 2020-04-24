<template>
  <div id="trade-history">
    <user-header></user-header>
    <h1>Trade History</h1>
    <table> 
      <tr>
        <td> Stock Name </td>
        <td> Stock Code </td>
         <td> Buy/Sell </td>
          <td> Shares </td>
          <td> Value </td>
          <td> Trade Date</td>

      </tr>
      
    </table>
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

<style>
#trade-history {
  color: var(--color-grey-light-1);
  font-size: 2rem;
}
</style>