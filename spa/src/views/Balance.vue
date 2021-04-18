<template>
  <Card v-bind:image="require('../assets/account.png')">
    <p>您好，{{ username }}！</p>
    <p>您当前余额为1234.56元</p>
  </Card>
</template>

<script>
import Card from "../components/Card.vue";

export default {
  name: "Balance",
  data() {
    return {
      username: this.getName(),
    };
  },
  methods: {
    getName() {
      fetch("/api/name?session_id=" + document.cookie, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }).then((response) => response.json())
          .then((data) => {
            if (data.status === "good") {
              this.username = data.name
            }
          })
    }
  },
  components: {
    Card,
  }
}

</script>

<style scoped>

</style>