<template>
  <div>
    <strong class="title">SSAC</strong>
    <v-icon left @click="gotoProfile">
      mdi-arrow-left
    </v-icon>
    <br>
    <br>
    <v-container class="text-center">
      <v-dialog v-model="dialog" width="500">
        <template v-slot:activator="{ on, attrs }">
          <v-badge bordered bottom color="blue-grey lighten-4" icon="mdi-camera" offset-x="25" offset-y="25">
            <v-avatar height="110" width="110" v-bind="attrs" v-on="on">
              <img :src="user.img" alt="John">
            </v-avatar>
          </v-badge>
        </template>
        <v-card>
          <v-card-title class="headline blue--text text--lighten-1 ">
            프로필 사진
          </v-card-title>

          <v-file-input class="d-flex align-center justify-center pa-4 mx-auto" label="File input" filled
            prepend-icon="mdi-camera"></v-file-input>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                text
                @click="dialog = false"
              >취소</v-btn>
            <v-btn color="primary" text @click="dialog = false">
              적용
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

    </v-container>

    <v-container>
      <v-row class="d-flex align-center justify-center pa-4 mx-auto">
        <v-col>
          <v-text-field v-model="nickname" label="별명" style="width:300px;"></v-text-field>

          <v-text-field v-model="introduce" label="한줄소개" style="width:300px;"></v-text-field>

        </v-col>
      </v-row>
      <v-container class="d-flex align-center justify-center pa-4 mx-auto">
        <v-row align="center" justify="space-around">
          <v-btn large @click="goback">취소</v-btn>
          <v-btn v-if="this.nickname.length === 0 || this.introduce.length === 0" large disabled depressed
            color="primary" @click="usernickname">
            확인
          </v-btn>
          <v-btn v-else large color="primary" @click="usernickname">
            확인
          </v-btn>
        </v-row>
      </v-container>

      <v-container class="d-flex align-center justify-center pa-4 mx-auto">
        <v-dialog
        transition="dialog-bottom-transition"
        max-width="600"
      >
        <template v-slot:activator="{ on, attrs }">
        <v-btn text color="grey"
        v-bind="attrs"
            v-on="on">
          회원탈퇴
        </v-btn>
        </template>
        <template v-slot:default="dialog">
          <v-card>
            <v-toolbar
              color="primary"
              dark
            >회원탈퇴</v-toolbar>
            <v-card-text>
              <div class="text-h6 pa-12">나는 빡빡이다.</div>
              <template>
                <v-text-field :rules="rules"></v-text-field>
              </template>
            </v-card-text>
            <v-card-actions class="justify-end">
              <v-btn
                text
                @click="dialog.value = false"
              >취소</v-btn>
              <v-btn color="red" text @click="dialog.value = false">
              회원탈퇴
            </v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </v-dialog>
      </v-container>

    </v-container>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
    name: 'ProfileEdit',
     data: () => ({
      valid: false,
      nickname: '',
      introduce: '',
      dialog: false,
      rules: [
        value => !!value || 'Required.',
        value => value === '나는 빡빡이다' || '나는 빡빡이다를 작성하세요',
        
      ],
    }),

    computed: {
      ...mapState([
        'user'
      ])
    },
    methods:{
      gotoProfile() {
        if (this.$route.path !== "/profile") {
          this.$router.push({name:"Article"})
          }
        },
      goback() {
        this.$router.push({name:"Article"})
      },
      usernickname() {
        const nickname = this.nickname
        const introduce = this.introduce
        console.log(nickname);
        console.log(introduce);
        this.$store.dispatch('usernickname', {nickname,introduce})
        this.$router.push({name:"Article"})
      }
    }
}
</script>

<style scoped>
.v-avatar:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}
</style>