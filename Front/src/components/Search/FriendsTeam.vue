<template>
  <div>
    <b-list-group style="max-width: 500px;">
      <FriendsTeamResult v-for="(team, idx) in fri_teams" 
      :key="idx"
      :team="team"
      />
    </b-list-group>   
  </div>
</template>

<script>
import FriendsTeamResult from './FriendsTeamResult.vue'
export default {
  name: "FriendsTeam", 
  components: {
    FriendsTeamResult,
  },
  data: function() {
    return {
      fri_teams: [{img: 'src', name: 'team1', friend_name: '은지', people: '1'}, 
      {img: 'src', name: 'team2', friend_name: '경연', people: '2'}, 
      {img: 'src', name: 'team2', friend_name: '규태', people: '3'}, 
      {img: 'src', name: 'team3', friend_name: '노정', people: '4'},
      {img: 'src', name: 'team3', friend_name: '지원', people: '5'},
      ]
    }
  }
}
</script>

<style>

</style>