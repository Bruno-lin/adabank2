<template>
  <el-container>
    <el-header>
      <el-menu class="el-menu-demo" mode="horizontal">
        <el-menu-item index="1">首页</el-menu-item>
        <el-menu-item index="1">公司金融</el-menu-item>
        <el-menu-item index="1">个人金融</el-menu-item>
        <el-menu-item index="1">银行卡</el-menu-item>
        <el-menu-item index="1">金融市场</el-menu-item>
        <el-menu-item index="1">电子银行</el-menu-item>
        <el-menu-item index="1">投资者关系</el-menu-item>
        <el-menu-item index="1">关于中行</el-menu-item>
        <el-menu-item index="1">
          <img id="accountImg" src="../assets/account.png"/>
          <span>您好，{{ username }}！</span>
          <span>{{ info }}</span>
          <el-button type="submit" @click="logout" square>退出</el-button>
        </el-menu-item>
      </el-menu>
    </el-header>
    <el-main>Main</el-main>
    <el-footer>Footer</el-footer>
  </el-container>
</template>


<script>
const axios = require('axios');

export default {
  name: "Balance",
  data() {
    return {
      info: "您当前余额为1234.56元",
      username: this.getName(),
    };
  },
  methods: {
    logout() {
      let now = new Date();
      document.cookie = "session_id=;expires=" + now.toGMTString();
      this.$router.push({name: "Login"})
    },

    getName() {
      axios.get(
          "/api/name",
          {
            headers: {
              "Content-Type": "application/json",
            }
          })
          .then((response) => {
            let data = response.data;
            if (data.status === "good") {
              this.username = data.name;
            }
          });
    },
  },

  beforeRouteEnter: (to, from, next) => {
    next(vm => {
      vm.getName();
    });
  },
}

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

#accountImg{
  width: 40px;
  height: 40px;
  border-radius: 20px;
}
</style>