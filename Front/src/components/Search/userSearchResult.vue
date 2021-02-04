<template>
  <b-list-group-item class="d-flex align-items-center">
    <b-avatar class="mr-3" src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMTVfMTg0/MDAxNDg5NTMzMTAwNjQ0.V7tqJArWLv350swvN0w8KFfjGEE8LFauuomjhAjJv9Eg.ARhfQCD6kfix4RdJt5YK4EYdvW6HIT2ctqkPlw6RRgsg.JPEG.doghter4our/IMG_5221.jpg?type=w800"></b-avatar>
    <span class="mr-auto">{{ user.name }}</span>
  </b-list-group-item>  
</template>

<script>
export default {
  name: 'userSearchResult',
  props: {
    user: {
      type: Object,
    }
  }
}
</script>

<style>

</style>