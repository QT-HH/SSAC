<template>
  <div>
      <v-tabs
        background-color="#536DFE"
        dark
        fixed-tabs
        >
        <v-tab @click="gotorealSearch">검색</v-tab>
        <v-tab @click="gotoFriendsTeam">친구의 팀</v-tab>
        <v-tab @click="gotoRecommend">팀 추천</v-tab>
      </v-tabs>
  </div>
</template>

<script>
export default {
  name: "SearchNav",
  methods: {
    gotorealSearch() {
      if (this.$route.path !== "/tabs/search") {
        this.$router.push({name:"realSearch"})
      }
    },
    gotoFriendsTeam() {
      if (this.$route.path !== "/tabs/search/friendsteam") {
        this.$router.push({name:"FriendsTeam"})
      }
    },
    gotoRecommend() {
      if (this.$route.path !== "/tabs/search/recommend") {
        this.$router.replace({name:"Question1"})
      }
    },
  },
}
</script>

<style>

</style>