import { createInstance } from "../index.js"

const instance = createInstance();
const url = "http://i4d102.p.ssafy.io:9000/ssac"

function getArticle(userid, success, fail) {
  instance
    .get(`${url}/newsfeed/newsFeedList?userid=${userid}`)
    .then(success)
    .catch(fail);
}
 
function writeArticle(userid,content,success, fail) {
  const config = {
    userid: userid,
    content: content
  }
  instance
    .post(`${url}/newsfeed/newsFeedwrite`,JSON.stringify(config))
    .then(success)
    .catch(fail);
}
 
 
function updateArticle(no, content, success, fail) {
  const config = {
    no: no,
    content: content
  }
  instance
    .patch(`${url}/newsfeed/newsFeedUpdate`,JSON.stringify(config))
    .then(success)
    .catch(fail);
}
 
function deleteArticle(no, success, fail) {
  const config = {
    no: no
  }
  instance
    .delete(`${url}/newsfeed/newsFeedDelete`,JSON.stringify(config))
    .then(success)
    .catch(fail);
}
 
function likeArticle(userid, nickname, no, success, fail) {
  const config = {
    userid: userid,
    nickname: nickname,
    no: no
  }
  instance
    .post(`${url}/newsfeed/likeWrite`,JSON.stringify(config))
    .then(success)
    .catch(fail);
}
 
function getLikeUsers(no, success, fail) {
  instance
    .get(`${url}/newsfeed/likeList?no=${no}`)
    .then(success)
    .catch(fail);
}
 
function unlikeArticle(userid, no, success, fail) {
  const config = {
    userid: userid,
    no: no
  }
  instance
    .delete(`${url}/newsfeed/likeDelete`,JSON.stringify(config))
    .then(success)
    .catch(fail);
}
 
function getComment(no, success, fail) {
  instance
    .get(`${url}/newsfeedcomment/commentList?no=${no}`)
    .then(success)
    .catch(fail);
}
 
function writeComment(userid,comment, success, fail) {
  const config = {
    userid: userid,
    comment: comment,
  }
  instance
    .post(`${url}/newsfeedcomment/commentWrite`,JSON.stringify(config))
    .then(success)
    .catch(fail);
}
 
function updateComment(no,comment, success, fail) {
  const config = {
    no: no,
    comment: comment,
  }
  instance
    .patch(`${url}/newsfeedcomment/commentUpdate`,JSON.stringify(config))
    .then(success)
    .catch(fail);
}
 
function deleteComment(no, success, fail) {
  const config = {
    no: no,
  }
  instance
    .delete(`${url}/newsfeedcomment/commentDelete`,JSON.stringify(config))
    .then(success)
    .catch(fail);
}
 
export { getArticle, writeArticle, updateArticle, deleteArticle, likeArticle, getLikeUsers, unlikeArticle, getComment, writeComment, updateComment, deleteComment};