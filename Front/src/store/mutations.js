export default {
  setIsLogined(state, isLogin) {
    state.isLogin = isLogin;
  },
  createUser1(state, newuser) {
    state.newUser.email = newuser.email
    state.newUser.password = newuser.password
    state.newUser.nickname = newuser.nickname
  },
  createUser2(state, myteams) {
    state.newUser.myteams = myteams
  }
};
