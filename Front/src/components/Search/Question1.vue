<template>
  <div class="text-center mt-4">
    <h4>1. 어떤 종목인가요?</h4>
    <div class="mx-5 py-2"><b-button pill variant="outline-danger" size="lg" class="mt-3" block @click="[saveQuestion(choices[0]), goNext()]">{{ choices[0] }}</b-button></div>
    <div class="mx-5 py-2"><b-button pill variant="outline-danger" size="lg" class="mt-3" block @click="[saveQuestion(choices[1]), goNext()]">{{ choices[1] }}</b-button></div>
    <div class="mx-5 py-2"><b-button pill variant="outline-danger" size="lg" class="mt-3" block @click="[saveQuestion(choices[2]), goNext()]">{{ choices[2] }}</b-button></div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: "Question1",
  data: function() {
    return {
      choices: ['축구', '야구', 'LOL'],
    }
  },
  methods: {
    ...mapActions ([
      'saveQuestion',
    ]),
    // clickAnswer1: function () {
    //   // console.log('clickAnswer1')
    //   this.answerData = this.choices[0]
    //   this.$store.dispatch('saveQuestion', this.answerData)
    // },
    goNext: function() {
      this.$router.push({ name: 'Question2'})

    }
  }
}
</script>

<style>

</style>