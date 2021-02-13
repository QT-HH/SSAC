import { createInstance } from "../index.js"

const instance = createInstance();

function listTeams(success, fail) {
    instance
      .get("/profile/newteam")
      .then(success)
      .catch(fail);
  }
  
  function addMyteam(team, success, fail) {
    instance
      .post("/profile/newteam", JSON.stringify(team))
      .then(success)
      .catch(fail);
  }



  export { listTeams, addMyteam };