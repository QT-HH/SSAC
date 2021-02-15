<template>
  <div class="text-center mt-4">
    {{ surveyAnswers }}
    <div class="mx-5 py-2"><v-btn outlined color="error" x-large class="mt-3" @click="seeResult()"> 결과보기 </v-btn></div>
    <div class="mx-5 py-2"><v-btn outlined color="error" x-large class="mt-3" @click="goNext()"> 다시하기 </v-btn></div>
    <!-- {{ team }} -->
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import axios from 'axios'

export default {
  name: "recomTeam",
  data: function() {
    return {
      team: [],
    }
  },
  methods: {
    ...mapActions ([
      'saveQuestion',
    ]),
    goNext: function() {
      this.$router.push({ name: 'Question1'})
      // console.log('모든 질문에 답변 완료')
      // axios
    },
    seeResult: function() {
      // axios 요청부분 - 요청한번보내서 this.users랑 this.teams를 다 업데이트.

      // 받아오는 정보
      // no(고유번호), event_no(종목번호), name(팀명), logo(로고이미지), count(팔로우수)
      const params = {
        params: {
          userid: this.userid,
          surveyAnswers: this.surveyAnswers,
        }
      }

      axios.get(`http://i4d102.p.ssafy.io:9000/ssac/search/recommend/`, params)
        .then(response => {
          console.log(response.data)
          // this.team = response.data.items
          // this.searched = true // 유저검색결과 한 줄 
        })
        .catch(error => {
          console.error(error)
        })
    }
  },
  computed: {
    surveyAnswers: function () {
      return this.$store.state.surveyAnswers
    },
    userid: function () {
      return this.$store.state.user.email
    }    
    // ...mapState ([
    //   'surveyAnswers',
    //   'user.email',
    // ])
  },

  created() {
    console.log('created')
  }
}
</script>

<style>

</style>