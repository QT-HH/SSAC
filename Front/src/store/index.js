import Vue from "vue";
import Vuex from "vuex";
import getters from "./getters";
import actions from "./actions";
import mutations from "./mutations";

// import jwt_decode from "jwt-decode";
// import { findById } from "@/api/user.js";

Vue.use(Vuex);

const state = {
  isLogin: false,
  userInfo: null,
  newUser: {
    email: "",
    password: "",
    nickname: "",
    myteams: []
  }
};

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions
});
