<template>
  <div>
    <!-- 검색창 -->
    <div>
      <v-row 
        align="center"
        justify="space-around"
      >
        <v-col cols=8>
          <v-text-field
            label="검색어를 입력하세요."
            v-model="search"

          ></v-text-field>
        </v-col>
        <v-cols cols=2>
          <v-btn @click="realSearch">
            검색
          </v-btn>
        </v-cols>
      </v-row>

    </div>

    <!-- <li v-for="(user, idx) in users" :key="idx"><img src="user.img" alt="img"> {{user.name}}</li> -->
    

    <v-list subheader>
      <p v-if="searched"><v-subheader>유저 검색 결과</v-subheader></p>

      <userSearchResult 
        v-for="(user,idx) in users"
        :key="idx"
        :user="user"
        />
    </v-list>

    <v-list subheader>
      <p v-if="searched"><v-subheader>팀 검색 결과</v-subheader></p>
      <teamSearchResult 
        v-for="(team,idx) in teams"
        :key="idx"
        :team="team"
        />
    </v-list>

  </div>
</template>

<script>
import userSearchResult from './userSearchResult.vue'
import teamSearchResult from './teamSearchResult.vue'
import axios from 'axios'
export default {
  name: "realSearch",
  components: {
    userSearchResult,
    teamSearchResult,
  },
  data: function() {
    return {
      search: '',
      users: [
        // {img: 'src', name: 'user1'}, {img: 'src', name: 'user2'}, {img: 'src', name: 'user3'}
        ],
      teams: [
        // {name: "T2", img: "https://placekitten.com/300/300" , nickname: "", followers: 14565, category: "축구"},
        // {name: "GEN.G", img: "https://placekitten.com/300/300" , nickname: "", followers: 14565, category: "lol"}, 
        // {name: "한화생명", img: "https://placekitten.com/300/300", nickname: "", followers: 14565, category: "농구"},
        // {img: 'src', name: 'team2', sport: '야구', people: '2'}
        ],
      searched: false,
    }
  },
  methods: {
    realSearch: function() {
      console.log('realSearch')
      this.searched = true
      // axios 요청부분 - 요청한번보내서 this.users랑 this.teams를 다 업데이트.

      const params = {
        params: {
          userid: this.userid,
          search: this.search,
        }
      }

      axios.get(`http://i4d102.p.ssafy.io:9000/ssac/search/search/`, params)
        .then(response => {
          console.log(response.data.items)
          this.users = response.data.items
          this.teams = response.data.items
          // this.searched = true // 유저검색결과 한 줄 
        })
        .catch(error => {
          console.error(error)
        })
    },
  }
}
</script>

<style>

</style>