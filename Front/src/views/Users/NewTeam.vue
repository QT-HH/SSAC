<template>
  <div>
    <br>
    <b-container class="bv-example-row bv-example-row-flex-cols ml-auto">
      <b-row align-v="start">
        <b-col @click="showfootball" class="text-center"><b-button block variant="dark">축구</b-button></b-col>
        <b-col class="text-center"><b-button block variant="dark">야구</b-button></b-col>
        <b-col class="text-center"><b-button block variant="dark">LoL</b-button></b-col>
      </b-row>
    </b-container>
    <br>

    <b-card-group columns>
      <b-card
        v-for="(team, idx) in teams"
        :key = "idx"
        border-variant="success"
        tag="article"
        style="max-width: 15rem;"
        class="mb-2"
        v-b-modal.modal-prevent-closing
      >
        <b-container>

            
          <b-avatar 
          src="https://placekitten.com/300/300" 
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
  name: "NewTeam",
  computed: {
   ...mapState([
    'teams'
   ])
  },
  methods: {
    showfootball: function () {
      // console.log(this.$store)
      this.$store.dispatch(this.showfootball)
    }
  }
}
</script>

<style>

</style>