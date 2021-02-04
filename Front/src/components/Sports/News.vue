<template>
  <div>
    <div class="p-3">
      <div v-for="(post, idx) in posts" :key="idx">
        <b-card :img-src=post.imgsrc img-alt="Card image" img-left class="mb-3">
          <b-card-text>
            {{post.headline}}
          </b-card-text>
        </b-card>
      </div>
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