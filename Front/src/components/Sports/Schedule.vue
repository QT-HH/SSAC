<template>
  <div class="mt-1">
    <!-- <v-sheet>
        <h1 class="ml-5">
          Upcoming!
        </h1>
      <v-carousel
        cycle
        height="300"
        hide-delimiter-background
        show-arrows-on-hover
        light
      >
        <v-carousel-item
          v-for="(event, i) in events"
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
    </v-sheet> -->
    <v-expand-transition>
      <v-card
        v-show="expandDaily"
        width="100%"
        class="px-2 blue text-center"
        elevation="2"
      >
        <v-card-title>
          데일리 경기 ( {{ focus }} )
        </v-card-title>
        <v-list-item
          v-for="(event, i) in filterDaily(focus)"
          :key="i"
        >
          <!-- <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon> -->
          <v-list-item-content>
            <v-list-item-title class="text--center" @click="changeShow(i)">
              {{ event.name }}
            </v-list-item-title>
             <v-expand-transition>
              <v-card
                v-show="expandGame[i]"
              >
                <v-card-text>
                  <v-row justify="center">
                    <v-col cols=6>
                        {{teamname[event.team1_id]}}
                        <br>
                        {{event.team1_score}}
                        <br>
                      <v-btn>
                        투표!
                      </v-btn>
                    </v-col>
                    <v-col cols=6>
                        {{teamname[event.team2_id]}}
                        <br>
                        {{event.team2_score}}
                        <br>
                      <v-btn>
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
        <v-btn
          outlined
          color="grey darken-2"
          @click="toggle"
          small
        >
          change
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
    <br>
    <br>

  </div>
</template>

