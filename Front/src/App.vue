<template>
  <v-app class="app">
    <router-view/>
    <div  v-if="this.$store.state.isLogin && !this.$store.state.isChatRoom" >
    <Footer/>
    </div>
  </v-app>
</template>

<script>
import Footer from "@/components/common/Footer.vue"
export default {
  name: 'App',
  components: {
    Footer,
  },
};
</script>

<style>
.app {
  max-width: 100%;
  overflow-x: hidden;
}
</style>
 