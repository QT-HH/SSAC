<template>
  <div class="user" id="login">
    <v-parallax src="https://cdn.vuetifyjs.com/images/parallax/material.jpg">
    <div class="wrapC">
      <h1>
        SSAC
      </h1>

      <div class="input-with-label">
        <label for="email">ID</label>
        <input
          v-model="email"
          v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
          @keyup.enter="Login"
          id="email"
          placeholder="이메일을 입력하세요."
          type="text"
          style="text-transform:lowercase"
        />
        <div class="error-text" v-if="error.email">{{error.email}}</div>
      </div>

      <div class="input-with-label">
        <label for="password">PW</label>
        <input
          v-model="password"
          type="password"
          v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
          id="password"
          @keyup.enter="Login"
          placeholder="비밀번호를 입력하세요."
        />
        <div class="error-text" v-if="error.password">{{error.password}}</div>
      </div>
      <v-btn
        color="blue"
        @click="onLogin"
        :disabled="!isSubmit"
        :class="{disabled : !isSubmit}"
        v-if="isSubmit === true"
      >로그인</v-btn>

      <div class="sns-login">
        <div class="text">
          <p>SNS 간편 로그인</p>
          <v-btn
          color="yellow"
          @click="KakaoLogin"
            >카카오톡</v-btn>
          <v-btn
          color="green"
          @click="NaverLogin"
            >네이버</v-btn>
        </div>

        <!-- <kakaoLogin :component="component" /> -->
        <!-- <NaverLogin :component="component" /> -->
        <!-- <GoogleLogin :component="component" />  -->
      </div>
      <div class="add-option">
        <div class="text">
          <div class="bar"></div>
        </div>
        <div class="wrap">
          <p>비밀번호를 잊으셨나요?</p>
        </div>
        <div class="wrap">
          <p>아직 회원이 아니신가요?</p>
          <router-link to="/signup" class="btn--text">가입하기</router-link>
        </div>
      </div>
    </div>
  </v-parallax>
  </div>
</template>

<script>
// import "../../components/css/user.scss";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
// import KakaoLogin from "../../components/user/snsLogin/Kakao.vue";
// import GoogleLogin from "../../components/user/snsLogin/Google.vue";
// import UserApi from "../../api/UserApi";
import { login, kakaologin, naverlogin } from "@/api/user/login.js";


export default {
  components: {
    // KakaoLogin,
    // GoogleLogin
  },
  created() {
    this.component = this;

    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
  watch: {
    password: function() {
      this.checkForm();
    },
    email: function() {
      this.checkForm();
    }
  },
  methods: {
    checkForm() {    
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    onLogin() {
      if (this.isSubmit) {
        localStorage.setItem("access-token", "");
        login(
          this.email, this.password,
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
    },
    KakaoLogin() {
      localStorage.setItem("access-token", "");
      kakaologin(
          (res) => {
            if (res.data.message === "success") {
              let token = res.data["access-token"];
              this.$store.commit("setIsLogined", true);
              localStorage.setItem("access-token", token);
  
              this.$store.dispatch("GET_KAKAO_INFO", token);
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
    },
    NaverLogin() {
      localStorage.setItem("access-token", "");
      naverlogin(
          (res) => {
            if (res.data.message === "success") {
              let token = res.data["access-token"];
              this.$store.commit("setIsLogined", true);
              localStorage.setItem("access-token", token);
  
              this.$store.dispatch("GET_NAVER_INFO", token);
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
  },
  data: () => {
    return {
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false
      },
      isSubmit: false,
      component: this
    };
  }
};
</script>