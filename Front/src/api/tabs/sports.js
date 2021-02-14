import { createInstance } from "../index.js";

const instance = createInstance();

// http://i4d102.p.ssafy.io:9000/ssac/schedule/schedule

function getSchedule(success,fail) {
  instance
    .get("http://i4d102.p.ssafy.io:9000/ssac/schedule/schedule")
    .then(success)
    .catch(fail);
}

export { getSchedule };