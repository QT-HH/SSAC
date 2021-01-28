<template>
  <div>
      <b-navbar type="dark" variant="dark" class="p-0 nav-content">
        <b-navbar-nav class="nav-content pb-1 text-center" style="width:100%">
          <b-nav-item style="width:33%"><router-link to="/search/" class="text-decoration-none">검색</router-link></b-nav-item>
          <b-nav-item style="width:33%"><router-link to="/search/friendsteam" class="text-decoration-none">친구의팀</router-link></b-nav-item>
          <b-nav-item style="width:33%"><router-link to="/search/recommend" class="text-decoration-none">팀추천</router-link></b-nav-item>
          <!-- <b-nav-item style="width:25%" @click="gotoFeed" class="">피드</b-nav-item> -->
        </b-navbar-nav>
      </b-navbar>
  </div>
</template>

<script>
export default {
  name: "SearchNav"
}
</script>

<style>

</style>