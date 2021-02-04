<template>
  <div>
    <div class="pt-3 d-flex justify-content-around">
      <b-button variant="success" @click="showFootball">축구</b-button>
      <b-button variant="success" @click="showBaseball">야구</b-button>
      <b-button variant="success" @click="showLOL"> LOL </b-button>
    </div>

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