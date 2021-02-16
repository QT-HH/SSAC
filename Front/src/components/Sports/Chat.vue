<template>
  <div>
    <template>
      <v-row width="max-content">
        <v-col width="max-content">
          <v-card style="height: 530px" width="max-content">
            <v-list two-line>
              
              <v-list-item  
              v-for="(chat,idx) in chats" 
              :key="idx" 
              link 
              :to="`chat-room/${idx}`"
               >
                <v-list-item-avatar @click="footerOut">
                  <v-icon>
                    fas fa-search
                  </v-icon>
                </v-list-item-avatar>

                <v-list-item-content @click="footerOut; selectChat(chat.room.roomId)">  
                  <v-list-item-title>{{ chat.room.name }}</v-list-item-title>
                  <v-list-item-subtitle>
                    <v-chip v-for="(name, idx) in chat.usernames" :key="idx" class="ma-2" x-small>
                      {{ name }}
                    </v-chip>
                  </v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-action>
                  <v-btn icon>
                    <!-- <v-icon color="grey lighten-1">
                      mdi-information
                    </v-icon> -->
                  </v-btn>
                </v-list-item-action>
              </v-list-item>
            </v-list>
            <v-fab-transition>
              <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn color="indigo accent-2" dark absolute bottom right v-bind="attrs" v-on="on" @click="makechat">
                    <v-icon>mdi-plus</v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-toolbar dark color="indigo accent-2">
                    <v-btn icon dark @click="dialog = false">
                      <v-icon>mdi-close</v-icon>
                    </v-btn>
                    <v-toolbar-title>채팅 만들기</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-toolbar-items @click="dialog = false">
                      <!--  -->
                      <v-btn dark text @click="makechatroom">
                        생성
                      </v-btn>
                      <!--  -->
                    </v-toolbar-items>
                  </v-toolbar>
                  <v-list three-line subheader>
                    <v-subheader>대상 경기</v-subheader>
                    <v-list-item>
                      <v-list-item-content>
                        <v-container v-for="(game, idx) in games" :key="idx">
                          <v-card class="text-center align-center justify-center pa-0 mx-0 rounded-card" link @click="teamselect(game)">
                            <v-img
                              src="https://placekitten.com/300/300"
                              class="white--text align-end"
                              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                              height="100px"
                            >
                            <v-card-title class="justify-center">
                              <div class="headerClass">
                                {{game}}
                            </div>
                              </v-card-title>
                            </v-img>
                              
                          </v-card>
                        </v-container>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list>
                  <v-divider></v-divider>
                  <v-list three-line subheader>
                    <v-subheader>초대할 멤버</v-subheader>
                    <v-list-item >
                      <v-container v-for="(member, idx) in members" :key="idx">
                        <v-list-item-content style="width:50px " @click="memberselect(member.nickname,member.id)">
                          <v-chip dark class="justify-center indigo accent-2">{{member.nickname}}</v-chip>
                        </v-list-item-content>
                      </v-container>
                    </v-list-item>
                  </v-list>
                </v-card>
              </v-dialog>
            </v-fab-transition>
          </v-card>
        </v-col>
      </v-row>
    </template>
  </div>
</template>

<script>

import {listChatroom, createChat, registerChat} from '@/api/sports/chat.js'


export default {
  name:"chat",
  data () {
      return {
        dialog: false,
        notifications: false,
        sound: true,
        widgets: false,
        idx:0,
        // 채팅방 이름
        name: '',
        //채팅멤버 아이디 배열
        ids: [this.$store.state.user.userid],
        //채팅멤버 닉네임 배열
        nicknames: [this.$store.state.user.nickname],
        chats: [],
        // 여기는 채팅만드는 팀
        games: [],
        members: []
      }
    },
    created() {
      listChatroom(
      this.$store.state.user.userid,
      (response) => {
        console.log(response);
        this.chats = response.data 
      },
      (error) => {
        console.log(error)
      });



    },
    methods: {
      footerOut: function () {
        this.$store.dispatch('footerOut');
        console.log(this.$store.state.isChatRoom);
      },
      selectChat(roomid) {
        console.log(roomid);
        this.$store.state.chat.roomid = roomid
      },
      teamselect(game) {
        this.name = game
        console.log(this.name);
      },
      memberselect(nickname, id) {
        this.nicknames.push(nickname)
        this.ids.push(id)
        console.log(this.ids);
        console.log(this.nicknames);

      },
      makechat() {
        createChat(
          this.$store.state.user.userid,
          (response) => {
            console.log(response);
            this.games = response.data.game
            this.members = response.data.follow
          },
          (error) => {
            console.log(error)
          });
      },
      makechatroom() {
        let para = {
          name: this.name,
          ids: this.ids,
          nicknames: this.nicknames
        };
        registerChat(
          para,
          (response) => {
            console.log(response);
            console.log(para);
            // this.$router.push({name:"SportsChat"})
            listChatroom(
              this.$store.state.user.userid,
              (response) => {
                console.log(response);
                this.chats = response.data 
              },
              (error) => {
                console.log(error)
              });
            },  
          (error) => {
            console.log(error)
          });
      }
    }
}
</script>

<style>
.v-list-item {
  border-bottom: 1px;
  border-bottom-color: black
}
.rounded-card{
    border-radius:20px;
}
.headerClass{
    white-space: nowrap ;
    word-break: normal;
    overflow: hidden ;
    text-overflow: ellipsis;
}
</style>