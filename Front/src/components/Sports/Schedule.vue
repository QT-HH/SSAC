<template>
  <div>
    <v-sheet>
      <v-btn
        icon
        class="ma-2"
        @click="prev"
        color="blue"
      >
        <v-icon>mdi-chevron-left</v-icon>
      </v-btn>
      <v-btn
        icon
        class="ma-2"
        @click="next"
      >
        <v-icon>mdi-chevron-right</v-icon>
      </v-btn>
    </v-sheet>
    <v-sheet height="600">
      <v-calendar
        ref="calendar"
        v-model="focus"
        :events="event"
      ></v-calendar>
    </v-sheet>

    <br>
    <br>

      
  </div>
</template>

<script>
  export default {
    name:"Schedule",
    data: () => ({
      focus: '',
      type: 'month',
      typeToLabel: {
        month: 'Month',
        week: 'Week',
        day: 'Day',
        '4day': '4 Days',
      },
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      events: [],
      colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
      names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
      event:[
        {
          name:"T1",
          start: '2021-02-18 18:30',
          end: '2021-02-20 19:30',
          // color: "green",
          timed: true,
        },
        {
          name:"test",
          start: new Date(),
          end: new Date(),
          // color: "green",
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
        {
          name:"test",
          start: new Date(),
          end: new Date(),
          color: "green",
          timed: true,
        },
      ],
    }),
    mounted () {
      this.$refs.calendar.checkChange()
    },
    methods: {
      viewDay ({ date }) {
        this.focus = date
        console.log(date)
        // this.type = 'day'
      },
      getEventColor (event) {
        return event.color
      },
      setToday () {
        this.focus = ''
      }, 
      prev () {
        console.log(this.focus)
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
      updateRange ({ start, end }) {
        const events = []

        const min = new Date(`${start.date}T00:00:00`)
        const max = new Date(`${end.date}T23:59:59`)
        const days = (max.getTime() - min.getTime()) / 86400000
        const eventCount = this.rnd(days, days + 20)

        for (let i = 0; i < eventCount; i++) {
          const allDay = this.rnd(0, 3) === 0
          const firstTimestamp = this.rnd(min.getTime(), max.getTime())
          const first = new Date(firstTimestamp - (firstTimestamp % 900000))
          const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000
          const second = new Date(first.getTime() + secondTimestamp)

          events.push({
            name: this.names[this.rnd(0, this.names.length - 1)],
            start: first,
            end: second,
            color: this.colors[this.rnd(0, this.colors.length - 1)],
            timed: !allDay,
          })
        }

        this.events = events
        console.log(this.events)
      },
      rnd (a, b) {
        return Math.floor((b - a + 1) * Math.random()) + a
      },
    },
  }
</script>
