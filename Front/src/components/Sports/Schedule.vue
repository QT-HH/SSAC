<template>
  <div class="mt-10 ml-2 mr-2 mb-3">
    <v-sheet>
      <v-carousel
        cycle
        height="300"
        hide-delimiter-background
        show-arrows-on-hover
        light
        hide-delimiters
        v-if="filterCarousel2(today,hm).length > 0"
      >
        <h1 class="ml-5">
          오늘의 경기
        </h1>
        <v-carousel-item
          v-for="(event, i) in filterCarousel2(today,hm)"
          :key="i"
        >
          <v-sheet 
            height="100%"
          >
            <v-card
              class="mx-auto"
              max-width="344"
            >
              <v-avatar
                  size="172"
                  tile
              >
                <v-img src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"></v-img>
              </v-avatar>
              <v-avatar
                  size="172"
                  tile
              >
                <v-img src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"></v-img>
              </v-avatar>

              <v-card-title>
                {{ event.name }}
              </v-card-title>

              <v-card-subtitle>
                {{ event.start }}
              </v-card-subtitle>
            </v-card>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
      <div v-else class="text-center">
        <h1> 오늘은 경기가 없습니다 </h1>
      </div>
    </v-sheet>
    <v-expand-transition>
      <v-card
        v-show="expandDaily"
        width="95%"
        class="mx-auto grey lighten-2 text-center"
        elevation="2"
      >
        <div class="py-3 grey darken-3 white--text" @click="closeDaily">
          <span><h2>{{focus}}</h2></span>
        </div>
        <v-list-item
          v-for="(event, i) in filterDaily(focus)"
          :key="i"
        >
          <!-- <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon> -->
          <v-list-item-content>
            <v-list-item-title class="text-center" @click="changeShow(i)">
              <v-btn
                rounded
                :color="colors[event.events_no]"
                dark 
              >
                {{ event.name }}
              </v-btn>
            </v-list-item-title>
             <v-expand-transition>
              <v-card
                v-show="expandGame[i]"
              >
                <v-card-text>
                  <v-row justify="center">
                    <v-col 
                      v-if="event.gameDone.length === 4"
                      cols=12
                    >
                      경기끝
                    </v-col>
                    <v-col 
                      v-if="event.betDone.length === 4"
                      cols=12
                    >
                      베팅끝
                    </v-col>
                    <v-col 
                      v-if="event.calDone.length === 4"
                      cols=12
                    >
                      정산끝
                    </v-col>
                    <v-col cols=4>
                        <span><h1>{{teamname[event.team1_id]}}</h1></span>
                    </v-col>
                    <v-col cols=4>
                        {{event.team1_score}}   vs   {{event.team2_score}}
                    </v-col>
                    <v-col cols=4>
                        <span><h1>{{teamname[event.team2_id]}}</h1></span>
                    </v-col>
                        <br>
                    <v-col cols=6>
                      <v-btn
                        @click="editScore(event,1,-1)"
                        v-if="mas"
                      >
                        -
                      </v-btn>
                      <v-btn
                        @click="editScore(event,1,1)"
                        v-if="mas"
                      >
                        +
                      </v-btn>
                      <v-btn
                        :disabled="isDisabled(event,event.team1_user)"
                        @click="betTeam(event,1)"
                      >
                        투표!
                      </v-btn>

                    </v-col>

                    <v-col cols=6>
                      <v-btn
                        @click="editScore(event,2,-1)"
                        v-if="mas"
                      >
                        -
                      </v-btn>
                      <v-btn
                        @click="editScore(event,2,1)"
                        v-if="mas"
                      >
                        +
                      </v-btn>
                      <v-btn
                        :disabled="isDisabled(event,event.team2_user)"
                        @click="betTeam(event,2)"
                      >
                        투표!
                      </v-btn>
                    </v-col>
                    <v-col cols=12>
                      무승부
                      <br>
                      <v-btn
                        :disabled="isDisabled(event,event.draw_user)"
                        @click="betTeam(event,3)"
                      >
                        투표!
                      </v-btn>
                    </v-col>
                    <v-col>
                      투표비율({{teamname[event.team1_id]}} : {{teamname[event.team2_id]}} : 무승부): {{event.team1_user.length}} : {{event.team2_user.length}} : {{event.draw_user.length}}
                      <br>
                      예상획득포인트({{teamname[event.team1_id]}}) : {{ betRatio(event.team1_user.length,event.team2_user.length,event.draw_user.length,event.team1_user.length) }}
                      <br>
                      예상획득포인트({{teamname[event.team2_id]}}) : {{ betRatio(event.team1_user.length,event.team2_user.length,event.draw_user.length,event.team2_user.length) }}
                      <br>
                      예상획득포인트(무승부) : {{ betRatio(event.team1_user.length,event.team2_user.length,event.draw_user.length,event.draw_user.length) }}
                    </v-col>
                    <v-col cols=12>
                      <v-btn
                        @click="endBetting(event)"
                        v-if="mas"
                      >
                        베팅 끝내기
                      </v-btn>
                      <br>
                      <v-btn
                        @click="endtheGame(event)"
                        v-if="mas"
                      >
                        경기 끝내기
                      </v-btn>
                      <br>
                      <v-btn
                        @click="calculEvent(event)"
                        v-if="mas"
                      >
                        정산하기
                      </v-btn>
                    </v-col>
                  </v-row>
                </v-card-text>
              </v-card>
            </v-expand-transition>
          </v-list-item-content>
        </v-list-item>
        <v-card-actions>
          <v-btn 
            @click="closeDaily"
            text
            class="ml-auto"
          >
            닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-expand-transition>

    <!-- 팀 필터 -->
    <v-sheet>
      <v-card class="px-5">
        <h3>Myteams</h3>
        <v-row>
          <v-col cols=3>
            <img @click="filterMyteam(0)" class="d-flex rounded-circle" width="60px" height="60px" src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMTVfMTE4/MDAxNDg5NTMzMTAwMjY0.m9UYu7Dt4CyJcaMMeAuIhOFP2nnXBnW5eUqx3rXZY14g.3axKiINI_FaRrOzK70_FY2qRXLulYTBkzwFIaeY8yd4g.JPEG.doghter4our/IMG_5252.jpg?type=w800" alt="Generic placeholder image">
            전체
          </v-col>
          <v-col cols=3 
            v-for="(myteam,idx) in myteamss"
            :key=idx
          >
            <img @click="filterMyteam(myteam.team_no)" class="d-flex rounded-circle" width="60px" height="60px" :src="changeBlob(myteam.logo)" alt="team">
            {{teamname[myteam.team_no]}}
          </v-col>
        </v-row>
      </v-card>
    </v-sheet>


    <v-sheet>
      <v-toolbar
        flat
      >
      <v-row align="center" justify="space-around">
        <v-btn
          outlined
          color="grey darken-2"
          @click="setToday"
          small
          class="mr-3"
        >
          Today
        </v-btn>
        <v-btn
          icon
          @click="prev"
          color="blue"
        >
          <v-icon>mdi-chevron-left</v-icon>
        </v-btn>
        <v-toolbar-title v-if="$refs.calendar">
          {{ $refs.calendar.title }}
        </v-toolbar-title>
        <v-btn
          icon
          @click="next"
        >
          <v-icon>mdi-chevron-right</v-icon>
        </v-btn>
      </v-row>
      </v-toolbar>
    </v-sheet>
    <v-sheet height="600">
      <v-calendar
        categories="test01"
        ref="calendar"
        v-model="focus"
        :events="events"
        :event-color="getEventColor"

        @click:event="showEvents"
        @click:more="viewDay"
        @click:date="viewDay"
        :type="type[tog]"
      ></v-calendar>
      <!-- <v-menu
        v-model="selectedOpen"
        :close-on-content-click="false"
        :activator="selectedElement"
        offset-x
      >
        <v-card
          color="grey lighten-4"
          min-width="350px"
          flat
        >

          <v-card-text>
            
          </v-card-text>

        </v-card>
      </v-menu>  -->
    </v-sheet>
    <br>
    <br>
  </div>
