<template>
  <div>
    <v-row align="center">
      <br>
      <br>
      <br>
      <v-col>
        <div 
        v-for="(article, idx) in articles" 
        :key="idx" 
        :article="article"
        >
        <hr style="height:14px; visibility:hidden;" />
        <v-card 
        :elevation="2"
        class="mx-3 rounded-lg " 
        max-width="400" 
        outlined
        type="white">
          <v-card-actions>
            <v-list-item class="grow">
              <v-list-item-avatar color="grey darken-3">
                <v-img class="elevation-6" alt="" :src="user.img"></v-img>
              </v-list-item-avatar>

              <v-list-item-content>
                <v-list-item-title>{{nickname}}</v-list-item-title>
              </v-list-item-content>

              <v-row align="center" justify="end">
                <v-icon class="mr-1">
                  mdi-heart
                </v-icon>
                <span class="subheading mr-2">256</span>
                <span class="mr-1">·</span>
                <v-icon class="mr-1">
                  mdi-email
                </v-icon>
                <span class="subheading">45</span>
              </v-row>
            </v-list-item>
          </v-card-actions>
            <!-- <hr> -->
          <v-card-text class="headline font-weight-bold">
            {{article.content}}
          </v-card-text>

        </v-card>
        </div>
        <br>
        <br>
        <br>
      </v-col>
    </v-row>
  </div>
</template>

<script>
// import { mapState } from 'vuex'
import axios from 'axios'

export default {
  name:"Article",
  data: function() {
    return {
      articles: [],
      user: '',
      following: [],
      nickname: '',
      useremail: '',
      point: '',
      follower: [],
    }
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

    axios.get(`http://i4d102.p.ssafy.io:9000/ssac/newsfeed/newsFeedList/`, params)
      .then(response => {
        console.log(response.data)
        this.articles = response.data
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

    axios.get(`http://i4d102.p.ssafy.io:9000/ssac/user/userSelect/`, params2)
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
  }

}
</script>

<style>

</style>