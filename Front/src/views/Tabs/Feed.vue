<template>
  <div id="Feed" class="flex container h-screen w-full">
     
    <!-- 새 글 작성 -->
    <div class="px-5 py-3 border-b border-lighter flex items-center justify-between">
        <h1 class="text-xl font-bold">Feed</h1>
    </div>
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
              <v-file-input class="d-flex align-center justify-center pa-4 mx-auto" label="File input" filled
                prepend-icon="mdi-camera"></v-file-input>

              <v-divider></v-divider>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    text
                    @click="dialog = false"
                  >취소</v-btn>
                <v-btn color="primary" text @click="dialog = false">
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
            <v-list-item-avatar color="grey darken-3">
              <v-img
                class="elevation-6"
                alt=""
                :src="feed.src"
              ></v-img>
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>{{ feed.name }}</v-list-item-title>
            </v-list-item-content>

            <v-row
              align="center"
              justify="end"
            >
              <v-icon class="mx-2">
                mdi-pencil
              </v-icon>
              <span class="subheading">{{ feed.time }} 전</span>
            </v-row>
        </v-list-item>

        <v-card-text class="headline font-weight-bold">
          {{ feed.article }}
        </v-card-text>

        
        <v-card-actions>
          <v-list-item class="grow">
            <v-row
              align="center"
              justify="end"
            >
              <v-icon class="mr-1">
                mdi-heart
              </v-icon>
              <span class="subheading mr-2">{{ feed.like }}</span>
              <span class="mr-1">·</span>
              <v-btn
                icon
                @click="changeShow(idx)"
              >
                <v-icon class="mr-1">                  
                  mdi-message-text
                </v-icon>
              </v-btn>
            </v-row>
          </v-list-item>
        </v-card-actions>

        <!-- 피드의 댓글 -->
        <v-expand-transition>
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
        </v-expand-transition>
      
      </v-card>
    </div>
    <br>
    <br>
    <br>
    <br>

  </div>
  
</template>

<script>
import { mapState } from 'vuex';

export default {
  name:"Feed",
  data () {
    return {
      feeds: [
        {
          src: require("@/assets/images/corinlee.jpg"), 
          name:'이경연', 
          time: '10분', 
          article: '아 제발 대구에 메시 왔으면 좋겠다.', 
          like: '1,000,003',
          comments: [
            {
              from: '박노정',
              message: '나도',
              time: '3:07am',
              src: require("@/assets/images/yesjeong.jpg"),
            },
            {
              from: '백규태',
              message: '스타디움 갈 사람~',
              time: '3:08am',
              src: require("@/assets/images/qt.jpg"),
            }
          ]
        },
        {
          src: require("@/assets/images/qt.jpg"), 
          name:'백규태', 
          time: '25분', 
          article: '맨유 1위~', 
          like: '50',
          comments: [
            {
              from: '이경연',
              message: '배고프다.',
              time: '3:07am',
              src: require("@/assets/images/corinlee.jpg"),
            },
            {
              from: '박노정',
              message: '야식타임 고고',
              time: '3:08am',
              src: require("@/assets/images/yesjeong.jpg"),
            }
          ]          
        },
        {
          src: require("@/assets/images/yesjeong.jpg"), 
          name:'박노정', 
          time: '2시간', 
          article: '롤챔스 같이 볼 규태형아 구함', 
          like: '5,000,000',
          comments: [
            {
              from: '이경연',
              message: '나는 왜 안불러?',
              time: '3:07am',
              src: require("@/assets/images/corinlee.jpg"),
            },
            {
              from: '백규태',
              message: '내 토요일에 교회가야된다 노정아. 일요일에는 절 가야하고',
              time: '3:08am',
              src: require("@/assets/images/qt.jpg"),
            }
          ]
        }
      ],
      show: [false,false,false,false,false,false,],
      comment : {message: ''},
      user : {
        name: "이경연",
        src: require("@/assets/images/corinlee.jpg")
      },
      newText : "",
      dialog:false
    }
  },
  computed: {
    ...mapState([
      'user'
    ])
  },
  methods: {
    addNewComment(key) {
      let newComment = {
        from : this.feeds[key].user.name,
        time: new Date(),
        message: this.feeds[key].comment.message,
        src:this.feeds[key].user.src
      };
      this.feeds[key].comments.push(newComment)
    },
    changeShow(idx) {
      this.$set(this.show, idx, !this.show[idx])
    },
    createNewFeed() {
      let newArticle = 
      {
          src: require("@/assets/images/corinlee.jpg"), 
          name:'', 
          time: '', 
          article: '', 
          like: 0,
          comments: []
      }

      newArticle.name = this.user.name
      newArticle.time = "방금"
      newArticle.article = this.newText

      this.feeds.push(newArticle)
      this.newText = ""
    }
  }
}
</script>

<style>

</style>