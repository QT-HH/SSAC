import { createInstance } from "../index.js";

const instance = createInstance();
const url = "http://i4d102.p.ssafy.io:9000/ssac"

function getAlert(success,fail){
  instance
    .get(`${url}`)
    .then(success)
    .catch(fail);
}

export { getAlert }