</template>

<script>
import { getMyTeam, getSchedule, pmScore, betGame, endBet, endGame, calculPts } from "@/api/tabs/sports.js"
// 
  export default {
    name:"Schedule",
    data: () => ({
      mas:false,
      myteamss:[

      ],
      myteams:[

      ],
      teamname:{
        1: '강원', 2: '광주', 3: '대구', 4: '서울', 5: '성남', 6: '수원', 7: '수원FC', 8: '울산', 9: '인천', 10: '전북', 11: '제주', 12: '포항',
        23: '두산', 24: '롯데', 25: '삼성', 26: '키움', 27: '한화', 28: 'KIA', 29: 'KT wiz', 30: 'LG', 31: 'NC', 32: 'SK',
        33: '담원 기아', 34: 'DRX', 35: '젠지', 36: '아프리카', 37: 'T1', 38: 'KT', 39: '리브 샌박', 40: '농심', 41: '한화생명', 42: '프레딧'
      },
      expandGame:[false,false,false,false,false,false,false,false,false,false],
      expandDaily:false,
      dialog:false,
      focus: '',
      tog:true,
      type: {
        true:"month",
        false:"day",
      },
      selectedEvent: {
        id: Number,
        events_no: Number,
        name: String, // (달력에 표시할 이름) 
        start: String, // ('YYYY-MM-DD')
        start_time: String, // ("hh-mm")
        team1_id: Number, // (팀1 고유번호)
        team2_id: Number, // (팀2 고유번호)
        team1_score: Number, // (팀1 점수)
        team2_score: Number, // (팀2 점수)
        team1_user: Array,  // (팀1에 베팅한 유저 고유번호 리스트)
        team2_user: Array, // (팀2에 베팅한 유저 고유번호 리스트)
        draw_user: Array, // (무승부에 베팅한 유저 고유번호 리스트)
        betDone: "false", // (베팅 끝났는지)
        gameDone: "false", // (게임 끝났는지)
        calDone: "false", // (정산 끝났는지)
      },
      selectedElement: null,
      selectedOpen: false,
      colors: ['grey darken-1','blue','red', 'green'],
      // events:[],
      originevents:[
        // {
        //   id: 1,
        //   events_no: 3,
        //   name: "T1 vs 젠지", // (달력에 표시할 이름) 
        //   start: "2021-02-13", // ('YYYY-MM-DD')
        //   start_time: "17:00", // ("hh-mm")
        //   team1_id: 37, // (팀1 고유번호)
        //   team2_id: 35, // (팀2 고유번호)
        //   team1_score: 1, // (팀1 점수)
        //   team2_score: 2, // (팀2 점수)
        //   team1_user: [1],  // (팀1에 베팅한 유저 고유번호 리스트)
        //   team2_user: [], // (팀2에 베팅한 유저 고유번호 리스트)
        //   draw_user: [], // (무승부에 베팅한 유저 고유번호 리스트)
        //   betDone: "true", // (베팅 끝났는지)
        //   gameDone: "true", // (게임 끝났는지)
        //   calDone: "true", // (게임 끝났는지)
        // }, 
        // {
        //   id: 2,
        //   events_no: 1,
        //   name: "대구 vs 서울", // (달력에 표시할 이름) 
        //   start: "2021-02-18", // ('YYYY-MM-DD')
        //   start_time: "19:00", // ("hh-mm")
        //   team1_id: 3, // (팀1 고유번호)
        //   team2_id: 4, // (팀2 고유번호)
        //   team1_score: 0, // (팀1 점수)
        //   team2_score: 0, // (팀2 점수)
        //   team1_user: [],  // (팀1에 베팅한 유저 고유번호 리스트)
        //   team2_user: [], // (팀2에 베팅한 유저 고유번호 리스트)
        //   draw_user: [0], // (무승부에 베팅한 유저 고유번호 리스트)
        //   betDone: "false", // (베팅 끝났는지)
        //   gameDone: "true", // (게임 끝났는지)
        //   calDone: "true", // (게임 끝났는지)
        // },
        // {
        //   id: 3,
        //   events_no: 2,
        //   name: "삼성 vs 롯데", // (달력에 표시할 이름) 
        //   start: "2021-02-19", // ('YYYY-MM-DD')
        //   start_time: "21:30", // ("hh-mm")
        //   team1_id: 25, // (팀1 고유번호)
        //   team2_id: 24, // (팀2 고유번호)
        //   team1_score: 0, // (팀1 점수)
        //   team2_score: 0, // (팀2 점수)
        //   team1_user: [1,2,3,4,5,6,7],  // (팀1에 베팅한 유저 고유번호 리스트)
        //   team2_user: [8,9,10,11,12,13], // (팀2에 베팅한 유저 고유번호 리스트)
        //   draw_user: [14,15], // (무승부에 베팅한 유저 고유번호 리스트)
        //   betDone: "false", // (베팅 끝났는지)
        //   gameDone: "false", // (게임 끝났는지)
        //   calDone: "false", // (게임 끝났는지)
        // },
        // {
        //   id: 4,
        //   events_no: 1,
        //   name: "강원 vs 수원FC", // (달력에 표시할 이름) 
        //   start: "2021-02-19", // ('YYYY-MM-DD')
        //   start_time: "21:30", // ("hh-mm")
        //   team1_id: 1, // (팀1 고유번호)
        //   team2_id: 7, // (팀2 고유번호)
        //   team1_score: 0, // (팀1 점수)
        //   team2_score: 0, // (팀2 점수)
        //   team1_user: [1,2,3,4,5,6,7],  // (팀1에 베팅한 유저 고유번호 리스트)
        //   team2_user: [8,9,10,11], // (팀2에 베팅한 유저 고유번호 리스트)
        //   draw_user: [14,15], // (무승부에 베팅한 유저 고유번호 리스트)
        //   betDone: "false", // (베팅 끝났는지)
        //   calDone: "false", // (게임 끝났는지)
        // },
        // {
        //   id: 5,
        //   events_no: 3,
        //   name: "DRX vs 아프리카", // (달력에 표시할 이름) 
        //   start: "2021-02-19", // ('YYYY-MM-DD')
        //   start_time: "13:00", // ("hh-mm")
        //   team1_id: 34, // (팀1 고유번호)
        //   team2_id: 36, // (팀2 고유번호)
        //   team1_score: 0, // (팀1 점수)
        //   team2_score: 0, // (팀2 점수)
        //   team1_user: [1,2,3,4,5,6,7],  // (팀1에 베팅한 유저 고유번호 리스트)
        //   team2_user: [8,9,10,11,12,13,14,15], // (팀2에 베팅한 유저 고유번호 리스트)
        //   draw_user: [], // (무승부에 베팅한 유저 고유번호 리스트)
        //   betDone: "true", // (베팅 끝났는지)
        //   calDone: "false", // (게임 끝났는지)
        // },
      ],
      events:[],
      today:'',
      hm:'',
    }),
    mounted () { 
      this.$refs.calendar.checkChange()
      getSchedule(
        (res) => {
          // console.log(res.data)
          this.originevents = res.data
          this.events = this.originevents
        },
        (err) => {
          console.log(err)
        }
      )
      let now = new Date()
      let time = `${now.getFullYear()}-${now.getMonth()+1}-${now.getDate()}`
      // let hourmin = `${now.getHours()}:${now.getMinutes()}`
      let h = now.getHours()
      let m = now.getMinutes()
      h = `${h}`
      m = `${m}`
      if (h.length === 1){
        h = `0${h}`
      }
      if (m.length === 1){
        m = `0${h}`
      }
      this.today = time
      this.focus = time
      this.hm = `${h}:${m}`
      // console.log(this.hm < '19:00')
      // this.events = this.originevents
      getMyTeam(
        this.$store.state.user.userid,
        (res) => {
          this.myteamss = res.data
          console.log(res.data)
        },
        (err) => {
          console.log(err)
        }
      )
    },
    methods: {
      toggle () {
        this.tog = !this.tog
      },
      viewDay ( {date} ) {
        this.focus = date
        this.resetShow()
        let items = this.filterDaily(date)
        if (items.length > 0){
          this.expandDaily = true
        }
        // this.tog = false
      },
      getEventColor (event) {
        return this.colors[event.events_no]
      },
      setToday () {
        this.focus = ''
      }, 
      prev () {
        this.$refs.calendar.prev()
      },
      next () {
        this.$refs.calendar.next()
      },
      // showEvent ({ nativeEvent, event }) {
      //   const open = () => {
      //     this.selectedEvent = event
      //     this.selectedElement = nativeEvent.target
      //     setTimeout(() => {
      //       this.selectedOpen = true
      //     }, 10)
      //   }

      //   if (this.selectedOpen) {
      //     this.selectedOpen = false
      //     setTimeout(open, 10)
      //   } else {
      //     open()
      //   }

      //   nativeEvent.stopPropagation()
      // },
      showEvents ({event}) {
        let ev = {
          date:event.start
        }
        this.viewDay(ev)
      },
      closeDaily () {
        this.expandDaily = false
        this.resetShow()
      },
      filterDaily : function(focus) {
        // console.log(focus)
        return this.events.filter(function(ev){
          return ev.start.includes(focus)
        })
      },
      changeShow(idx) {
        this.$set(this.expandGame, idx, !this.expandGame[idx])
      },
      resetShow: function() {
        for(let idx = 0; idx < 10; idx++) {
          this.$set(this.expandGame, idx, false)
        }
      },
      betRatio(team1,team2,draw,want) {
        let pts = Math.round((team1+team2+draw-want)/((team1+team2+draw)*2)*100)
        if (team1+team2+draw === 0) {
          return 0
        }
        if (pts === 0) {
          return 1
        } else {
          return pts
        }
      },
      isDisabled(event,want) {
        let userid = this.$store.state.user.userid
        if ((event.betDone.length === 4) || (event.gameDone.length === 4)) {
          return true
        }
        if (event.team1_user.includes(userid) || event.team2_user.includes(userid) || event.draw_user.includes(userid)) {
          if (want.includes(userid)) {
            return false
          } else {
            return true
          }
        } else {
          return false
        }
      },
      betTeam(event,n) {
        let userid = this.$store.state.user.userid
        let want = []
        if (n === 1) {
          want = event.team1_user
        } else if (n === 2) {
          want = event.team2_user
        } else {
          want = event.draw_user
        }
        betGame(
          event.id,userid,n,
          (res) => {
            console.log(res.data)
            if (want.includes(userid)) {
              console.log("빼기")
              let idx = want.indexOf(userid)
              want.splice(idx,1)
            } else {
              console.log("더하기")
              want.push(userid)
            }
          },
          (err) => {
            console.log(err)
          }
        )

      },
      endBetting(event) {
        if (event.gameDone.length === 4) {
          console.log("게임 끝을 풀어줭")
        } else {
          let bool = event.betDone.length
          if (bool === 4) {
            bool = "false"
          } else {
            bool = "true"
          }
          endBet(
            event.id,bool,
            (res) => {
              console.log(res.data)
              if (bool.length === 4) {
                event.betDone = "true"
              } else {
                event.betDone = "false"
              }
            },
            (err) => {
              console.log(err)
            }
          )
        }
      },
      endtheGame(event) {
        if (event.betDone.length === 5) {
          if (event.gameDone.length === 5) {
            event.gameDone = "false"
          } else {
            console.log("베팅 먼저 끝내세용")
          }
        } else {
          let bool = event.gameDone.length
          if (bool === 4) {
            bool = "false"
          } else {
            bool = "true"
          }
          endGame(
            event.id,bool,
            (res) => {
              console.log(res.data)
              if (bool.length === 4) {
                event.gameDone = "true"
              } else {
                event.gameDone = "false"
              }
            },
            (err) => {
              console.log(err)
            }
          )
          console.log("게임끝")
        }
      },
      calculEvent(event) {
        if ((event.betDone.length === 4) && (event.gameDone.length === 4) && (event.calDone.length === 5)) {
          let users = []
          if (event.team1_score > event.team2_score) {
            users = event.team1_user
          } else if(event.team1_score < event.team2_score) {
            users = event.team2_user
          } else {
            users = event.draw_user
          }
        
          let pts = this.betRatio(event.team1_user.length,event.team2_user.length,event.draw_user.length,users.length)
          calculPts(
            event.id,users,pts,
            (res) => {
              console.log(res.data)
              event.calDone = "true"
            },
            (err) => {
              console.log(err)
            }
          )
          

        } else {
          alert("베팅, 게임먼저 끝내세용")
        }
      },
      editScore(event,n,pm) {
        console.log(event.id,n,pm)
        pmScore(
          event.id,n,pm,
          (res) => {
            if (n === 1 && pm === 1) {
              event.team1_score += 1
            } else if (n === 1 && pm === -1) {
              event.team1_score -= 1
            } else if (n === 2 && pm === 1) {
              event.team2_score += 1
            } else {
              event.team2_score -= 1
            }
            console.log(res.data)
            
          },
          (err) => {
            console.log(err)
          }
        )
      },
      filterMyteam(number) {
        if (number === 0) {
          this.myteams = []
        } else {
          if (this.myteams.includes(number)){
            let idx = this.myteams.indexOf(number)
            this.myteams.splice(idx,1)
          } else {
            this.myteams.push(number)
          }  
        }
        if (this.myteams.length === 0) {
          this.events = this.originevents
        } else {
          this.events = this.filterteam(this.myteams)
        }
      },
      filterteam : function(myteams) {
        // console.log(focus)
        return this.originevents.filter(function(ev){
          return myteams.includes(ev.team1_id) || myteams.includes(ev.team2_id)
        })
      },
      filterCarousel : function(today,hm) {
        let items = this.filterDaily(today)
        // console.log(2)
        // console.log(items)
        return items.filter(function(ev){
          return ev.start_time > hm
        })
      },
      filterCarousel2 : function(today,hm) {
        let items = this.filterCarousel(today,hm)
        // console.log(1)
        // console.log(items)
        return items.sort(function(a,b){
          return a.start_time - b.start_time
        })
      },
      master() {
        this.mas = !this.mas
      },
      changeBlob(data){
        let blob = new Blob([new ArrayBuffer(data)], {type: "image/jpg"});
        const url = window.URL.createObjectURL(blob);
        // document.getElementsByClassName("feedimg").src = url
        console.log(url)
        return url
      }

    }, 
  }
</script>
