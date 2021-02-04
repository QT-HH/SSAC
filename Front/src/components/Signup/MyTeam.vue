<template>
  <div>
      <h1>좋아하는 팀을 고르세요.</h1>
      <h3>(최소 1개 이상)</h3>
      선택한 팀들:
      <button v-for="(team, idx) in myteams" :key="idx" @click="addmyteams(team)">
          {{ team.name }} ({{ translate[team.category]}})
      </button>
      <!-- {{myteams}} -->
      
      <hr>

      <input type="text" v-model="query">
      <div>
        <button @click="turnAll">전체</button>
        <button @click="turnFootball">축구</button>
        <button @click="turnBaseball">야구</button>
        <button @click="turnLOL">롤</button>
      </div>
      <div>
          <b-container class="">
              <b-row>
                  <b-col v-for="(item, idx) in filterTeam(cat,query)" :key="idx" cols="4" class="my-3">
                    <b-card :title="item.name" img-src="" img-alt="Image" img-top @click="addmyteams(item)">
                    <template #footer>
                        <small class="text-muted">{{ item.fandom }}명</small>
                    </template>
                    </b-card>
                  </b-col>                  
              </b-row>
          </b-container>
          <!-- {{ items | sportsCategory(cat) | searchResult(query) }} -->
      </div>

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
            items: [
                {
                    "no": Number,
                    "event_no": Number,
                    "name": "",
                    "logo": "",
                    "count": Number
                }
            ]
            // [ 
            //     {
            //         category: "Football",
            //         name : "포항스틸러스",
            //         fandom : 1234 
            //     },
            //     {   
            //         category: "Football",
            //         name : "대구FC",
            //         fandom : 100000
            //     },
            //     {
            //         category: "Football",
            //         name: "서울FC",
            //         fandom : 123
            //     },
            //     {
            //         category: "Baseball",
            //         name : "삼성라이온즈",
            //         fandom : 1234 
            //     },
            //     {
            //         category: "Baseball",
            //         name : "롯데자이언츠",
            //         fandom : 100000
            //     },
            //     {
            //         category: "Baseball",
            //         name: "한화이글스",
            //         fandom : 123
            //     },
            //     {
            //         category: "LOL",
            //         name : "T1",
            //         fandom : 1234 
            //     },
            //     {
            //         category: "LOL",
            //         name : "젠지",
            //         fandom : 100000
            //     },
            //     {
            //         category: "LOL",
            //         name: "KT",
            //         fandom : 123
            //     }
            // ],
            // cat: "",
            // query: "",
            // event_no: {
            //     1 : "축구",
            //     2 : "야구",
            //     3 : "롤"
            // },
            // myteams: []
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
            this.cat = ""
        },
        turnFootball: function() {
            this.cat = "Football"
        },
        turnBaseball: function() {
            this.cat = "Baseball"
        },
        turnLOL: function() {
            this.cat = "LOL"
        },
        filterTeam: function(cat,que) {
            return this.items.filter(function(it){
                return it.category.includes(cat) && it.name.includes(que)
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
                    if (res.data.message === "success") {
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
        axios.get("").then(response => (this.items = response))
        console.log('MyTeams')
    }
}
</script>

<style>

</style>