<template>
  <el-container>
    <el-header>Header</el-header>
    <el-main>
      <el-card shadow="always" :body-style="{ padding: '0px' }">
        <img src="../assets/bank.png" class="image">
        <div style="padding: 14px;">
          <el-input v-model="username" placeholder="请输入用户名" clearable></el-input>
          <el-input v-model="password" placeholder="请输入密码" show-password clearable></el-input>
          <el-button type="submit" @click="login" round>登陆</el-button>
          <el-alert v-if="errMsg" title="" type="error" center show-icon>
            <span>{{ errMsg }}</span>
          </el-alert>
        </div>
      </el-card>
    </el-main>
    <el-footer>Footer</el-footer>
  </el-container>
</template>

<script>
const axios = require('axios');

export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
      errMsg: ""
    };
  },
  methods: {
    login() {
      const data = {
        username: this.username,
        password: this.encrypt(this.password),
      };
      axios.post(
          '/api/login',
          data,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
      ).then((response) => {
        console.log("xx");
        let data = response.data;
        if (data.status === "good") {
          this.$router.push({name: "Balance",})
        } else {
          this.errMsg = data.errMsg;
        }
      });
    },

    encrypt(passwd) {
      const alphabet = "0123456789abcdefghijklmnopqrstuvwxyz";
      const key = 9;
      let result = ""
      for (const c of passwd) {
        const pos = alphabet.indexOf(c)
        const newPos = (pos - key + alphabet.length) % alphabet.length
        result += alphabet.charAt(newPos)
      }
      return result
    },
  },
};
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  min-height: 20vh;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  min-height: 60vh;
}
</style>