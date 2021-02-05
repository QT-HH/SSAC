export default {
  setIsLogined(state, isLogin) {
    state.isLogin = isLogin;
  },
  createUser1(state, newuser) {
    state.newUser.email = newuser.email
    state.newUser.password = newuser.password
    state.newUser.nickname = newuser.nickName
  },
  createUser2(state, myteams) {
    state.newUser.myteams = myteams
  },
  ADD_ANSWER: function (state, answerData) {
    // console.log('mutations')
    // console.log(answerData)
    state.surveyAnswers.push(answerData)
  },
  ADD_MYTEAM: function (state, selectTeam) {
    // console.log('mutations')
    console.log(selectTeam)
    state.myTeams.push(selectTeam)
  }  
};
