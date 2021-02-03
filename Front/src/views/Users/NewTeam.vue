<template>
  <div>
    <br>
    
    <b-container class="bv-example-row bv-example-row-flex-cols ml-auto">
      <b-input-group prepend="검색" class="my-3 ml-3 mr-3">
        <b-form-input></b-form-input>
        <b-input-group-append>
          <router-link :to="{name : 'NewTeamSearchResult', params: {name: this.name }}" class="text-decoration-none"><b-icon-search font-scale="2" class="ml-2 mr-4" @click="realSearch"></b-icon-search></router-link>
        </b-input-group-append>
      </b-input-group>
      <b-row align-v="start">
        <b-col class="text-center"><router-link :to="{name : 'NewTeamFootball' }" class="text-decoration-none"><b-button class="m-1" block variant="dark">축구</b-button></router-link></b-col>
        <b-col class="text-center"><router-link :to="{name : 'NewTeamBaseball' }" class="text-decoration-none"><b-button class="m-1" block variant="dark">야구</b-button></router-link></b-col>
        <b-col class="text-center"><router-link :to="{name : 'NewTeamLol' }" class="text-decoration-none"><b-button class="m-1" block variant="dark">LoL</b-button></router-link></b-col>
      </b-row>
    </b-container>
    <router-view />
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: "NewTeam",
  data: function () {
    return {
      name: '',
    }
  },
  computed: {
    ...mapState([
      'teams'
    ]),
  },
  methods: {
    realSearch: function() {
      console.log('realSearch')
  }
  }
}
</script>

<style>

</style>