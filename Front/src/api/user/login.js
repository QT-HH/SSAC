import { createInstance } from "../index.js";

const instance = createInstance();

function login(email, password, success, fail) {
  instance.defaults.headers["access-token"] = window.localStorage.getItem(
    "access-token"
  );
  const body = {
    userid: email,
    userpw: password
  };

  instance
    .post("/", JSON.stringify(body))
    .then(success)
    .catch(fail);
}


async function findById(email, success, fail) {
  instance.defaults.headers["access-token"] = window.localStorage.getItem(
    "access-token"
  );
  await instance
    .get(``)
    .then(success)
    .catch(fail);
}

export { login, findById };