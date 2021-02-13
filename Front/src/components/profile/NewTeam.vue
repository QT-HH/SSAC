<template>
  <div>
    <br>
  
    <div>
      <v-text-field
        label="검색어를 입력하세요."
        v-model="query"
      ></v-text-field>

      <v-row
        align="center"
        justify="space-around"
      > 
        <v-btn
          @click="turnAll"
        >
          전체
        </v-btn>

        <v-btn
          color="primary"
          @click="turnFootball"
        >
          축구
        </v-btn>

        <v-btn
          color="error"
          @click="turnBaseball"
        >
          야구
        </v-btn>

        <v-btn
          color="green darken-1 white--text"
          @click="turnLOL"
        >
          LOL
        </v-btn>

      </v-row>

    </div>
    <br>
    <v-container>
      <v-row>
        <v-col 
          cols=4
          v-for="(team, idx) in filterTeam(cat,query)"
          :key="idx"
          :team="team"
        >
          <v-card
            hover
            class="mx-auto"
            style="max-width: 15rem;"
            tag="article"
            border-variant="success"
          >
            <v-img
              :src="team.logo"
              class="white--text align-end"
              height="100px"
            >
              <v-card-title>{{ team.name }}</v-card-title>
            </v-img>

            <v-card-text class="text--primary">
              {{ team.count }} 명이 좋아합니다.
            </v-card-text>

            <v-card-actions>
              <v-btn
                color="blue white--text"
              >
                내 팀 추가
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>

</template>

<script>
import { mapState } from 'vuex'
import { listTeams } from "@/api/profile/newteam.js"

export default {
  name: "NewTeam",
  data: function () {
    return {
      name: '',
      cat: 0,
      query: "",
    }
  },
  computed: {
    ...mapState([
      'teams'
    ]),
  },
  created() {
    listTeams(
      (response) => {
        console.log(response);
        this.teams = response.data
      },
      error => {
        console.log(error);
      });
  },
  methods: {
    realSearch: function() {
      console.log('realSearch')
    },
    turnAll: function() {
            this.cat = 0
        },
    turnFootball: function() {
        this.cat = 1
    },
    turnBaseball: function() {
        this.cat = 2
    },
    turnLOL: function() {
        this.cat = 3
    },
    filterTeam: function(cat,que) {
    return this.teams.filter(function(it){
        return (it.event_no*cat === 0 || it.event_no === cat) && it.name.includes(que)
    })
    },
  }
}
</script>

<style>

</style>