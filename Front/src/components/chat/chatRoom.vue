<template>

  <div>
    <Chat 
       :participants="participants"
       :myself="myself"
       :messages="messages"
       :onType="onType"
       :onMessageSubmit="onMessageSubmit"
       :chatTitle="chatTitle"
       :placeholder="placeholder"
       :colors="colors"
       :borderStyle="borderStyle"
       :hideCloseButton="hideCloseButton"
       :closeButtonIconSize="closeButtonIconSize"
       :submitIconSize="submitIconSize"
          @onImageClicked="onImageClicked"
          @onImageSelected="onImageSelected"
          @onMessageSubmit="sendMessage"
          @onType="onType"
          @onClose="onClose">
          </Chat>
  </div>

</template>

<script src="/webjars/vue/2.5.16/dist/vue.min.js"></script>
<script src="/webjars/axios/0.17.1/dist/axios.min.js"></script>
<script src="/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/2.3.3-1/stomp.min.js"></script>
<script>

import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

import {Chat} from 'vue-quick-chat';
import 'vue-quick-chat/dist/vue-quick-chat.css';
import {chatroomEnter} from '@/api/sports/chat.js'

var sock = new SockJS("http://i4d102.p.ssafy.io:9090/ws-stomp");
var ws = Stomp.over(sock);

export default {
    name: 'ChatRoom',
    components: {
        Chat
    },
    data() {
        return {
            roomId: '',
            room: {},
            sender: '',
            message: '',
            // 메세지 담기
            messages: [],
            visible: true,
            participants: [],
            myself: {},
            messages: [],
            chatTitle: 'My chat title',
            placeholder: 'send your message',
            colors: {
                header: {
                    bg: '#d30303',
                    text: '#fff'
                },
                message: {
                    myself: {
                        bg: '#fff',
                        text: '#bdb8b8'
                    },
                    others: {
                        bg: '#fb4141',
                        text: '#fff'
                    },
                    messagesDisplay: {
                        bg: '#f7f3f3'
                    }
                },
                submitIcon: '#b91010',
                submitImageIcon: '#b91010',
            },
            borderStyle: {
                topLeft: "10px",
                topRight: "10px",
                bottomLeft: "10px",
                bottomRight: "10px",
            },
            hideCloseButton: false,
            submitIconSize: 25,
            closeButtonIconSize: "20px",
            asyncMode: false,
            toLoad: [
                {
                    content: 'Hey, John Doe! How are you today?',
                    myself: false,
                    participantId: 2,
                    timestamp: {year: 2011, month: 3, day: 5, hour: 10, minute: 10, second: 3, millisecond: 123},
                    uploaded: true,
                    viewed: true,
                    type: 'text'
                },
                {
                    content: "Hey, Adam! I'm feeling really fine this evening.",
                    myself: true,
                    participantId: 3,
                    timestamp: {year: 2010, month: 0, day: 5, hour: 19, minute: 10, second: 3, millisecond: 123},
                    uploaded: true,
                    viewed: true,
                    type: 'text'
                },
            ],
            scrollBottom: {
                messageSent: true,
                messageReceived: false
            },
            displayHeader:true,
            timestampConfig: {   
                format: 'HH:mm',
                relative: false
            },
            // there are other options, you can check them here
            // https://soapbox.github.io/linkifyjs/docs/options.html
            linkOptions: {
                myself: {
                    className: 'myLinkClass',
                    events: {
                        click: function (e) {
                            alert('Link clicked!');
                        },
                        mouseover: function (e) {
                            alert('Link hovered!');
                        }
                    },
                    format: function (value, type) {
                        if (type === 'url' && value.length > 50) {
                            value = value.slice(0, 50) + '…';
                        }
                        return value;
                    }
                },
                others: {
                    className: 'othersLinkClass',
                    events: {
                        click: function (e) {
                            alert('Link clicked!');
                        },
                        mouseover: function (e) {
                            alert('Link hovered!');
                        }
                    },
                    format: function (value, type) {
                        if (type === 'url' && value.length > 50) {
                            value = value.slice(0, 50) + '…';
                        }
                        return value;
                    }
                }
            },
            // there are other options, you can check them here
            // https://soapbox.github.io/linkifyjs/docs/options.html
        }
    },
    created () {
        let para = {
            roomid: this.$store.state.chat.roomid,
            userid: this.$store.state.user.userid
        };
        chatroomEnter(
            para,
            (response) => {
                console.log(response);
                this.participants= response.data.participants
                this.myself= response.data.myself
                this.messages= response.data.messages
                this.connect()
            },
            (error) => {
                console.log(error)
            });

    },
    methods: {
        connect() {
            // pub/sub event
            ws.connect({}, function(frame) {
                ws.subscribe("/sub/chat/room/"+this.$store.state.chat.roomid, function(message) {
                    var recv = JSON.parse(message.body);
                    console.log(recv);
                    vm.recvMessage(recv);
                });
                ws.send("/pub/chat/message", {}, JSON.stringify({type: 'TALK', message: "asd",roomId: this.$store.state.chat.roomid, sender:this.myself.id,}));
            }, function(error) {
                console.log(error);
            });
        },
        onType: function (event) {
            //here you can set any behavior
            console.log(event);
        },
        sendMessage: function() {
            ws.send("http://i4d102.p.ssafy.io:9090/pub/chat/message", {}, JSON.stringify({type:'TALK', roomId:this.$store.state.chat.roomid, sender:this.myself.id, message:this.message}));
            this.message = '';
        },
        recvMessage: function(recv) {
            this.messages.unshift({"type":recv.type,"sender":recv.type=='ENTER'?'[알림]':recv.sender,"message":recv.message})
        },
        loadMoreMessages(resolve) {
            setTimeout(() => {
                resolve(this.toLoad); //We end the loading state and add the messages
                //Make sure the loaded messages are also added to our local messages copy or they will be lost
                this.messages.unshift(...this.toLoad);
                this.toLoad = [];
            }, 1000);
        },
        onMessageSubmit: function (message) {
            /*
            * example simulating an upload callback. 
            * It's important to notice that even when your message wasn't send 
            * yet to the server you have to add the message into the array
            */
            this.message = message;
            this.messages.push(message);
        
            /*
            * you can update message state after the server response
            */
            // timeout simulating the request
            setTimeout(() => {
                message.uploaded = true
            }, 2000)
        },
        onClose() {
            this.visible = false;
            this.$store.dispatch('footerOut');
            this.$router.push({name:'SportsChat'});
        },
        onImageSelected(files, message){
            let src = 'https://149364066.v2.pressablecdn.com/wp-content/uploads/2017/03/vue.jpg'
            this.messages.push(message);
            /**
             * This timeout simulates a requisition that uploads the image file to the server.
             * It's up to you implement the request and deal with the response in order to
             * update the message status and the message URL
             */
            setTimeout((res) => {
                message.uploaded = true
                message.src = res.src
            }, 3000, {src});
        },
        onImageClicked(message){
            /**
             * This is the callback function that is going to be executed when some image is clicked.
             * You can add your code here to do whatever you need with the image clicked. A common situation is to display the image clicked in full screen.
             */
            console.log('Image clicked', message.src)
        }
    }
}

</script>

<style>

.quick-chat-container {
    height: 100vh;
}
</style>