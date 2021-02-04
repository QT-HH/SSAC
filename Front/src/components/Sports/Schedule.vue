<template>
  <div>
    <div class="pt-3">
      <b-calendar 
        block
        v-model="value" 
        @context="onContext" 
        locale="ko-KR" 
        class="mx-3 border rounded"
        selected-variant="success"
        today-variant="success"
        nav-button-variant="success"
        label-help=""
        ></b-calendar>

    </div>
    <div class="p-3 mt-4">
        <v-calendar />
      <h4>이번주 일정</h4>

    </div>
    <b-modal v-model="dateModal" hide-footer :title="value">
      <p class="my-4">오늘의 경기들</p>
    </b-modal>
  </div>
</template>

<script>


export default {
  name: "SportsSchedule",
  watch:{
    value: function() {
      this.dateSch()
    }
  },
  data() {
    return {
      value: '', 
      context: null,
      dateModal: false,
    }
  },
  methods: {
    onContext(ctx) {
      this.context = ctx
    },
    dateSch() {
      this.dateModal = !this.dateModal
    }
  }
}
</script>

<style>
/* b-calendar {
  width : 100%;
} */

</style>