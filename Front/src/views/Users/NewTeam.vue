<template>
  <div>
    <br>
    <b-container class="bv-example-row bv-example-row-flex-cols ml-auto">
      <b-row align-v="start">
        <b-col class="text-center"><router-link :to="{name : 'NewTeamFootball' }" class="text-decoration-none"><b-button class="m-1" block variant="dark">축구</b-button></router-link></b-col>
        <b-col class="text-center"><router-link :to="{name : 'NewTeamBaseball' }" class="text-decoration-none"><b-button class="m-1" block variant="dark">야구</b-button></router-link></b-col>
        <b-col class="text-center"><router-link :to="{name : 'NewTeamLol' }" class="text-decoration-none"><b-button class="m-1" block variant="dark">LoL</b-button></router-link></b-col>
      </b-row>
    </b-container>
    <router-view />
    <!-- <br>
    <b-collapse id="collapse-1">
      <b-card-group columns deck>
      <b-card
        v-for="(team, idx) in showfootball"
        :key = "idx"
        :src = "team.img"
        border-variant="success"
        tag="article"
        style="max-width: 15rem;"
        class="mb-2"
      >
        <b-container>

            
          <b-avatar 
          :src = "team.img"
          size="6rem"
          class="d-flex"
          >
          </b-avatar>

          <br>
          <b-card-title>
          <div v-if="team.nickname.length === 0">{{ team.name }}</div>
          <div v-else>{{ team.nickname }}</div>
          </b-card-title>

            <b-card-text>
              {{ team.followers }} 명이 좋아합니다.
            </b-card-text>
          <b-button pill variant="primary" size="sm">내 팀 추가</b-button>
        </b-container>
      </b-card>
    </b-card-group>
    </b-collapse>

    <b-collapse id="collapse-2">
      <b-card-group columns deck>
      <b-card
        v-for="(team, idx) in showbaseball"
        :key = "idx"
        :src = "team.img"
        border-variant="success"
        tag="article"
        style="max-width: 15rem;"
        class="mb-2"
      >
        <b-container>

            
          <b-avatar 
          :src = "team.img"
          size="6rem"
          class="d-flex"
          >
          </b-avatar>

          <br>
          <b-card-title>
          <div v-if="team.nickname.length === 0">{{ team.name }}</div>
          <div v-else>{{ team.nickname }}</div>
          </b-card-title>

            <b-card-text>
              {{ team.followers }} 명이 좋아합니다.
            </b-card-text>
          <b-button pill variant="primary" size="sm">내 팀 추가</b-button>
        </b-container>
      </b-card>
    </b-card-group>
    </b-collapse>
    
    <b-collapse id="collapse-3">
      <b-card-group columns deck>
      <b-card
        v-for="(team, idx) in showlol"
        :key = "idx"
        :src = "team.img"
        border-variant="success"
        tag="article"
        style="max-width: 15rem;"
        class="mb-2"
      >
        <b-container>

            
          <b-avatar 
          :src = "team.img" 
          size="6rem"
          class="d-flex"
          >
          </b-avatar>

          <br>
          <b-card-title>
          <div v-if="team.nickname.length === 0">{{ team.name }}</div>
          <div v-else>{{ team.nickname }}</div>
          </b-card-title>

            <b-card-text>
              {{ team.followers }} 명이 좋아합니다.
            </b-card-text>
          <b-button pill variant="primary" size="sm">내 팀 추가</b-button>
        </b-container>
      </b-card>
    </b-card-group>
    </b-collapse> -->
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: "NewTeam",

  computed: {
    ...mapState([
      'teams'
    ]),
    showfootball: function () {
        // console.log(this.$store)
        return this.teams.filter (function (team) {
          return team.category === 'football'
        })
    },
    showbaseball: function () {
      // console.log(this.$store)
      return this.teams.filter (function (team) {
        return team.category === 'baseball'
      })
    },
    showlol: function () {
      // console.log(this.$store)
      return this.teams.filter (function (team) {
        return team.category === 'lol'
      })
    },

  }
}
</script>

<style>

</style>