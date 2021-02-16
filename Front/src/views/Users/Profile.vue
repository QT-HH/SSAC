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
        <v-row no-gutters >
      <img class="d-flex ml-8 rounded-circle" width="80px" height="80px" src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMTVfMTE4/MDAxNDg5NTMzMTAwMjY0.m9UYu7Dt4CyJcaMMeAuIhOFP2nnXBnW5eUqx3rXZY14g.3axKiINI_FaRrOzK70_FY2qRXLulYTBkzwFIaeY8yd4g.JPEG.doghter4our/IMG_5252.jpg?type=w800" alt="Generic placeholder image">
          <v-col>
            <div class="d-flex align-center justify-center mx-auto"
            style="text-align:center;
            font-weight: bold; 

            font-size: 1.7em;">
              {{this.nickname}}
            </div>
            <hr style="height:13px; visibility:hidden;" />
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

        <v-col style="font-weight: bold">
            <div  class="pa-2 text" style="text-align:center ">
              게시글
            </div>
            <div 
            style="text-align:center;
            font-weight: bold; 
            font-style: italic;
            font-size: 1.0em;
            color: grey"
            class="pa-2 text">
              123
            </div>
          </v-col>
          <v-col style="font-weight: bold">
            <div class="pa-2 text" style="text-align:center font-weight: bold">
              팔로워
            </div>
            <div 
            style="text-align:center;
            font-weight: bold; 
            font-style: italic;
            font-size: 1.0em;
            color: grey"
            class="pa-2 text">
              345
            </div>
          </v-col>
          <v-col style="font-weight: bold">
            <div class="pa-2 text" style="text-align:center font-weight: bold">
              팔로잉
            </div>

            <div 
            style="text-align:center;
; 
            font-style: italic;
            font-size: 1.0em;
            color: grey"
            class="pa-2 text">
              789
            </div>
          </v-col>
          <v-col style="font-weight: bold">
            <div class="pa-2 text" style="text-align:center font-weight: bold; ">
              포인트
            </div>

            <div 
            style="text-align:center;
            
            font-style: italic;
            font-size: 1.0em;
            color: grey"
            class="pa-2 text">
              74532
            </div>
          </v-col>
        </v-row>
      </v-container>
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
import { mapState } from 'vuex'

export default {
  name:"Profile",
  data() {
    return {
      introduce: this.$store.state.user.introduce,
      nickname: this.$store.state.user.nickname
    }
  },
  computed: {
      ...mapState([
        'user'
      ])
    },
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
  }
}
</script>

<style scoped>
  .v-col {
    display: table-cell;
    vertical-align: middle;
}
</style>