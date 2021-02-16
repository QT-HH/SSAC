<template>
  <div class="text-center">
    <br>
    
    <div class="text-center">
    <v-dialog
      v-model="dialog"
      width="500"
    >
      <template v-slot:activator="{ on, attrs }">
      <v-row>
        <v-col 
          cols=4
          v-for="(team, idx) in myteams"
          :key = "idx"
          :team="team"
        >
          <v-card
            hover
            class="mx-auto"
            style="max-width: 15rem;"
            tag="article"
            border-variant="success"
            v-bind="attrs"
            v-on="on"
            @click="selectTeam(team)"
          >
            <v-img
              :src="team.img"
              class="white--text align-end"
              height="100px"
            >
              <!-- <v-card-title v-if="team.nickname.length === 0 ">{{ team.name }}</v-card-title> -->
              <v-card-title>{{ team.name }}</v-card-title>
            </v-img>

            <v-card-text class="text--primary">
              {{ team.count }} 명이 좋아합니다.
            </v-card-text>

            <v-card-actions>
              <v-btn
                color="error"
                @click="removeMyteam()"
              >
                내 팀 제거
              </v-btn>
            </v-card-actions>
          </v-card>

        </v-col>
      </v-row>
      </template>

      <v-card>
        <v-card-title class="headline blue--text lighten-2">
          {{ this.selectedData.name }}
        </v-card-title>

        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-text-field
                  label="별명을 입력하세요."
                  v-model="nickname"
                  required
                ></v-text-field>
                <span>체크한 이름: {{ nickname }}</span>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            :nickname = 'nickname'

            @click="dialog = false; nicknaming({selectedData,nickname});"
          >
            변경완료
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
    
  </div>
</template>

<script>
// import { mapState } from 'vuex'
import axios from 'axios'

export default {
  name: 'MyMyTeam',
  data () {
      return {
        dialog: false,
        selectedData: Object,
        nickname: '',
        myteams: Object,

      }
    },
  // computed: {
  //  ...mapState({
  //    myteams: 'myTeams'
  //  })
  // },

  computed: {
    userid: function () {
      return this.$store.state.user.email
    }

  },
  methods: {
    selectTeam(team) {
      this.selectedData = team
    },
    nicknaming({selectedData,nickname}) {
      this.$store.dispatch('nicknaming', {selectedData,nickname});
      console.log(selectedData);
      console.log(nickname);


    console.log('created')
    const params2 = {
      params2: {
        userid: this.userid,
        name: this.nickname
      }
    }

    axios.patch(`http://i4d102.p.ssafy.io:9000/ssac/team/myTeamNameChange`, params2)
      .then(response => {
        console.log(response.data)
        // this.articles = response.data
        // this.users = response.data.items
        // this.searched = true // 유저검색결과 한 줄 
      })
      .catch(error => {
        console.error(error)
      })
    },
    removeMyteam () {
    const params3 = {
      params3: {
        userid: this.userid,
        no: this.no
      }
    }

    axios.delete(`http://i4d102.p.ssafy.io:9000/ssac/team/myTeamDelete`, params3)
      .then(response => {
        console.log(response.data)
        // this.articles = response.data
        // this.users = response.data.items
        // this.searched = true // 유저검색결과 한 줄 
      })
      .catch(error => {
        console.error(error)
      })
    },
  },

  created() {
    console.log('created')
    const params = {
      params: {
        userid: this.userid,
      }
    }

    axios.get(`http://i4d102.p.ssafy.io:9000/ssac/team/myTeamList`, params)
      .then(response => {
        console.log(response.data)
        this.myteams = response.data
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

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}
</style>