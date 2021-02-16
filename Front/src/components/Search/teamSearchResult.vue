<template>
<v-list-item>
  <!-- <b-list-group-item class="d-flex align-items-center">
    <b-avatar class="mr-3" src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMTVfMTY4/MDAxNDg5NTMzMTAxNTg3.3DcigjBTsc0_wKhaqCdjcRlFP4kK5J_GdUJVBZFliyIg.EVfpvEjWAGBzkOWVYiQxLlwn54CV1BJpoN9YMM9uuIkg.JPEG.doghter4our/IMG_5255.jpg?type=w800"></b-avatar>
    <span class="mr-auto">{{ team.name }}</span>
    <span class="mr-auto">{{ team.category }}  {{ team.followers }}명</span>
    <b-icon :icon="iconType" @click="[saveMyTeam(team), changeIconType()]"></b-icon>
  </b-list-group-item> -->
  

    <v-list-item-avatar>
      <v-img
        :alt="team.name"
        src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMTVfMTY4/MDAxNDg5NTMzMTAxNTg3.3DcigjBTsc0_wKhaqCdjcRlFP4kK5J_GdUJVBZFliyIg.EVfpvEjWAGBzkOWVYiQxLlwn54CV1BJpoN9YMM9uuIkg.JPEG.doghter4our/IMG_5255.jpg?type=w800"
      ></v-img>
    </v-list-item-avatar>

    <v-list-item-content>
      <v-list-item-title v-text="team.name"></v-list-item-title>
    </v-list-item-content>

    <v-icon @click="[saveMyTeam(team), changeIconType(), dbRequest()]">{{ iconType }}</v-icon>


</v-list-item>

</template>

<script>
import { mapActions, mapState } from 'vuex'
import axios from 'axios'

export default {
  name: 'teamSearchResult',
  props: {
    team: {
      type: Object,
    }
  },
  data: function() {
    return {
      iconType: 'mdi-plus-circle-outline'
    }
  },
  methods: {
    ...mapActions ([
      'saveMyTeam',
    ]),
    changeIconType: function() {
      this.iconType = 'blank'
    },

    dbRequest: function() {

      console.log('created')
      const params = {
          params: {
            userid: this.userid,
            team_no: this.team_no
          }
        }

      axios.post(`http://i4d102.p.ssafy.io:9000/ssac/team/myTeamInsert/`, params)
        .then(response => {
          console.log(response.data)
          // axios 요청부분 - 요청한번보내서 this.users랑 this.teams를 다 업데이트.
          // this.fri_teams = response.data
          // this.searched = response.data.searched // 유저검색결과 한 줄 
        })
        .catch(error => {
          console.error(error)
        })
      

    }
  },
  computed: {
    ...mapState ([
      'myTeams',
    ])
  },
  created() {
    // console.log('created')
    if (this.team in this.myTeams) {
        this.iconType = ''
    }
  }
}
</script>

<style>

</style>