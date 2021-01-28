<template>
  <div>
    <Navbar />
    <SearchNav />
    <router-view />
  </div>
</template>

<script>
import Navbar from '@/components/common/Navbar.vue'
import SearchNav from '@/components/Search/SearchNav.vue'

export default {
  components: {
    Navbar,
    SearchNav
  }
}
</script>

<style>

</style>