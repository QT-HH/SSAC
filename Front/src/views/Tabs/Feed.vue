<template>
  <div style="width:100%;">
    <v-system-bar color="deep-purple darken-3"></v-system-bar>
    <v-app-bar
      color="deep-purple accent-4"
      dark
      height="54px"
    >
      <v-row>
        <v-col cols="3">
        </v-col>
        <v-col class="mt-1" cols="6">
          <h2 align="center" justify="center">Feed</h2>
        </v-col>
        <v-col cols="3">
        </v-col>
      </v-row>
      <!-- <v-btn icon>
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-filter</v-icon>
      </v-btn> -->
    </v-app-bar>
     
    <!-- 새 글 작성 -->
    <v-card 
      ref="form"
      max-width="350"
      class="mx-auto mb-5"
    >
          <v-textarea
            v-model="newText"
            color="teal"
          >
            <template v-slot:label>
              <div>
                새 글 작성 <small></small>
              </div>
            </template>
          </v-textarea>
          <v-spacer></v-spacer>
          <!-- 사진 업로드 -->
          <v-btn
            icon
            @click.stop="dialog = true"
            >
            <v-icon size="24px">
              mdi-image
            </v-icon>
          </v-btn>          
          <v-dialog v-model="dialog" width="500">
            <v-card>
              <v-file-input 
                class="d-flex align-center justify-center pa-4 mx-auto" 
                label="File input" 
                filled
                prepend-icon="mdi-camera"
                accept="image/*" 
                chips
                v-model="files"
              ></v-file-input>

              <v-divider></v-divider>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    text
                    @click="dialog = false"
                  >취소</v-btn>
                <v-btn color="primary" text @click="upload">
                  적용
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>          
          <!-- 게시글 업로드 -->
          <v-btn
            color="primary"
            text
            @click="createNewFeed"
            class="mx=auto"
          >
            Submit
          </v-btn>
    </v-card>

    <!-- 피드 -->
    <div v-for="(feed, idx) in feeds" :key="idx">
      <v-card
        class="mx-auto mt-5 mb-5"
        color="#E3F2FD"
        light
        max-width="350"
      >
        <v-list-item class="grow">
            <!-- <v-list-item-avatar color="grey darken-3">
              <v-img
                class="elevation-6"
                alt=""
                :src="feed.src"
              ></v-img>
            </v-list-item-avatar> -->

            <v-list-item-content>
              <v-list-item-title>{{ feed.id }}</v-list-item-title>
            </v-list-item-content>

            <v-row
              align="center"
              justify="end"
            >
              <v-icon 
                class="mx-2" 
                v-if="feed.id === user.userid"
                @click="editModal(feed)"
              >
                mdi-pencil
              </v-icon>
              <span class="subheading">{{ feed.regtime }}</span>
            </v-row>
        </v-list-item>

        <v-card-text class="headline font-weight-bold">
          {{ feed.content }}
        </v-card-text>

        
        <v-card-actions>
          <v-list-item class="grow">
            <v-row
              align="center"
              justify="end"
            >
              <v-icon class="mr-1" @click="likeFeed(feed)">
                mdi-heart
              </v-icon>
              <span class="subheading mr-2">{{ feed.like }}</span>
              <span class="mr-1">·</span>
              <v-btn
                icon
                @click="changeModal(feed)"
              >
                <v-icon class="mr-1">                  
                  mdi-message-text
                </v-icon>
              </v-btn>
              1
            </v-row>
          </v-list-item>
        </v-card-actions>

        <!-- 피드의 댓글 -->
        <!-- <v-expand-transition>
          <v-card v-show="show[idx]">
            <v-divider></v-divider>
            <v-card-text>
              <div class="font-weight-bold ml-6 mb-2 overflow-y-scroll">
                Comments
              </div>
              <v-timeline
                align-top
                dense
              >
                <v-timeline-item
                  v-for="comment in feed.comments"
                  :key="comment.time"
                  small
                >
                  <template v-slot:icon>
                    <v-avatar>
                      <img :src="comment.src">
                    </v-avatar>
                  </template>
                  <v-card class="elevation-1">
                    <v-card-text><strong>{{ comment.from }}</strong> @{{ comment.time }}</v-card-text>
                    <v-card-text class="font-weight-normal">
                      {{ comment.message }}
                    </v-card-text>
                  </v-card>
                </v-timeline-item>
              </v-timeline>

              <div class="px-5 py-3 border-b-8 border-lighter flex">
                <div class="flex-none">
                  <v-avatar>
                    <img :src="user.src">
                  </v-avatar>
                  <span>
                    <form v-on:submit.prevent="addNewComment(key)" class="w-full px-4 relative" action="">
                      <textarea v-model="comment.message" placeholder="What's up?" class="w-full focus:outline-none"></textarea>
                      <span>                  
                        <button type="submit" class="h-10 px-4 text-white font-semibold bg-blue hover:bg-darkblue focus:outline-none rounded-full absolute bottom-0 right-0">
                          submit
                        </button>
                      </span>
                    </form>
                  </span>
                </div>
              </div>
            </v-card-text>
          </v-card>
        </v-expand-transition> -->
      
      </v-card>
    </div>
    <br>
    <br>
    <br>
    <br>
    <v-row justify="center">
      <v-dialog
        v-model="modal"
        fullscreen
        hide-overlay
        transition="dialog-bottom-transition"
      >
        <v-card>
          <v-toolbar
            dark
            color="primary"
          >
            <!-- <v-btn
              icon
              dark
              @click="modal = false"
            >
              <v-icon>mdi-close</v-icon>
            </v-btn> -->
            <v-toolbar-title>댓글</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
              <v-btn
                dark
                text
                @click="modal = false"
              >
                닫기
              </v-btn>
            </v-toolbar-items>
          </v-toolbar>

          <v-card
            elevation="5"
            max-width="444"
            class="mx-auto"
          >
            <v-list two-line>
              <v-list-item>
                <v-list-item-avatar>
                  <v-img src="https://cdn.vuetifyjs.com/images/john.png"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title>{{selected.id}}</v-list-item-title>
                  <v-list-item-subtitle>{{selected.id}}</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-list>
            <v-img
              v-if="selected.img"
              :src="selected.img"
            >
            </v-img>
            <v-card-title>
              {{selected.content}}
            </v-card-title>
          </v-card>

          <v-divider></v-divider>
              <v-timeline
                align-top
                dense
              >
                <v-timeline-item
                  v-for="(comment, idx) in comments"
                  :key="idx"
                  small
                >
                  <!-- <template v-slot:icon>
                    <v-avatar>
                      <img :src="comment.src">
                    </v-avatar>
                  </template> -->
                  <v-card class="elevation-1">
                    <v-card-text><strong>{{ comment.nickname }}</strong> @{{ comment.regtime }}</v-card-text>
                    <v-card-text class="font-weight-normal" v-show="!editCommBool[idx]">
                      {{ comment.content }} 
                        <v-icon
                          v-if="comment.id === user.userid"
                          @click="editCommentBool(comment.content,idx)"
                        >
                          <!-- @click="editModal(feed)" -->
                          mdi-pencil
                        </v-icon>
                        <v-icon
                          v-if="comment.id === user.userid"
                          @click="delComment(comment.no,idx)"
                        >
                          mdi-delete
                        </v-icon>
                    </v-card-text>
                    <div v-show="editCommBool[idx]">
                      <v-text-field
                        label="new Comments"
                        solo
                        v-model="editComm"
                      ></v-text-field>
                      <v-btn @click="editCommentBool('',idx)">
                        취소
                      </v-btn>
                      <v-btn @click="editComment(comment,idx)">
                        수정
                      </v-btn>
                    </div>
                  </v-card>
                </v-timeline-item>
              </v-timeline>
                <v-row>
                  <v-col cols=2 class="ml-7">
                    <v-list-item-avatar>
                      <v-img src="https://cdn.vuetifyjs.com/images/john.png"></v-img>
                    </v-list-item-avatar>
                  </v-col>
                  <v-col cols=6 class="px-0">
                    <v-text-field
                      label="new Comments"
                      solo
                      v-model="newComment"
                    ></v-text-field>
                  </v-col>
                  <v-col cols=3>
                    <v-btn @click="addNewComment(selected.no)">
                      입력
                    </v-btn>
                  </v-col>
                </v-row>
        </v-card>
      </v-dialog>
    </v-row>

    <v-row justify="center">
      <v-dialog
        v-model="editmodal"
        fullscreen
        hide-overlay
        transition="dialog-top-transition"
      >
        <v-card>
          <v-toolbar
            dark
            color="primary"
          >
            <!-- <v-btn
              icon
              dark
              @click="modal = false"
            >
              <v-icon>mdi-close</v-icon>
            </v-btn> -->
            <v-toolbar-title>글 수정</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
              <v-btn
                dark
                text
                @click="editmodal = false"
              >
                취소
              </v-btn>
            </v-toolbar-items>
          </v-toolbar>
          <v-divider></v-divider>
          <v-container fluid>
            <v-textarea
              clearable
              clear-icon="mdi-close-circle"
              label="Content"
              v-model="selected.content"
            ></v-textarea>
            <v-file-input 
              class="d-flex align-center justify-center pa-4 mx-auto" 
              label="File input" 
              filled
              prepend-icon="mdi-camera"
              accept="image/*" 
              chips
              v-model="files"
            ></v-file-input>
            ※글 수정에서 사진 업로드시<br>
            기존 사진에서 변경됩니다. <br>
            <v-btn color="primary" @click="upload">
              적용
            </v-btn>
            <v-divider></v-divider>
            <br>
            <v-btn @click="deleteFeed(selected.no)">
              삭제하기
            </v-btn>
            <v-btn @click="updateFeed(selected)">
              수정하기
            </v-btn>
          </v-container>
        </v-card>
      </v-dialog>
    </v-row>

    <br>
    <br>
    <br>
    <br>
    <br>
  </div>
  
