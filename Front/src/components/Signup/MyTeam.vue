<template>
  <div class="fill-height">
    <!-- 로고 -->
    <v-container>
      <v-row class="mt-5 mb-5" align="center" justify="center">
        <v-img
          :src="logo.src"
          max-width="130"
          max-height="50"
          class="rounded-pill"
        >
        </v-img>
      </v-row>
    </v-container>

    선택한 팀들:
    <v-btn v-for="(team, idx) in myteams" :key="idx" @click="addmyteams(team)">
        {{ team.name }} ({{ translate[team.event_no] }})
    </v-btn>
    <!-- {{myteams}} -->
    
    <hr>

    <v-container>
    <v-row>
        <v-col 
        cols=4
        v-for="(team, idx) in filterTeam(cat,query)"
        :key="idx"
        :team="team"
        >
        <v-card
            hover
            class="mx-auto"
            style="max-width: 15rem;"
            tag="article"
            border-variant="success"
        >
            <v-img
            :src="team.img"
            class="white--text align-end"
            height="100px"
            >
            <v-card-title>{{ team.name }}</v-card-title>
            </v-img>

            <v-card-text class="text--primary">
            {{ team.count }} 명이 좋아합니다.
            </v-card-text>

        </v-card>
        </v-col>
    </v-row>
    </v-container>

    <div>
    <button
        class="btn-bottom"
        @click="backtoSignup"
        >BEFORE
    </button>
    <button
        class="btn-bottom"
        @click="completeSignup"
        >NEXT
    </button>
    </div>
  </div>
</template>

<script>
import { signup } from "@/api/user/signup.js"
import axios from 'axios'

export default {
    name: "MyTeam",
    data() {
        return {
            items: 
            [
                {
                    "no": Number,
                    "event_no": Number,
                    "name": "",
                    "logo": "",
                    "count": Number
                }
            ],
            cat: 0,
            query: "",
            translate: {
                1 : "축구",
                2 : "야구",
                3 : "롤"
            },
            myteams: [],
            logo : { src : require("@/assets/images/logo.png") }
        }
    },
    // filters: {
    //     sportsCategory: function (sports,cat) {
    //         const sportt = sports.filter(function (sport) {
    //             return sport.category === cat
    //         })
    //         return sportt
    //     },
    //     searchResult: function (sports,query) {
    //         const sportt = sports.filter(function (sport) {
    //             return sport.name.includes(query)
    //         })
    //         return sportt
    //     }
    // },
    methods: {
        turnAll: function() {
            this.cat = 0
        },
        turnFootball: function() {
            this.cat = 1
        },
        turnBaseball: function() {
            this.cat = 2
        },
        turnLOL: function() {
            this.cat = 3
        },
        filterTeam: function(cat,que) {
            return this.items.filter(function(it){
                return (it.event_no*cat === 0 || it.event_no === cat) && it.name.includes(que)
            })
        },
        addmyteams: function(item) {
            let before = this.myteams.length
            for (let index = this.myteams.length-1; index > -1; index--) {
                if (this.myteams[index].name === item.name){
                    this.myteams.splice(index,1)
                }
            }
            let now = this.myteams.length
            if(before == now){
                this.myteams.push(item)
            }
        },
        backtoSignup() {
            this.$router.push("/signup");
        },
        completeSignup() {
            this.$store.dispatch("CREATE_USER2", this.myteams)
            signup(
                this.$store.state.newUser,
                (res) => {
                    if (res.data === "success") {
                        this.$router.push("/signup/welcome")
                    } else {
                        alert("로그인 실패하였습니다1.")
                    }
                },
                (err) => {
                    console.log(err)
                    alert("로그인 실패하였습니다2.")
                }
            );
        }
    },
    mounted: function () {
        axios.get("http://i4d102.p.ssafy.io:9000/ssac/team/list").then(response => (this.items = response.data))
        console.log('MyTeams')
    }
}
</script>

<style>

</style>
