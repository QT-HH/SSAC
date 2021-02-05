<template>
  <div>
    <Navbar />
    <b-navbar type="dark" variant="dark" class="p-0 nav-content">
      <b-navbar-nav class="nav-content pb-1 text-center" style="width:100%">
        <b-nav-item style="width:50%"><router-link to="#" class="text-decoration-none">채팅개설</router-link></b-nav-item>
        <b-nav-item style="width:50%"><router-link :to="{name: 'chatRoom'}" class="text-decoration-none">채팅하기</router-link></b-nav-item>
      </b-navbar-nav>
    </b-navbar>
    <router-view />
  </div>
</template>

<script>
import Navbar from '@/components/common/Navbar.vue'

export default {
  components: {
    Navbar
  }
}
</script>

<style>

</style>