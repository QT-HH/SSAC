<template>
  <div>
  <v-data-table
    :headers="headers"
    :items="football"
    class="elevation-1"
    mobile-breakpoint="0"
  ></v-data-table>

  </div>
</template>

<script>
export default {
  name:"footballranking",
  data() {
    return {
      headers:[
        {
          text: '팀',
          align:'end',
          sortable: false,
          value:'name'
        },
        { text: '승점', value: 'score'},
        { text: '득점', value: '득점'},
        { text: '실점', value: '실점'},
        { text: '점', value: '점'},
        { text: '점수', value: '점수'},
      ],
      football: [
        {
          name:"광주FC",
          score:25,
          득점:32,
          실점:46,
          점:46,
          점수:46,
        },
        {
          name:"울산현대",
          score:57,
          득점:54,
          실점:23,
          점:23,
          점수:23,
        },
        {
          name:"포항스틸러스",
          score:50,
          득점:56,
          실점:35,
          점:35,
          점수:35,
        },
        {
          name:"대구FC",
          score:27,
          득점:43,
          실점:39,
          점:39,
          점수:39
        },
      ],
    }
  }

}
</script>

<style>

</style>