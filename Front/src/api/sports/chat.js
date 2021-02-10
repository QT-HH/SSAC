import { createInstance } from "../index.js"

const instance = createInstance();

function listTeam(para, success, fail) {
    instance
      .get("/tabs/chat", { params : para })
      .then(success)
      .catch(fail);
  }