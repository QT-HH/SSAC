<template>
<div style="width:100%;">
    <v-card>
      <v-tabs
        fixed-tabs
        background-color="#536DFE"
        dark
        >
        <v-tab @click="gotoSchedule">일정</v-tab>
        <v-tab @click="gotoRanking">순위</v-tab>
        <v-tab @click="gotoNews">뉴스</v-tab>
        <v-tab @click="gotoChat">채팅</v-tab>
      </v-tabs>
    </v-card>

 <!-- #82B1FF -->

</div>
</template>

<script>
export default {
  name:"SportsNav",
  methods: {
    gotoSchedule() {
      if (this.$route.path !== "/tabs") {
        this.$router.push({name:"SportsSchedule"})
      }
    },
    gotoRanking() {
      if (this.$route.path !== "/tabs/ranking") {
        this.$router.push({name:"SportsRanking"})
      }
    },
    gotoNews() {
      if (this.$route.path !== "/tabs/news") {
        this.$router.replace({name:"SportsNews"})
      }
    },
    gotoChat() {
      if (this.$route.path !== "/tabs/chat") {
        this.$router.replace({name:"SportsChat"})
      }
    },
  }
}
</script>

<style>

</style>