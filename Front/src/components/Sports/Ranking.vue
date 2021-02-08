<template>
  <div>
    <br>
    <v-row align="center" justify="space-around">
      <v-btn color="blue" @click="showFootball">축구</v-btn>
      <v-btn color="blue" @click="showBaseball">야구</v-btn>
      <v-btn color="blue" @click="showLOL"> LOL </v-btn>
    </v-row>

    <br>
    <div class="m-3">
      <football v-if="now === 'Football'"/>
      <baseball v-else-if="now === 'Baseball'"/>
      <lol v-else-if="now === 'LOL'"/>

    </div>

  </div>
</template>

<script>
import football from "./ranking/football"
import baseball from "./ranking/baseball"
import lol from "./ranking/lol"

export default {
  name: "SportsRanking",
  components:{
    football,
    baseball,
    lol
  },
  watch:{
    now: function () {

    }
  },
  data() {
    return {
      now:"Football",
    }
  },
  methods:{
    showFootball() {
      this.now = "Football"
    },
    showBaseball() {
      this.now = "Baseball"
    },
    showLOL() {
      this.now = "LOL"
    },
  }
}
</script>

<style>

</style>