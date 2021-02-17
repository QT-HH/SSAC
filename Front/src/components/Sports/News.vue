<template>
  <div class="mt-7">
    <v-row align="center" justify="space-around">
      <v-card class="ml-6 rounded-lg white--text deep-purple lighten-4" width="30vw" @click="showFootball">
        <v-card-title 
        class="justify-center"
        style="font-size: 16px; font-weight: bold">Football</v-card-title>
      </v-card>
      <v-card  class="mx-1 rounded-lg white--text deep-purple lighten-4"  width="30vw" @click="showBaseball">
        <v-card-title class="justify-center" style="font-size: 16px; font-weight: bold">Baseball</v-card-title>
      </v-card>
      <v-card  class="mr-6 rounded-lg white--text deep-purple lighten-4" width="30vw" @click="showLOL">
        <v-card-title class="justify-center" style="font-size: 15px; font-weight: bold">e-SPORTS</v-card-title>
      </v-card>
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
import football from "./news/football"
import baseball from "./news/baseball"
import lol from "./news/lol"

export default {
  name: "SportsNews",
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