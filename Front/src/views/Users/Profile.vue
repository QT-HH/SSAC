<template>
  <div>
    SSAC
    <div class="media">
      <v-container >
        <v-row no-gutters >
      <img class="d-flex mx-3 rounded-circle" width="80px" height="80px" src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMTVfMTE4/MDAxNDg5NTMzMTAwMjY0.m9UYu7Dt4CyJcaMMeAuIhOFP2nnXBnW5eUqx3rXZY14g.3axKiINI_FaRrOzK70_FY2qRXLulYTBkzwFIaeY8yd4g.JPEG.doghter4our/IMG_5252.jpg?type=w800" alt="Generic placeholder image">
          <v-col >
            <div class="pa-2 text"

            >
              게시글
            </div>
            <div class="pa-2 text"

            >
              123
            </div>
          </v-col>
          <v-col>
            <div class="pa-2 text"

            >
              팔로워
            </div>
            <div class="pa-2 text"

            >
              345
            </div>
          </v-col>
          <v-col>
            <div class="pa-2 text"

            >
              팔로잉
            </div>
            <div class="pa-2 text"

            >
              456
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
export default {
  name:"Profile",
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