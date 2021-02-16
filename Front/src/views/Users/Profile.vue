<template>
  <div style="width:100%;">
    <!-- Systembar & Navbar -->
    <v-system-bar color="deep-purple darken-3"></v-system-bar>
    <v-app-bar
      color="deep-purple accent-4"
      dark
      height="54px"
    >
      <v-row>
        <v-col cols="3">
          <v-btn icon class="mr-5" @click="gotoProfileEdit">
            <v-icon>mdi-account-plus</v-icon>
          </v-btn>
        </v-col>
        <v-col class="mt-1" cols="6">
          <h2 align="center" justify="center">Profile</h2>
        </v-col>
        <v-col cols="3">         
          <v-btn icon class="ml-5" @click="gotoProfileEdit">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </v-col>
      </v-row>
      <!-- <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-filter</v-icon>
      </v-btn> -->
    </v-app-bar>



    <div 
    style="border: 0px"
    class="media">
      <v-container  >
        <br>
      <img class="d-flex align-center justify-center mx-auto rounded-circle" width="80px" height="80px" src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMTVfMTE4/MDAxNDg5NTMzMTAwMjY0.m9UYu7Dt4CyJcaMMeAuIhOFP2nnXBnW5eUqx3rXZY14g.3axKiINI_FaRrOzK70_FY2qRXLulYTBkzwFIaeY8yd4g.JPEG.doghter4our/IMG_5252.jpg?type=w800" alt="Generic placeholder image">
        <v-row no-gutters >
          <v-col>
            <div class="d-flex align-center justify-center mx-auto"
            style="text-align:center;
            font-weight: bold; 
            font-size: 1.7em;">
              {{this.nickname}}
            </div>
            <div class="d-flex align-center justify-center mx-auto"
            style="text-align:center;
            font-style: italic;
            font-size: 1.0em
            color: grey;">
              {{this.introduce}}
            </div>

          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row>
        <v-col class="pt-0 pb-0" style="font-weight: bold">
            <div  class="pb-0 text" style="text-align:center ">
              게시글
            </div>
            <div 
            style="text-align:center;
            font-weight: bold; 
            font-style: italic;
            font-size: 1.0em;
            color: grey"
            class="pt-0 text">
              {{ articles.length }}
            </div>
          </v-col>
          <v-col class="pt-0 pb-0" style="font-weight: bold">
            <div class="pb-0 text" style="text-align:center">
              팔로워
            </div>
            <div 
            style="text-align:center;
            font-weight: bold; 
            font-style: italic;
            font-size: 1.0em;
            color: grey"
            class="pt-0 text">
              {{follower.length}}
            </div>
          </v-col>
          <v-col class="pt-0 pb-0" style="font-weight: bold">
            <div class="pb-0 text" style="text-align:center">
              팔로잉
            </div>

            <div 
            style="text-align:center;
; 
            font-style: italic;
            font-size: 1.0em;
            color: grey"
            class="pt-0 text">
              {{following.length}}
            </div>
          </v-col>
          <v-col class="pt-0 pb-0" style="font-weight: bold">
            <div class="pb-0 text" style="text-align:center">
              포인트
            </div>

            <div 
            style="text-align:center;
            
            font-style: italic;
            font-size: 1.0em;
            color: grey"
            class="pt-0 text">
              {{ point }}
            </div>
          </v-col>
        </v-row>
      </v-container>

          <!-- <v-btn
          v-if="useremail === this.$store.state.user.email"
            :elevation="0"
            style="width: 300px"
            class="rounded-lg"
            color="grey lighten-2"
            @click="gotoProfileEdit"
          >
            프로필 편집
          </v-btn>
          <v-btn 
            v-else
            :elevation="0"
            style="width: 300px"
            
            color="primary"
            dark
          >
            팔로우
          </v-btn> -->
    </div>
    
    <div>
      <v-tabs
        
        background-color="white"
        fixed-tabs
        >
        <v-tab @click="gotoArticle">게시글</v-tab>
        <v-tab @click="gotoMyTeam">마이팀</v-tab>
      </v-tabs>
    </div>
    <router-view />
    
  </div>
</template>

<script>
// import { mapState } from 'vuex'
import axios from 'axios'

export default {
  name:"Profile",
  // data() {
  //   return {

  //   }
  // },
  data: function() {
    return {
      following: [],
      nickname: '',
      useremail: '',
      point: '',
      introduce: 'Hi there!',
      follower: [],
      articles: Object
      // introduce: this.$store.state.user.introduce,
      // nickname: this.$store.state.user.nickname
    }
  },
  // computed: {
  //     ...mapState([
  //       'user'
  //     ])
  //   },
  methods:{
    gotoArticle() {
      if (this.$route.path !== "/profile") {
        this.$router.push({name:"Article"})
      }
    },
    gotoMyTeam() {
      if (this.$route.path !== "/profile/myteam") {
        this.$router.push({name:"MyMyTeam"})
      }
    },
    gotoProfileEdit() {
      if (this.$route.path !== "/profile/edit") {
        this.$router.push({name:"ProfileEdit"})
      }
    },
    
  },


  computed: {
    // ...mapState({
    //  articles: 'articles',
    //  user: 'user'
  //  })
    userid: function () {
      return this.$store.state.user.email
    }

  },
  created() {
    console.log('created')

    const params = {
      params: {
        userid: this.userid,
      }
    }

    axios.get(`http://i4d102.p.ssafy.io:9000/ssac/user/userSelect/`, params)
      .then(response => {
        console.log(response.data)
        this.nickname = response.data.usernickname
        this.useremail = response.data.userid
        this.point = response.data.point
        this.follower = response.data.follower
        this.following = response.data.following
        // this.users = response.data.items
        // this.searched = true // 유저검색결과 한 줄 
      })
      .catch(error => {
        console.error(error)
      })

    const params2 = {
      params2: {
        userid: this.userid,
      }
    }

    axios.get(`http://i4d102.p.ssafy.io:9000/ssac/newsfeed/newsFeedList/`, params2)
      .then(response => {
        console.log(response.data)
        this.articles = response.data
        // this.users = response.data.items
        // this.searched = true // 유저검색결과 한 줄 
      })
      .catch(error => {
        console.error(error)
      })

  }

}
</script>

<style scoped>
  .v-col {
    display: table-cell;
    vertical-align: middle;
}
</style>