<template>
  <div>
      <b-navbar type="dark" variant="dark" class="p-0 nav-content">
        <b-navbar-nav class="nav-content pb-1 text-center" style="width:100%">
          <b-nav-item style="width:33%"><router-link to="/sports/" class="text-decoration-none">일정</router-link></b-nav-item>
          <b-nav-item style="width:33%"><router-link to="/sports/ranking" class="text-decoration-none">순위</router-link></b-nav-item>
          <b-nav-item style="width:33%"><router-link to="/sports/news" class="text-decoration-none">뉴스</router-link></b-nav-item>
          <!-- <b-nav-item style="width:25%" @click="gotoFeed" class="">피드</b-nav-item> -->
        </b-navbar-nav>
      </b-navbar>
  </div>
</template>

<script>
export default {
  name: "SportsNav"
}
</script>

<style>

</style>