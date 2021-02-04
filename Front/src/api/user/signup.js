import { createInstance } from "../index.js";

const instance = createInstance();

function signup(newuser, success, fail) {

  const body = {
    userid: newuser.email,
    userpw: newuser.password,
    usernickname: newuser.nickname,
    userteam: newuser.myteams
  };

  instance
    .post("http:/localhost:9000/ssac/user", JSON.stringify(body))
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

export { signup };