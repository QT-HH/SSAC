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
        :hide-header=true
        :date-info-fn="dateClass"
        ></b-calendar>
      <!-- <button @click="hical1">포항</button>
      <button @click="check">대구</button> -->
    </div>
    <div class="p-2 mt-4">
      <h4>이번주 일정</h4>
      <div>
        <b-tabs content-class="mt-3" fill>
          <b-tab v-for="(day, idx) in thisWeek" :key=idx :title=days[idx] ><p>{{ day }}</p></b-tab>
        </b-tabs>
      </div>
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
      high: [],
      thisWeek: [],
      days: {
        0: "일",
        1: "월",
        2: "화",
        3: "수",
        4: "목",
        5: "금",
        6: "토"
      }
    }
  },
  methods: {
    onContext(ctx) {
      this.context = ctx
    },
    dateSch() {
      console.log(this.value)
      this.dateModal = !this.dateModal
    },dateClass(ymd,date) {
      const day = date.getDate()
      // console.log(ymd,date)
      if (day>=20) {
        return 'table-success'
      } else if (day>=10) {
        return 'table-danger'
      }
      // return day >=10 && day <=20 ? 'table-success': ''
    },
    hical1() {
      let test = new Date("2021-02-06").getDay()
      console.log(test)
      this.high = ["2021-02-06","2021-02-10","2021-02-19"]
    },
    hical2() {
      this.high = ["2021-02-16","2021-02-20","2021-02-25"]
    },
  },
  mounted() {
    let today = new Date()
    let date = today.getDate()
    let day = today.getDay()
    for (let index = 0; index < 7 ; index++) {
      // console.log(new Date(new Date().setDate(date-day+index)))
      this.thisWeek.push(new Date(new Date().setDate(date-day+index)))
    }
  }
}
</script>

<style>
/* b-calendar {
  width : 100%;
} */

</style>