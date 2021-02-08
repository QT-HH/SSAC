<template>
  <div>
    <div class="p-3">
      <v-row v-for="(post, idx) in posts" :key="idx">
        <v-col cols=12>
          <v-card
            color="pink lighten-5"
          >
            <div class="d-flex flex-no-wrap justify-space-between">
              <div>
                  <v-card-title class="headline">
                    {{ post.headline }}
                  </v-card-title>
                    <!-- <v-card-subtitle>Listen to your favorite artists and albums whenever and wherever, online and offline.</v-card-subtitle> -->
              </div>
                <v-avatar
                    class="ma-3"
                    size="125"
                    tile
                >
                  <v-img :src="post.imgsrc"></v-img>
                </v-avatar>

            </div>
          </v-card>
        </v-col>
      </v-row>
      <br>
      <br>
      <br>
    </div>
  </div>
</template>

<script>
export default {
  name: "SportsNews",
  data() {
    return { 
      posts:[
        {
          imgsrc:"https://placekitten.com/70/70",
          headline:"headline1",
          content:"content1"
        },
        {
          imgsrc:"",
          headline:"headline2",
          content:"content2"
        },
        {
          imgsrc:"",
          headline:"headline3",
          content:"content3"
        },
        {
          imgsrc:"",
          headline:"headline4",
          content:"content4"
        },
      ]
    }
  }
}
</script>

<style>

</style>