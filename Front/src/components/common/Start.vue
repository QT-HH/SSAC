<template>
  <div class="black fill-height fill-width">
    <v-container class="fill-height" align="center" justify="center">
      <v-row align="center" justify="center">
        <div v-anime="{ rotate: '1turn', backgroundColor: '#000000', duration: 20000, loop: false }">
          <v-img
            :src="start.src"
            @click="gotoLogin"
          >
          </v-img>
        </div>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  data: () => {
    return {
      start : { src : require("@/assets/images/START.png") }
    }
  },
  methods: {
    gotoLogin() {
      this.$router.push("/login");
    }        
  },
  mounted() {
    const targets = this.$el;
    this
      .$anime
      .timeline()
      .add({
        targets,
        easing: 'easeOutExpo',
      });
  },
}
</script>

<style scoped>
</style>