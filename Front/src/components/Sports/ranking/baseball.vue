<template>
  <div>
    <b-table
      hover
      head-variant="light"
      :items="items"
      :fields="fields"
      :sort-by.sync="sortBy"
      :sort-desc.sync="sortDesc"
      responsive="sm"
      sort-icon-left
    ></b-table>

  </div>
</template>

<script>
export default {
name:"baseballranking",
  data() {
    return {
      sortBy: 'winrate',
      sortDesc: true,
      fields: [
        {key: 'name', sortable: false},
        {key: 'winrate', sortable: true, class:"text-center"},
        {key: '승', sortable: true, class:"text-center"},
        {key: '패', sortable: true, class:"text-center"}
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