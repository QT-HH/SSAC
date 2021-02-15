<template>
  <div class="text-center mt-4">
    {{ surveyAnswers }}
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: "recomTeam",
  // data: function() {
  //   return {
  //     choices: ['한 명의 슈퍼스타가 이끄는 팀', '모두가 헌신하는 팀'],
  //   }
  // },
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
    ...mapState ([
      'surveyAnswers',
    ])
  },
  created() {
    console.log('created')
      // axios 요청부분 - 요청한번보내서 this.users랑 this.teams를 다 업데이트.
      // console.log(inputData)
      
      // const API_KEY = process.env.VUE_APP_YOUTUBE_API_KEY
      // const API_URL = 'https://www.googleapis.com/youtube/v3/search'

      // const params = {
      //   params: {
      //     key: API_KEY,
      //     part: 'snippet',
      //     q: inputData,
      //     type: 'video'
      //   }
      // }

      // axios.get(API_URL, params)
      //   .then(response => {
      //     console.log(response.data.items)
      //     this.videos = response.data.items
      //     this.searched = true // 유저검색결과 한 줄 
      //   })
      //   .catch(error => {
      //     console.error(error)
      //   })
  }
}
</script>

<style>

</style>