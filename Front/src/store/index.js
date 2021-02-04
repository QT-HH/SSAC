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
  myTeams: [
    {name: "T1", img: "https://placekitten.com/300/300" , nickname: "", followers: 14565, category: "lol"},
    {name: "대구FC", img: "https://placekitten.com/300/300" , nickname: "", followers: 1234, category: "football"},
    {name: "삼성라이온즈", img: "https://placekitten.com/300/300" , nickname: "", followers: 76567, category: "baseaball"},
  ],
  teams: [
    {name: "T1", img: "https://placekitten.com/300/300" , nickname: "", followers: 14565, category: "lol"},
    {name: "GEN.G", img: "https://placekitten.com/300/300" , nickname: "", followers: 14565, category: "lol"},
    {name: "SSG 일렉트로스", img: "https://placekitten.com/300/300", nickname: "", followers: 14565, category: "baseball"},
    {name: "롯데 자이언츠", img: "https://placekitten.com/300/300", nickname: "", followers: 14565, category: "baseball"},
    {name: "FC서울", img: "https://placekitten.com/300/300", nickname: "", followers: 14565, category: "football"},
    {name: "수원삼성 블루윙즈", img: "https://placekitten.com/300/300", nickname: "", followers: 14565, category: "football"},
    {name: "DRX", img: "https://placekitten.com/300/300", nickname: "", followers: 14565, category: "lol"},
    {name: "리브샌박", img: "https://placekitten.com/300/300", nickname: "", followers: 14565, category: "lol"},
    {name: "전북현대", img: "https://placekitten.com/300/300", nickname: "", followers: 14565, category: "football"},
    {name: "LG 트윈스", img: "https://placekitten.com/300/300", nickname: "", followers: 14565, category: "baseball"},
    {name: "두산 베어스", img: "https://placekitten.com/300/300", nickname: "", followers: 14565, category: "baseball"},
    {name: "한화생명", img: "https://placekitten.com/300/300", nickname: "", followers: 14565, category: "lol"},
    {name: "대구FC", img: "https://placekitten.com/300/300", nickname: "", followers: 1234, category: "football"},
    {name: "삼성라이온즈", img: "https://placekitten.com/300/300", nickname: "", followers: 76567, category: "baseball"},
  ]
};

export default new Vuex.Store({
  state,
  getters,
  actions,
  mutations
});
