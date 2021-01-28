<template>
  <div>
    <Navbar />
    <SportsNav />
    <router-view />
  </div>
</template>

<script>
import Navbar from '@/components/common/Navbar.vue'
import SportsNav from '@/components/Sports/SportsNav.vue'

export default {
  name: "Sports",
  components: {
    Navbar,
    SportsNav
  }
};
</script>

<style>

</style>