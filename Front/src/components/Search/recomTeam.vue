<template>
  <div class="text-center mt-4">
    <!-- {{ surveyAnswers }} -->
    <!-- {{ user.email }} -->
    {{ team }}
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
      // this.$router.push({ name: 'recomTeam'})
      console.log('모든 질문에 답변 완료')
      // axios
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
    // console.log(user.email)
      // axios 요청부분 - 요청한번보내서 this.users랑 this.teams를 다 업데이트.
      // console.log(inputData)

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
          console.log(response.data.items)
          this.team = response.data.items
          // this.searched = true // 유저검색결과 한 줄 
        })
        .catch(error => {
          console.error(error)
        })
  }
}
</script>

<style>

</style>