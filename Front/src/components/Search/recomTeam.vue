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
  }
}
</script>

<style>

</style>