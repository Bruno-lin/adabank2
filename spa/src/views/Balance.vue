<template>
  <Card v-bind:image="require('../assets/account.png')">
    <p>您当前余额为1234.56元</p>
  </Card>
</template>

<script>
import Card from "../components/Card.vue";

export default {
  name: "Balance",
  // data(){
  //   return{
  //
  //   };
  // },
  components: {
    Card,
  }
}

</script>

<style scoped>

</style>