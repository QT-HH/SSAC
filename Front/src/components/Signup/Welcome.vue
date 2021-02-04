<template>
  <div>
    <h1>환영합니다!</h1>
    <h4>
      SSAC은 종합 스포츠 스케쥴 SNS입니다.<br> 
      언제든지 당신의 팀을 설정하세요!<br>
      친구들과 일정을 공유하세요!<br>
      열심히 활동하여 레벨을 올리세요!
    </h4>

    <button 
      class="btn-botton"
      @click="startSSAC"
    >Let's Start!</button>
  </div>
</template>

<script>
import {login} from "../../api/user/login.js"

export default {
  name: "Welcome",
  methods: {
    startSSAC() {
      localStorage.setItem("access-token", "");
      let email = this.$store.state.newUser.email 
      let password = this.$store.state.newUser.password 
      login(
        email, password,
        (res) => {
          if (res.data.message === "success") {
            let token = res.data["access-token"];
            this.$store.commit("setIsLogined", true);
            localStorage.setItem("access-token", token);

            this.$store.dispatch("GET_MEMBER_INFO", token);
            this.$router.push("/sports");
          } else {
            this.isLoginError = true;
          }
        },
        (err) => {
          console.error(err);
          alert("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
      )
    }
  }
}
</script>

<style>

</style>