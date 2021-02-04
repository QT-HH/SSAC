<template>
  <div>
    <b-navbar type="dark" variant="dark" class="p-0 nav-content">
      <b-navbar-nav class="nav-content pb-1 text-center" style="width:100%">
        <b-nav-item style="width:50%"><router-link to="/profile/myteam/mymyteam" class="text-decoration-none">내 팀</router-link></b-nav-item>
        <b-nav-item style="width:50%"><router-link to="/profile/myteam/newteam" class="text-decoration-none">새로운 팀</router-link></b-nav-item>
      </b-navbar-nav>
    </b-navbar>
    <router-view />
  </div>
</template>

<script>
export default {
  name:"MyTeam"
}
</script>

<style>

</style>