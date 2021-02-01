import jwt_decode from "jwt-decode";

export default {
  async GET_MEMBER_INFO({ commit }, token) {
    let decode = jwt_decode(toekn);

    await findById(
      decode.email,
      (res) => {
        if (res.data.message === "success") {
          commit("setUserInfo", res.data.userInfo);
        } else {
          console.log("존재하지 않는 사용자 입니다.");
        }
      },
      (err) => {
        console.log(err);
      }
    );
  },
  async CREATE_USER1({ commit }, newuser) {
    commit('createUser1', newuser)
  },
  async CREATE_USER2({ commit }, myteams) {
    commit('createUser2', myteams)
  }
};
