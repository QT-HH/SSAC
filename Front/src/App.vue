<template>
  <v-app>
    <router-view/>
    <Footer v-if="this.$store.state.isLogin"/>
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
 