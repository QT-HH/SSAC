<template>
  <v-list-item>
    <v-list-item-avatar>
      <v-img
        :alt="team.name"
        src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMTVfMTg0/MDAxNDg5NTMzMTAwNjQ0.V7tqJArWLv350swvN0w8KFfjGEE8LFauuomjhAjJv9Eg.ARhfQCD6kfix4RdJt5YK4EYdvW6HIT2ctqkPlw6RRgsg.JPEG.doghter4our/IMG_5221.jpg?type=w800"
      ></v-img>
    </v-list-item-avatar>

    <v-list-item-content>
      <span class="mr-auto">{{ team.name }}</span>
      <span class="mr-auto">{{ team.friend_name }} 님 외에 {{ team.people }}명이 좋아합니다.</span>
    </v-list-item-content>

  </v-list-item> 
</template>

<script>
export default {
  name: 'FriendsTeamResult',
  props: {
    team: {
      type: Object,
    }
  }
}
</script>

<style>

</style>