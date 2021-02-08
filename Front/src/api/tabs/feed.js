import { createInstance } from "../index.js"

const instance = createInstance();

function article(success, fail) {

  const body = {

  };

  instance
    .post("/", JSON.stringify(body))
    .then(success)
    .catch(fail);
}


export { article };