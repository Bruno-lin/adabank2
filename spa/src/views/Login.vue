  <template>
  <Card v-bind:image="require('../assets/bank.png')">
    <input type="text" placeholder="用户名" v-model="username" />
    <input type="password" placeholder="密码" v-model="password" />
    <input type="submit" value="登陆" @click="login" />
    <span v-if="errMsg">{{ errMsg }}</span>
  </Card>
</template>

<script>
import Card from "../components/Card.vue";

export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
      errMsg: ""
    };
  },
  components: {
    Card,
  },
  methods: {
    login() {
      const data = {
        username: this.username,
        password: this.encrypt(this.password),
      };
      fetch("/api/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.status === "good") {
            this.$router.push({ name: "Balance" })
          } else {
            this.errMsg = data.errMsg
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
input {
  display: block;
  width: 70%;
  height: 15%;
  margin: 15px auto;
  background: #fff;
  border: 0px;
  padding: 5px;
  font-size: 16px;
  border: 2px solid #fff;
  transition: all 0.3s ease;
  border-radius: 5px;
}

input:focus {
  border: 2px solid #1abc9d;
}

input[type="submit"] {
  display: block;
  background: #1abc9d;
  width: 70%;
  padding: 4px;
  cursor: pointer;
  color: #fff;
  border: 0px;
  margin: auto;
  border-radius: 5px;
  font-size: 12px;
  transition: all 0.3s ease;
  line-height: 12px;
}

input[type="submit"]:hover {
  background: #09cca6;
}

span {
  margin: 0.5em;
  color: red;
}
</style>