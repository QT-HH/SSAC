<template>

    <v-footer
      fixed
      :padless="padless"
      class="ml-2 mr-2 mb-2 opa rounded-xl"
    >
      <v-card
        width="100%"
        class="rounded-xl"
      >
        <v-card-text>
          <v-row>
            <v-col>
              <v-btn
                icon
                @click="gotoSports"
              >
                <v-icon size="24px">
                  {{ icons[0] }}
                </v-icon>
              </v-btn>
            </v-col>
            <v-col>
              <v-btn
                icon
                @click="gotoFeed"
              >
                <v-icon size="24px">
                  {{ icons[1] }}
                </v-icon>
              </v-btn>
            </v-col>
            <v-col>
              <v-btn
                icon
                @click="gotoSearch"
              >
                <v-icon size="24px">
                  {{ icons[2] }}
                </v-icon>
              </v-btn>
            </v-col>
            <v-col>
              <v-btn
                icon
                @click="gotoProfile"
              >
                <v-icon size="24px">
                  {{ icons[3] }}
                </v-icon>
              </v-btn>
            </v-col>
            <v-col>
              <v-dialog
                v-model="dialog"
                width="500"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn 
                    icon
                    v-bind="attrs"
                    v-on="on"
                    ><v-icon>{{ icons[4] }}</v-icon>
                  </v-btn>
                </template>

                <v-card>
                  <v-card-title class="headline grey lighten-2">
                    여러가지 알림들!
                  </v-card-title>

                  <v-card-text class="p-2">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                  </v-card-text>

                </v-card>
              </v-dialog>
            </v-col>
          </v-row>
        </v-card-text>

      </v-card>


    </v-footer>


</template>

<script>
// import {getAlert} from "@/api/common.js"

export default {
  name:"Footer",
  data () {
    return {
      icons: [
        'mdi-home',
        'mdi-text-box',
        'mdi-magnify',
        'mdi-account',
        'mdi-bell-outline',
      ],
      padless: true,
      rounded: true,
      dialog: false,
      funcs: [
        "gotoSports",
        "gotoFeed",
        "gotoSearch",
        "checkAlert",
        "gotoProfile",
      ]
    }
  },
  methods: {
    gotoSports() {
      if (this.$route.path !== "/tabs") {
        this.$router.push({name:'SportsSchedule'})
      }
    },
    gotoFeed() {
      if (this.$route.path !== "/tabs/feed") {
        this.$router.push({name:'Feed'})
      }
    },
    gotoSearch() {
      if (this.$route.path !== "/tabs/search") {
        this.$router.push({name:'realSearch'})
      }
    },
    checkAlert() {
      
    },
    gotoProfile() {
      if (this.$route.path !== "/profile") {
        this.$router.push({name:'Article'})
      }
    },
  }
}
</script>

<style>
.opa {
  opacity: 0.6;
  
}

</style>