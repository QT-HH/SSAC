<template>
  <div>
    <v-data-table 
      :headers="headers"
      :items="items"
      class="elevation-1"
    ></v-data-table>

  </div>
</template>

<script>
export default {
name:"baseballranking",
  data() {
    return {
      headers:[
        {
          text: '팀',
          align:'end',
          sortable: false,
          value:'name'
        },
        { text: '승률', value: 'winrate'},
        { text: '승', value: '승'},
        { text: '패', value: '패'},
      ],
      items: [
        {
          name:"롯데자이언츠",
          winrate:0.497,
          승:71,
          패:72
        },
        {
          name:"SK와이번스",
          winrate:0.357,
          승:51,
          패:92
        },
        {
          name:"NC다이노스",
          winrate:0.601,
          승:83,
          패:55
        },
        {
          name:"KT위즈",
          winrate:0.566,
          승:81,
          패:62
        },
      ],
    }
  }

}
</script>

<style>

</style>