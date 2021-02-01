<template>
  <div class="user join wrapC">
    <h1>회원가입</h1>
    <div class="form-wrap">
      <div class="input-with-label">
        <label for="nickname">닉네임</label>
        <input v-model="nickName" id="nickname" placeholder="닉네임을 입력하세요." type="text" />
      </div>

      <div class="input-with-label">
        <label for="email">이메일</label>
        <input 
          v-model="email"
          v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
          id="email" 
          placeholder="이메일을 입력하세요." 
          type="text"
          style="text-transform:lowercase"
        />
        <div class="error-text" v-if="error.email">{{error.email}}</div>
      </div>

      <div class="input-with-label">
        <label for="password">비밀번호</label>
        <input 
          v-model="password" 
          id="password" 
          :type="passwordType" 
          placeholder="비밀번호를 입력하세요."
          v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
        />
        <div class="error-text" v-if="error.password">{{error.password}}</div>
      </div>

      <div class="input-with-label">
        <label for="password-confirm">비밀번호 확인</label>
        <input
          v-model="passwordConfirm"
          :type="passwordConfirmType"
          id="password-confirm"
          placeholder="비밀번호를 다시한번 입력하세요."
          v-bind:class="{error : error.passwordConfirm, complete:!error.passwordConfirm&&passwordConfirm.length!==0}"
        />
        <div class="error-text" v-if="error.passwordConfirm">{{error.passwordConfirm}}</div>
      </div>
    </div>

    <!-- <span @click="termPopup=true">약관보기</span>

    <label>
      <input v-model="isTerm" type="checkbox" id="term" />
      <span>약관을 동의합니다.</span>
    </label> -->

    <button
      class="btn-bottom"
      @click="selectMyteam"
      :disabled="!isSubmit"
      :class="{disabled : !isSubmit}"
      v-if="isSubmit === true"
    >NEXT</button>

  </div>
</template>

<script>
import * as EmailValidator from "email-validator";
import PV from "password-validator";

export default {
  name: "Signup",
  created() {
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
    },
    passwordConfirm: function() {
      this.checkForm();
    },
    // isTerm: function() {
    //   this.checkForm();
    // }
  },

  methods: {
    checkForm() {

      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length >=0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문, 숫자 포함 8자리 이상이어야 합니다.";
      else this.error.password = false;

      if (this.password !== this.passwordConfirm)
        this.error.passwordConfirm = "비밀번호와 일치하지 않습니다."
      else this.error.passwordConfirm = false;

      // if (this.isTerm === false)
      //   this.error.isTerm = true
      // else this.error.isTerm = false

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    selectMyteam() {
      if (this.isSubmit) {
        let {nickName, email, password} = this;
        let newuser = {
          nickName,
          email,
          password
        };
        this.$store.dispatch("CREATE_USER1", newuser);
        this.$router.push("/signup/myteam");
      }
    }
   
  },

  data: () => {
    return {
      email: "",
      password: "",
      passwordConfirm: "",
      passwordSchema: new PV(),
      nickName: "",
      // isTerm: false,
      isLoading: false,
      error: {
        email: false,
        password: false,
        nickName: false,
        passwordConfirm: false,
        // isTerm: false
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      // termPopup: false
    };
  }
};
</script>