<script>
import { getSchedule } from "@/api/tabs/sports.js"

  export default {
    name:"Schedule",
    data: () => ({
      teamname:{
        1: '강원', 2: '광주', 3: '대구', 4: '서울', 5: '성남', 6: '수원', 7: '수원FC', 8: '울산', 9: '인천', 10: '전북', 11: '제주', 12: '포항',
        23: '두산', 24: '롯데', 25: '삼성', 26: '키움', 27: '한화', 28: 'KIA', 29: 'KT', 30: 'LG', 31: 'NC', 32: 'SK',
        33: '담원', 34: 'DRX', 35: '젠지', 36: '아프리카', 37: 'T1', 38: 'KT', 39: '리브샌박', 40: '농심', 41: '한화', 42: '프레딧'
      },
      expandGame:[false,false,false,false,false,false,false,false,false,false],
      expandDaily:false,
      dialog:false,
      focus: new Date(),
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
        betDone: false, // (베팅 끝났는지)
        gameDone: false, // (게임 끝났는지)
      },
      selectedElement: null,
      selectedOpen: false,
      colors: ['grey darken-1','blue','red', 'green'],
      // events:[],
      events:[
        {
          id: 1,
          events_no: 3,
          name: "T1 vs 젠지", // (달력에 표시할 이름) 
          start: "2021-02-13", // ('YYYY-MM-DD')
          start_time: "17:00", // ("hh-mm")
          team1_id: 37, // (팀1 고유번호)
          team2_id: 35, // (팀2 고유번호)
          team1_score: 1, // (팀1 점수)
          team2_score: 2, // (팀2 점수)
          team1_user: [1],  // (팀1에 베팅한 유저 고유번호 리스트)
          team2_user: [], // (팀2에 베팅한 유저 고유번호 리스트)
          draw_user: [], // (무승부에 베팅한 유저 고유번호 리스트)
          betDone: true, // (베팅 끝났는지)
          gameDone: true, // (게임 끝났는지)
        }, 
        {
          id: 2,
          events_no: 1,
          name: "대구 vs 서울", // (달력에 표시할 이름) 
          start: "2021-02-18", // ('YYYY-MM-DD')
          start_time: "19:00", // ("hh-mm")
          team1_id: 3, // (팀1 고유번호)
          team2_id: 4, // (팀2 고유번호)
          team1_score: 0, // (팀1 점수)
          team2_score: 0, // (팀2 점수)
          team1_user: [],  // (팀1에 베팅한 유저 고유번호 리스트)
          team2_user: [], // (팀2에 베팅한 유저 고유번호 리스트)
          draw_user: [], // (무승부에 베팅한 유저 고유번호 리스트)
          betDone: false, // (베팅 끝났는지)
          gameDone: false, // (게임 끝났는지)
        },
        {
          id: 3,
          events_no: 2,
          name: "삼성 vs 롯데", // (달력에 표시할 이름) 
          start: "2021-02-19", // ('YYYY-MM-DD')
          start_time: "21:30", // ("hh-mm")
          team1_id: 25, // (팀1 고유번호)
          team2_id: 24, // (팀2 고유번호)
          team1_score: 0, // (팀1 점수)
          team2_score: 0, // (팀2 점수)
          team1_user: [1,2,3,4,5,6,7],  // (팀1에 베팅한 유저 고유번호 리스트)
          team2_user: [8,9,10,11,12,13], // (팀2에 베팅한 유저 고유번호 리스트)
          draw_user: [14,15], // (무승부에 베팅한 유저 고유번호 리스트)
          betDone: false, // (베팅 끝났는지)
          gameDone: false, // (게임 끝났는지)
        },
        {
          id: 4,
          events_no: 1,
          name: "강원 vs 수원FC", // (달력에 표시할 이름) 
          start: "2021-02-19", // ('YYYY-MM-DD')
          start_time: "21:30", // ("hh-mm")
          team1_id: 1, // (팀1 고유번호)
          team2_id: 7, // (팀2 고유번호)
          team1_score: 0, // (팀1 점수)
          team2_score: 0, // (팀2 점수)
          team1_user: [1,2,3,4,5,6,7],  // (팀1에 베팅한 유저 고유번호 리스트)
          team2_user: [8,9,10,11], // (팀2에 베팅한 유저 고유번호 리스트)
          draw_user: [14,15], // (무승부에 베팅한 유저 고유번호 리스트)
          betDone: false, // (베팅 끝났는지)
          gameDone: false, // (게임 끝났는지)
        },
        {
          id: 5,
          events_no: 3,
          name: "DRX vs 아프리카", // (달력에 표시할 이름) 
          start: "2021-02-19", // ('YYYY-MM-DD')
          start_time: "13:00", // ("hh-mm")
          team1_id: 34, // (팀1 고유번호)
          team2_id: 36, // (팀2 고유번호)
          team1_score: 0, // (팀1 점수)
          team2_score: 0, // (팀2 점수)
          team1_user: [1,2,3,4,5,6,7],  // (팀1에 베팅한 유저 고유번호 리스트)
          team2_user: [8,9,10,11,12,13,14,15], // (팀2에 베팅한 유저 고유번호 리스트)
          draw_user: [], // (무승부에 베팅한 유저 고유번호 리스트)
          betDone: true, // (베팅 끝났는지)
          gameDone: false, // (게임 끝났는지)
        },
      ],
    }),
    mounted () { 
      // this.$refs.calendar.checkChange()
      getSchedule(
        (res) => {
          console.log(res.data)
          this.events = res.data
        },
        (err) => {
          console.errer(err)
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
        this.expandDaily = true
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
      showEvent ({ nativeEvent, event }) {
        console.log(nativeEvent)
        console.log(event)
        const open = () => {
          this.selectedEvent = event
          this.selectedElement = nativeEvent.target
          setTimeout(() => {
            this.selectedOpen = true
          }, 10)
        }

        if (this.selectedOpen) {
          this.selectedOpen = false
          setTimeout(open, 10)
        } else {
          open()
        }

        nativeEvent.stopPropagation()
      },
      showEvents ({event}) {
        let ev = {
          date:event.start
        }
        this.viewDay(ev)
      },
      sortAsce : function() {
        this.event.sort(function(a,b){
          return a.start - b.start;
        })
      },
      closeDaily () {
        this.expandDaily = false
        this.resetShow()
      },
      filterDaily : function(focus) {
        console.log(focus)
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
    },
  }
</script>
