<template>
  <div>
    <v-row align="center">
      <br>
      <br>
      
      <v-col>
        <div 
        v-for="(article, idx) in articles" 
        :key="idx" 
        :article="article"
        >
        <hr style="height:10px; visibility:hidden;" />
        <v-card 
        class="mx-auto rounded-card" 
        max-width="400" 
        outlined
        type="white"
        
>
          <v-card-title>
            <span class="title font-weight-light">{{ article.title }}</span>
          </v-card-title>

          <v-card-text class="headline font-weight-bold">
            {{article.context}}
          </v-card-text>

          <v-card-actions>
            <v-list-item class="grow">
              <v-list-item-avatar color="grey darken-3">
                <v-img class="elevation-6" alt="" :src="user.img"></v-img>
              </v-list-item-avatar>

              <v-list-item-content>
                <v-list-item-title>{{user.nickname}}</v-list-item-title>
              </v-list-item-content>

              <v-row align="center" justify="end">
                <v-icon class="mr-1">
                  mdi-heart
                </v-icon>
                <span class="subheading mr-2">256</span>
                <span class="mr-1">·</span>
                <v-icon class="mr-1">
                  mdi-share-variant
                </v-icon>
                <span class="subheading">45</span>
              </v-row>
            </v-list-item>
          </v-card-actions>
        </v-card>
        </div>
        <br>
        <br>
        <br>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name:"Article",
  data: () => ({
    }),
  computed: {
    ...mapState({
     articles: 'articles',
     user: 'user'
   })
  }
}
</script>

<style>
.rounded-card{
    border-radius:50px;
    width: 95%
}

</style>