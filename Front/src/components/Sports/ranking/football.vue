<template>
  <div>
    <b-table
      hover
      head-variant="light"
      :items="football"
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
  name:"footballranking",
  data() {
    return {
      sortBy: 'score',
      sortDesc: true,
      fields: [
        {key: 'name', sortable: false},
        {key: 'score', sortable: true, class:"text-center"},
        {key: '득점', sortable: true, class:"text-center"},
        {key: '실점', sortable: true, class:"text-center"}
      ],
      football: [
        {
          name:"광주FC",
          score:25,
          득점:32,
          실점:46
        },
        {
          name:"울산현대",
          score:57,
          득점:54,
          실점:23
        },
        {
          name:"포항스틸러스",
          score:50,
          득점:56,
          실점:35
        },
        {
          name:"대구FC",
          score:27,
          득점:43,
          실점:39
        },
      ],
    }
  }

}
</script>

<style>

</style>