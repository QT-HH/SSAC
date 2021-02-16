<template>
  <v-app class="app">
    <v-system-bar
    style="background-color: #1A237E"
      height="25"
      fixed
      dark
    >
    <v-spacer></v-spacer>
      <v-icon>mdi-wifi-strength-4</v-icon>
      <v-icon>mdi-signal-cellular-outline</v-icon>
      <v-icon>mdi-battery</v-icon>
      <span>12:30</span>
    </v-system-bar>
    <router-view/>
    <Footer v-if="this.$store.state.isLogin && !this.$store.state.isChatRoom"/>
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
 