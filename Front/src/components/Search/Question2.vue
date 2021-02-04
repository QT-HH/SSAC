<template>
  <div class="text-center mt-4">
    <h4>2. 어떤 경기가 더 좋아요?</h4>
    <div class="mx-5 py-2"><b-button pill variant="outline-danger" size="lg" class="mt-3" block @click="[saveQuestion(choices[0]), goNext()]">{{ choices[0] }}</b-button></div>
    <div class="mx-5 py-2"><b-button pill variant="outline-danger" size="lg" class="mt-3" block @click="[saveQuestion(choices[1]), goNext()]">{{ choices[1] }}</b-button></div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: "Question2",
  data: function() {
    return {
      choices: ['화끈한 경기력이었지만 졌잘싸', '꾸역꾸역이지만 이겼으니 만족!'],
    }
  },
  methods: {
    ...mapActions ([
      'saveQuestion',
    ]),
    goNext: function() {
      this.$router.push({ name: 'Question3'})
    }
  }
}
</script>

<style>

</style>