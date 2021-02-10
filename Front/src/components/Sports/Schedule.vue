<template>
  <div class="mt-1">
    <v-sheet>
        <h1>
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

            @click="showEvents({event})"
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
                {{event.teams[0]}} vs {{event.teams[1]}}
              </v-card-title>

              <v-card-subtitle>
                {{event.start}}
              </v-card-subtitle>
            </v-card>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
    </v-sheet>
    <v-expand-transition>
      <v-card
        v-show="expandDaily"
        width="100%"
        class="px-2 blue"
        elevation="2"
      >
        <v-card-title>
          오늘의 경기 ( {{ focus }} )
        </v-card-title>
        <v-list-item
          v-for="(item, i) in filterDaily(focus)"
          :key="i"
        >
          <!-- <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon> -->
          <v-list-item-content>
            <v-list-item-title class="text--center" @click="changeShow(i)">
              {{item.teams[0]}} vs {{item.teams[1]}}
            </v-list-item-title>
             <v-expand-transition>
              <v-card
                v-show="expandGame[i]"
              >
                <v-card-text>
                  <v-row>
                    <v-col>
                      <v-btn>
                        투표!
                      </v-btn>
                      <v-btn>
                        투표!
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
        color="blue"
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

    <v-btn @click="dialog = !dialog">
      asdfqwef
    </v-btn>
    <v-dialog
      v-model="dialog"
      width="500"
    >

      <v-card>
        <v-card-title class="headline grey lighten-2">
          {{ selectedEvent.teams[0] }} vs {{ selectedEvent.teams[1] }} 
        </v-card-title>

        <v-card-text>
          한판뜨자
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            @click="dialog = false"
          >
            닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <br>
    <br>
    <br>
    <br>

  </div>
</template>

<script>
  export default {
    name:"Schedule",
    data: () => ({
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
        event_no: Number,
        name: String,
        start: String,
        end: Date,
        done: Boolean,
        result: [0,0],
        teams: ["",""],
      },
      selectedElement: null,
      selectedOpen: false,
      colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
      events:[
        {
          teams:["T1","Gen.G"],
          name:"T1",
          start: '2021-02-18 18:30',
          end: '2021-02-18 19:30',
          // color: "green",
          // timed: true,
        },
        {
          teams:["대구","포항"],
          name:"test",
          start: '2021-02-08 18:30',
          end: '2021-02-08 19:30',
          // color: "green",
          // timed: false,
          eventParsed: {
            category:"test01"
          }
        },
        {
          teams:["KT","SK"],
          name:"test",
          start: '2021-02-16 18:30', 
          // end: '2021-02-16 19:30',
          color: "green",
          // timed: true,
        },
        {
          teams:["T1","Gen.G"],
          name:"test",
          start: '2021-02-16 18:30',
          // end: new Date(),
          color: "green",
          timed: true,
        },
        {
          teams:["T1","Gen.G"],
          name:"test",
          start: '2021-02-16 18:30',
          // end: new Date(),
          color: "green",
          timed: true,
        },
      ],
    }),
    // mounted () { 
    //   this.$refs.calendar.checkChange()
    // },
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
        return event.color
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
      showEvents (aa) {
        console.log(aa)
        // this.selectedEvent = event
        // this.dialog = !this.dialog
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
      }
    },
  }
</script>
