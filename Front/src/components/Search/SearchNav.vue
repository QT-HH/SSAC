<template>
  <div>
    <v-system-bar
    style="background-color: #1A237E"
      height="25"
      fixed
      dark
    >
    <v-spacer></v-spacer>
      <v-icon>mdi-wifi-strength-4</v-icon>
      <v-icon>mdi-signal-cellular-outline</v-icon>
      <v-icon>mdi-battery</v-icon>
      <span>12:30</span>
    </v-system-bar>
    <hr style="height:25px; visibility:hidden;" />
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