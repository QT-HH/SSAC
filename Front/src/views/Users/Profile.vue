<template>
  <div>
    SSAC
    <div class="media">
      <v-container >
        <v-row no-gutters >
      <img class="d-flex mx-3 rounded-circle" width="80px" height="80px" src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMTVfMTE4/MDAxNDg5NTMzMTAwMjY0.m9UYu7Dt4CyJcaMMeAuIhOFP2nnXBnW5eUqx3rXZY14g.3axKiINI_FaRrOzK70_FY2qRXLulYTBkzwFIaeY8yd4g.JPEG.doghter4our/IMG_5252.jpg?type=w800" alt="Generic placeholder image">
          <v-col>
            <div class="d-flex align-center justify-center mx-auto">
              내 별명: {{nickname}}
            </div>
            <br>
            <div class="d-flex align-center justify-center mx-auto">
              아이디: {{useremail}}
            </div>

          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row>

        <v-col >
            <div  class="pa-2 text">
              게시글
            </div>
            <div class="pa-2 text">
              {{ articles.length }} 개
            </div>
          </v-col>
          <v-col>
            <div class="pa-2 text">
              팔로워
            </div>
            <div class="pa-2 text">
              {{follower.length}} 명
            </div>
          </v-col>
          <v-col>
            <div class="pa-2 text">
              팔로잉
            </div>
            <div class="pa-2 text">
              {{following.length}} 명
            </div>
          </v-col>
          <v-col>
            <div class="pa-2 text">
              포인트
            </div>
            <div class="pa-2 text">
              {{ point }} p
            </div>
          </v-col>
        </v-row>
      </v-container>
      
      <v-container>
        <div class="text-center">
          <v-btn
            style="width: 300px"
            rounded
            color="primary"
            dark
          >
            팔로우
          </v-btn>
        </div>
        <div class="text-center">
          <v-btn
            style="width: 300px"
            rounded
            color="grey lighten-2"
            @click="gotoProfileEdit"
          >
            프로필 편집
          </v-btn>
        </div>
      </v-container>
    </div>
    
    <hr>
    <div>
      <v-tabs
        background-color="#536DFE"
        dark
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