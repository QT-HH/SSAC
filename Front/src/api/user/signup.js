import { createInstance } from "../index.js";

const instance = createInstance();

function signup(newuser, success, fail) {

  const body = {
    userid: newuser.email,
    userpw: newuser.password,
    usernickname: newuser.nickName,
    userteam: newuser.myteams
  };
  console.log(body)
  console.log(JSON.stringify(body))
  instance
    .post("http://i4d102.p.ssafy.io:9000/ssac/user/signup", JSON.stringify(body))
    .then(success)
    .catch(fail);
}

function getUser(userid, success, fail) {

  instance
    .get(`http://i4d102.p.ssafy.io:9000/ssac/user/userSelect?userid=${userid}`)
    .then(success)
    .catch(fail);
}


// async function getteams(success, fail) {
//   instance.defaults.headers["access-token"] = window.localStorage.getItem(
//     "access-token"
//   );
//   await instance
//     .get(``)
//     .then(success)
//     .catch(fail);
// }


// async function findById(email, success, fail) {
//   instance.defaults.headers["access-token"] = window.localStorage.getItem(
//     "access-token"
//   );
//   await instance
//     .get(``)
//     .then(success)
//     .catch(fail);
// }

export { signup, getUser };
