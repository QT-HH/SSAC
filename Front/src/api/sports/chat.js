import { createInstance } from "../index.js"

const instance = createInstance();
//채팅방 생성 리스트


//채팅방 목록 조회
function listChatroom(userid, success, fail) {
  instance
    .get("chat/roomList", userid)
    .then(success)
    .catch(fail);
}

//채팅방 입장
function chatroomEnter(para, success, fail) {
  instance
    .get("chat/roomEnter", { params : para })
    .then(success)
    .catch(fail);
}


export {listChatroom, chatroomEnter}