<template>
  <div class="text-center mt-4">
    <h4>4. 어떤게 더 좋아요?</h4>
    <div class="mx-5 py-2"><b-button pill variant="outline-danger" size="lg" class="mt-3" block @click="[saveQuestion(choices[0]), goNext()]">{{ choices[0] }}</b-button></div>
    <div class="mx-5 py-2"><b-button pill variant="outline-danger" size="lg" class="mt-3" block @click="[saveQuestion(choices[1]), goNext()]">{{ choices[1] }}</b-button></div>
    {{ surveyAnswers }}
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: "Question4",
  data: function() {
    return {
      choices: ['한 명의 슈퍼스타가 이끄는 팀', '모두가 헌신하는 팀'],
    }
  },
  methods: {
    ...mapActions ([
      'saveQuestion',
    ]),
    goNext: function() {
      // this.$router.push({ name: 'Question3'})
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