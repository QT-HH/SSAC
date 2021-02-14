<template>

  <div>
    <Chat 
          :participants="participants"
          :myself="myself"
          :messages="messages"
          :chat-title="chatTitle"
          :placeholder="placeholder"
          :colors="colors"
          :border-style="borderStyle"
          :hide-close-button="hideCloseButton"
          :close-button-icon-size="closeButtonIconSize"
          :submit-icon-size="submitIconSize"
          :submit-image-icon-size="submitImageIconSize"
          :load-more-messages="toLoad.length > 0 ? loadMoreMessages : null"
          :link-options="linkOptions"
          :async-mode="asyncMode"
          :scroll-bottom="scrollBottom"
          :display-header="true"
          :send-images="true"
          :profile-picture-config="profilePictureConfig"
          :timestamp-config="timestampConfig"
          @onImageClicked="onImageClicked"
          @onImageSelected="onImageSelected"
          @onMessageSubmit="onMessageSubmit"
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

import {Chat} from 'vue-quick-chat';
import 'vue-quick-chat/dist/vue-quick-chat.css';
import {chatroomEnter} from '@/api/sports/chat.js'

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
            participants: [
            ],
            myself: {
            },
            chatTitle: 'My chat title',
            placeholder: 'send your message',
            colors: {
                header: {
                    bg: '#536DFE',
                    text: '#fff'
                },
                message: {
                    myself: {
                        bg: '#fff',
                        text: '#bdb8b8'
                    },
                    others: {
                        bg: '#536DFE',
                        text: '#fff'
                    },
                    messagesDisplay: {
                        bg: '#f7f3f3'
                    }
                },
                submitIcon: '#536DFE',
                submitImageIcon: '#536DFE',
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
            submitImageIconSize: 20,
            asyncMode: false,
            scrollBottom: {
                messageSent: true,
                messageReceived: true
            },
            displayHeader:true,
            profilePictureConfig: {
                others: true,
                myself: true,
                styles: {
                    width: '30px',
                    height: '30px',
                    borderRadius: '50%'
                }
            },
            timestampConfig: {   
                format: 'HH:mm',
                relative: false
            },
            // there are other options, you can check them here
            // https://soapbox.github.io/linkifyjs/docs/options.html
        }
    },
    created () {
        let para = {
            roomid: this.$store.state.chat.idx,
            userid: this.$store.state.user.userid
        };
        chatroomEnter(
            para,
            (response) => {
                console.log(response);
                // this.participants: response.data
                // this.myself: response.data
                // this.messages: response.data
            },
            (error) => {
                console.log(error)
            });

    },
    methods: {
        onType: function (event) {
            //here you can set any behavior
            console.log(event);
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