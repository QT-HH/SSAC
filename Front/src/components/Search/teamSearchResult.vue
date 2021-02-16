<template>
<v-list-item>
  <!-- <b-list-group-item class="d-flex align-items-center">
    <b-avatar class="mr-3" src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMTVfMTY4/MDAxNDg5NTMzMTAxNTg3.3DcigjBTsc0_wKhaqCdjcRlFP4kK5J_GdUJVBZFliyIg.EVfpvEjWAGBzkOWVYiQxLlwn54CV1BJpoN9YMM9uuIkg.JPEG.doghter4our/IMG_5255.jpg?type=w800"></b-avatar>
    <span class="mr-auto">{{ team.name }}</span>
    <span class="mr-auto">{{ team.category }}  {{ team.followers }}명</span>
    <b-icon :icon="iconType" @click="[saveMyTeam(team), changeIconType()]"></b-icon>
  </b-list-group-item> -->
  
    <v-list-item-avatar>
      <v-img
        :alt="team.name"
        src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMTVfMTY4/MDAxNDg5NTMzMTAxNTg3.3DcigjBTsc0_wKhaqCdjcRlFP4kK5J_GdUJVBZFliyIg.EVfpvEjWAGBzkOWVYiQxLlwn54CV1BJpoN9YMM9uuIkg.JPEG.doghter4our/IMG_5255.jpg?type=w800"
      ></v-img>
    </v-list-item-avatar>

    <v-list-item-content>
      <v-list-item-title v-text="team.name"></v-list-item-title>
    </v-list-item-content>

    <v-icon @click="[saveMyTeam(team), changeIconType()]">{{ iconType }}</v-icon>


</v-list-item>

</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: 'teamSearchResult',
  props: {
    team: {
      type: Object,
    }
  },
  data: function() {
    return {
      iconType: 'mdi-plus-circle-outline'
    }
  },
  methods: {
    ...mapActions ([
      'saveMyTeam',
    ]),
    changeIconType: function() {
      this.iconType = 'blank'
    }
  },
  computed: {
    ...mapState ([
      'myTeams',
    ])
  },
  created() {
    // console.log('created')
    if (this.team in this.myTeams) {
        this.iconType = ''
    }
  }
}
</script>

<style>

</style>