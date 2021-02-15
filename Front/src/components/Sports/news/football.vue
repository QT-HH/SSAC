<template>
  <div>
    <v-sheet>
      <v-carousel
        cycle
        height="300"
        hide-delimiter-background
        show-arrows-on-hover
        light
        hide-delimiters
      >
        <v-carousel-item
          v-for="(post, i) in posts"
          :key="i"
        >
          <v-sheet 
            height="100%"
          >
          <a :href="post.link" target="_Blank">
            <v-card
              class="mx-auto"
              max-width="300"
            >
                <v-img 
                  :src="post.imgsrc"
                  max-height="150"
                ></v-img>

              <v-card-title>
                {{ post.title }}
              </v-card-title>

            </v-card>
          </a>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
    </v-sheet>
    <div class="p-3">
      <v-row v-for="(post, idx) in posts" :key="idx">
        <v-col cols=12>
          <a :href="post.link" target="_blank">
            <v-card
              color="blue lighten-5"
            >
              <div class="d-flex flex-no-wrap justify-space-between">
                <div>
                    <v-card-title class="headline">
                      {{ post.title }}
                    </v-card-title>
                      <v-card-subtitle>{{post.content}}</v-card-subtitle>
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
          </a>
        </v-col>
      </v-row>
      <br>
      <br>
      <br>
    </div>
  </div>
</template>

<script>
import data from "../../../../../Crawling/newsFoot.json"

export default {
  name: "NewsFoot",
  mounted() {
    this.posts = data
  },
  data() {
    return { 
      posts:[
        // {
        //   imgsrc:"https://placekitten.com/70/70",
        //   headline:"headline1",
        //   content:"content1"
        // },
        // {
        //   imgsrc:"",
        //   headline:"headline2",
        //   content:"content2"
        // },
        // {
        //   imgsrc:"",
        //   headline:"headline3",
        //   content:"content3"
        // },
        // {
        //   imgsrc:"",
        //   headline:"headline4",
        //   content:"content4"
        // },
      ]
    }
  }
}
</script>

<style>
a {
  text-decoration: none;
}
</style>