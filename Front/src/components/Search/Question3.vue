<template>
  <div class="text-center mt-4">
    <h4>3. 어떤 팀을 더 선호하나요?</h4>
    <div class="mx-5 py-2"><b-button pill variant="outline-danger" size="lg" class="mt-3" block @click="[saveQuestion(choices[0]), goNext()]">{{ choices[0] }}</b-button></div>
    <div class="mx-5 py-2"><b-button pill variant="outline-danger" size="lg" class="mt-3" block @click="[saveQuestion(choices[1]), goNext()]">{{ choices[1] }}</b-button></div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: "Question3",
  data: function() {
    return {
      choices: ['자주 이기는 상위권팀', '예측할 수 없는 중위권팀', '반전을 꿈꾸는 하위권 팀'],
    }
  },
  methods: {
    ...mapActions ([
      'saveQuestion',
    ]),
    goNext: function() {
      this.$router.push({ name: 'Question4'})
    }
  }
}
</script>

<style>

</style>