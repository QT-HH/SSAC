<template>
  <div>
    SSAC  
    <b-icon-gear></b-icon-gear>
    <div class="media">
      <img class="d-flex mx-3 rounded-circle" width="80px" height="80px" src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMTVfMTE4/MDAxNDg5NTMzMTAwMjY0.m9UYu7Dt4CyJcaMMeAuIhOFP2nnXBnW5eUqx3rXZY14g.3axKiINI_FaRrOzK70_FY2qRXLulYTBkzwFIaeY8yd4g.JPEG.doghter4our/IMG_5252.jpg?type=w800" alt="Generic placeholder image">
      <div class="media-body">
        <p>첫번째 줄</p>
        <p>두번째 줄</p>
        <p>세번째 줄</p>
      </div>
    </div>    

    <hr>
      <b-navbar type="lg" variant="dark" class="p-0 nav-content">
        <b-navbar-nav class="nav-content pb-1 text-center" style="width:100%">
          <b-nav-item style="width:50%"><router-link to="/profile/" class="text-decoration-none">게시글</router-link></b-nav-item>
          <b-nav-item style="width:50%"><router-link to="/profile/myteam" class="text-decoration-none">마이팀</router-link></b-nav-item>
          <!-- <b-nav-item style="width:33%"><router-link to="/profile/alarm" class="text-decoration-none">알림</router-link></b-nav-item> -->
          <!-- <b-nav-item style="width:25%" @click="gotoFeed" class="">피드</b-nav-item> -->
        </b-navbar-nav>
      </b-navbar>
      <router-view />
  </div>
</template>

<script>
export default {
  name:"Profile"
}
</script>

<style>

</style>