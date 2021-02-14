import Vue from 'vue'
import Vuex from 'vuex'
import getters from "./getters";
import actions from "./actions";
import mutations from "./mutations";
import socket from './modules/socket';

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production';

const state = {
  isLogin: true,
  isChatRoom: false,
  userInfo: null,
  isNav: false, 
  newUser: {
    email: "",
    password: "",
    nickname: "",
    myteams: []
  },
  surveyAnswers: [],
  chat: {
    idx: 0
  },
  user: {
    userid: 'aaa@aaa.com',
    password: "123123123",
    nickname: "nojjeong",
    myteams: ["T1"],
    img: "https://placekitten.com/300/300",
    introduce: "Frei Aber Froh"
  },
  myTeams: [
    {name: "T1", img: "https://placekitten.com/300/300" , nickname: "", count: 14565, event_no: 3},
    {name: "대구FC", img: "https://placekitten.com/300/300" , nickname: "", count: 1234, event_no: 1},
    {name: "삼성라이온즈", img: "https://placekitten.com/300/300" , nickname: "", count: 76567, event_no: 2},
  ],
  teams: [
    {name: "T1", img: "https://placekitten.com/300/300" , nickname: "", count: 14565, event_no: 3},
    {name: "GEN.G", img: "https://placekitten.com/300/300" , nickname: "", count: 14565, event_no: 3},
    {name: "SSG 일렉트로스", img: "https://placekitten.com/300/300", nickname: "", count: 14565, event_no: 2},
    {name: "롯데 자이언츠", img: "https://placekitten.com/300/300", nickname: "", count: 14565, event_no: 2},
    {name: "FC서울", img: "https://placekitten.com/300/300", nickname: "", count: 14565, event_no: 1},
    {name: "수원삼성 블루윙즈", img: "https://placekitten.com/300/300", nickname: "", count: 14565, event_no: 1},
    {name: "DRX", img: "https://placekitten.com/300/300", nickname: "", count: 14565, event_no: 3},
    {name: "리브샌박", img: "https://placekitten.com/300/300", nickname: "", count: 14565, event_no: 3},
    {name: "전북현대", img: "https://placekitten.com/300/300", nickname: "", count: 14565, event_no: 1},
    {name: "LG 트윈스", img: "https://placekitten.com/300/300", nickname: "", count: 14565, event_no: 2},
    {name: "두산 베어스", img: "https://placekitten.com/300/300", nickname: "", count: 14565, event_no: 2},
    {name: "한화생명", img: "https://placekitten.com/300/300", nickname: "", count: 14565, event_no: 3},
    {name: "대구FC", img: "https://placekitten.com/300/300", nickname: "", count: 1234, event_no: 1},
    {name: "삼성라이온즈", img: "https://placekitten.com/300/300", nickname: "", count: 76567, event_no: 2},
  ],
  articles: [
    {title: "오늘 경기 완전 노잼이었음", context: "나는 저돈 반만 받아도 되니까 내가 뛸게 걍"},
    {title: "지금은 새벽 1시", context: "아직은 할만하다"},
    {title: "근데 완전 눈이 감길라카는건 어쩌지", context: "안된다 오늘은 안잔다는 마인드니까"},
    {title: "침착맨은 한줄기 희망이다", context: "내가 잠오는걸 강제로 막아준다"},
    {title: "이대호 연봉 10억 넘네", context: "진짜 부럽다 나도 연봉 10억 찍어보고싶다."},
    {title: "데이식스 노래도 진짜 좋다", context: "들어도 들어도 안질리고 신난ㄴ다"},
    {title: "근데 여기서 의문인점", context: "나는 여기서 왜 일기를 쓰고 있는가"},
    {title: "왜긴 왜야", context: "더미를 채워야하기 때문이지"},
    {title: "마지막 할말은", context: "우리조,,,, 그냥,,, 화이팅,,,!!!"},
  ]
};

export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
  modules: {
    socket,
  },
  strict: debug,
  // plugins: debug ? [createLogger()] : []
})
