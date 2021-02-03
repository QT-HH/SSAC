<template>
  <div>
      <br>
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

  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: "NewTeamFootball",
  computed: {
    ...mapState([
      'teams'
    ]),
    showbaseball: function () {
      // console.log(this.$store)
      return this.teams.filter (function (team) {
        return team.category === 'baseball'
      })
    },
  },

}
</script>

<style>

</style>