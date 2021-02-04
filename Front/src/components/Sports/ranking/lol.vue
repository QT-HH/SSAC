<template>
  <div>
    <b-table
      hover="true"
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
name:"lolranking",
  data() {
    return {
      sortBy: 'winrate',
      sortDesc: true,
      fields: [
        {key: 'name', sortable: false},
        {key: 'winrate', sortable: true, class:"text-center"},
        {key: '득실차', sortable: true, class:"text-center"},

      ],
      items: [
        {
          name:"담원기아",
          winrate:0.83,
          득실차:6
        },
        {
          name:"한화생명",
          winrate:0.67,
          득실차:2
        },
        {
          name:"T1",
          winrate:0.43,
          득실차:-1
        },
        {
          name:"리브샌드박스",
          winrate:0.17,
          득실차:-6
        },
      ],
    }
  }
}
</script>

<style>

</style>