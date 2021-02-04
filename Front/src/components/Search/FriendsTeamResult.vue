<template>
  <div>
  <b-list-group-item class="d-flex align-items-center">
    <b-avatar class="mr-3" src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMTVfNTQg/MDAxNDg5NTMzMTAxODE0.AeJFquh3hBXmeRfjEMwxExA6jvsw-GhYYU-bdA66huMg.4r8b21NSf1qh4slVD2_U9mRXiUAT2LdjWdrn0UcU7_Ig.JPEG.doghter4our/IMG_5250.jpg?type=w800"></b-avatar>
    <span class="mr-auto">{{ team.name }}</span>
    <span class="mr-auto">{{ team.friend_name }} 님 외에 {{ team.people }}명이 좋아합니다.</span>
  </b-list-group-item>  
  </div>
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