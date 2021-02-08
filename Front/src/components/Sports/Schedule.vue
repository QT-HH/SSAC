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
            color="white"
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
                {{event.name}}
              </v-card-title>

              <v-card-subtitle>
                {{event.start}}
              </v-card-subtitle>
            </v-card>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
    </v-sheet>

    <br>
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
        @click:event="showEvent"
        @click:more="viewDay"
        @click:date="viewDay"
        :type="type[tog]"
      ></v-calendar>
      <v-menu
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
          <v-toolbar
            :color="selectedEvent.color"
            dark
          >
            <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn icon>
              <v-icon>mdi-heart</v-icon>
            </v-btn>
            <v-btn icon>
              <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
          </v-toolbar>

          <v-card-text>
            
          </v-card-text>
          <v-card-actions>
            <v-btn
              text
              color="secondary"
              @click="selectedOpen = false"
            >
              닫기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-menu>
    </v-sheet>

    <br>
    <br>

      
  </div>
</template>

<script>
  export default {
    name:"Schedule",
    data: () => ({
      focus: new Date(),
      tog:true,
      type: {
        true:"month",
        false:"day",
      },
      selectedEvent: {},
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
          name:"test",
          start: '2021-02-16 18:30', 
          // end: '2021-02-16 19:30',
          color: "green",
          // timed: true,
        },
        {
          name:"test",
          start: new Date(),
          end: new Date(),
          color: "green",
          timed: true,
        },
        {
          name:"test",
          start: new Date(),
          end: new Date(),
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
      viewDay ({ date }) {
        this.focus = date
        this.tog = false
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
      sortAsce : function() {
        this.event.sort(function(a,b){
          return a.start - b.start;
        })
      },
    },
  }
</script>