</template>

<script>
import { mapState } from 'vuex';
import { getArticle, writeArticle, updateArticle, deleteArticle, likeArticle, getLikeUsers, unlikeArticle,  getComment, writeComment, updateComment, deleteComment} from '@/api/tabs/feed.js'
import axios from "axios" 

export default {
  name:"Feed",
  data () {
    return {
      feeds: [
        {
          // src: require("@/assets/images/corinlee.jpg"), 
          // id:'이경연', 
          // regtime: '10분', 
          // content: '아 제발 대구에 메시 왔으면 좋겠다.', 
          // like: '1,000,003',
          // no:1
          // comments: [
          //   {
          //     from: '박노정',
          //     message: '나도',
          //     time: '3:07am',
          //     src: require("@/assets/images/yesjeong.jpg"),
          //   },
          //   {
          //     from: '백규태',
          //     message: '스타디움 갈 사람~',
          //     time: '3:08am',
          //     src: require("@/assets/images/qt.jpg"),
          //   }
          // ]
        },
        // {
        //   src: require("@/assets/images/qt.jpg"), 
        //   name:'백규태', 
        //   time: '25분', 
        //   article: '맨유 1위~', 
        //   like: '50',
        //   comments: [
        //     {
        //       from: '이경연',
        //       message: '배고프다.',
        //       time: '3:07am',
        //       src: require("@/assets/images/corinlee.jpg"),
        //     },
        //     {
        //       from: '박노정',
        //       message: '야식타임 고고',
        //       time: '3:08am',
        //       src: require("@/assets/images/yesjeong.jpg"),
        //     }
        //   ]          
        // },
        // {
        //   src: require("@/assets/images/yesjeong.jpg"), 
        //   name:'박노정', 
        //   time: '2시간', 
        //   article: '롤챔스 같이 볼 규태형아 구함', 
        //   like: '5,000,000',
        //   comments: [
        //     {
        //       from: '이경연',
        //       message: '나는 왜 안불러?',
        //       time: '3:07am',
        //       src: require("@/assets/images/corinlee.jpg"),
        //     },
        //     {
        //       from: '백규태',
        //       message: '내 토요일에 교회가야된다 노정아. 일요일에는 절 가야하고',
        //       time: '3:08am',
        //       src: require("@/assets/images/qt.jpg"),
        //     }
        //   ]
        // }
      ],
      // show: [false,false,false,false,false,false,false,false,false,false,false,false],
      // comment : {message: ''},
      // user : {
      //   name: "이경연",
      //   src: require("@/assets/images/corinlee.jpg")
      // },
      newText : "",
      newComment : "",
      editComm : "",
      editCommBool : [false,],
      dialog:false,
      modal:false,
      editmodal:false,
      selected:{
        no:Number,
        id:String,
        regtime:String,
        like:Number,
        content:String,
        img:String,
      },
      comments: [],
      editFeed:{
        no:Number,
        id:String,
        regtime:String,
        like:Number,
        content:String,
        img:String,
      },
      files:[],
    }
  },
  computed: {
    ...mapState([
      'user'
    ])
  },
  mounted() {
    // console.log(this.user.userid)
    getArticle(
      this.user.userid,
      (res) => {
        // console.log(res.data)
        this.feeds = res.data
      },
      (err) => {
        console.log(err)
      }
    )
  },
  methods: {
    changeShow(idx) {
      this.$set(this.show, idx, !this.show[idx])
    },
    addNewComment(no) {
      let comm = this.newComment
      if (comm.length === 0){
        alert("아무것도 입력하지 않았습니당")
      } else {
        writeComment(
          this.user.userid,comm,
          (res) => {
            console.log(res.data)
            this.comments.push({
              "regtime": new Date(),
              "no":no,
              "nickname":this.user.nickname,
              "id":this.user.userid,
              "content":comm
            })
          },
          (err) => {
            console.log(err)
            console.log(2)
          }
        )
        this.newComment = ''
      }
    },
    editCommentBool(content,idx){
      this.editCommBool[idx] = !this.editCommBool[idx]
      this.editComm = content
    },
    editComment(comm,idx) {
      let no = comm.no
      let comment = this.editComm
      if (comment.length>0){
        updateComment(
          no,comment,
          (res) => {
            console.log(res.data)
            comm.content = comment
            this.editCommBool[idx] = false
          },
          (err) => {
            console.log(err)
          }
        )
      } else {
        alert("빈 댓글은 허용되지 않습니다.")
      }
    },
    delComment(no,idx){
      deleteComment(
        no,
        (res) => {
          console.log(2)
          console.log(res.data)
          console.log(this.comments,idx)
          // this.comments.splice(idx,1)
        },
        (err) => {
          console.log(3)
          console.log(err)
        }
      )
    },
    createNewFeed() {
      writeArticle(
        this.user.userid,this.newText,
        (res) => {
          console.log(res.data)
          let newArticle = 
          {
              // src: require("@/assets/images/corinlee.jpg"), 
              id:this.user.userid, 
              regtime: new Date(), 
              content: this.newText, 
              like: 0,
              no:20
              // comments: []
          }
          this.feeds.push(newArticle)
          this.newText = ""
        },
        (err) => {
          console.log(err)
        }
      )
    },
    changeModal(feed) {
      this.modal=!this.modal
      this.selected = feed
      // this.selected.no = feed.no
      // this.selected.id = feed.id
      // this.selected.regtime = feed.regtime
      // this.selected.like = feed.like
      // this.selected.content = feed.like
      getComment(
        feed.no,
        (res) => {
          this.comments = res.data
        },
        (err) => {
          console.log(err)
        }
      )
    },
    editModal(feed) {
      this.selected = feed
      this.editmodal=!this.editmodal
    },
    updateFeed(selected) {
      let no = selected.no
      let content = selected.content
      if (content !== null) {
        updateArticle(
          no,content,
          (res) => {
            alert("글이 수정되었습니다")
            this.editmodal = !this.editmodal
            console.log(res.data)
          },
          (err) => {
            console.log(err)
          }
        )
      } else {
        alert("글 내용이 없습니다")
      }
    },
    deleteFeed(no) {
      deleteArticle(
        no,
        (res) => {
          this.editmodal = !this.editmodal
          console.log(res.data)
        },
        (err) => {
          console.log(err)
        }
      )
    },
    likeFeed(event) {
      // let userid = this.user.userid
      let userid = "leegw215@naver.com"
      let nickname = "이지원"
      let no = event.no
      let users = []
      let bool = false
      console.log(event.like)
      getLikeUsers(
        no,
        (res) => {
          // console.log(res.data)
          console.log(1)
          users = res.data
          console.log(res.data)
          for (let idx = 0; idx < users.length; idx++){
            if (users[idx].like_id === userid) {
              bool = true
              break
            }
          }
          console.log(2)
          console.log(bool)
          if (!bool) {
            likeArticle(
              userid,nickname,no,
              (res) => {
                console.log(3)
                console.log(res.data)
                event.like *= 1
                event.like += 1
                event.like += ''
              },
              (err) => {
                console.log(err)
              }
            )

          } else {
            unlikeArticle(
              userid,no,
              (res) => {
                console.log(res.data)
                event.like *= 1
                event.like -= 1
                event.like += ''
              },
              (err) => {
                console.log(err)
              }
            )
          }
        },
        (err) => {
          console.log(err)
        }
      )

    },
    unlikeFeed(userid, no){
      unlikeArticle(
        userid,no,
        (res) => {
          console.log(res.data)
        },
        (err) => {
          console.log(err)
        }
      )
    },
    likeUsers(no){
      getLikeUsers(
        no,
        (res) => {
          console.log(res.data)
        },
        (err) => {
          console.log(err)
        }
      )
    },
    async upload(){
      this.dialog = false
      let fd = new FormData();
      fd.append('files',this.files)
      await axios.post('http://localhost:1337/upload',
        fd, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
      ).then( response => {
        console.log('SUCCESS!!');
        console.log(response.data)
      })
      .catch(function () {
        console.log('FAILURE!!');
      });
    }

  },
}
</script>

<style>

</style>