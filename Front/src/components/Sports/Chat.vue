<template>
  <div>
    <template>
      <v-row width="max-content">
        <v-col width="max-content">
          <v-card style="height: 545px" width="max-content">
            <v-list two-line>
              <br>
              <v-list-item v-for="item in items" :key="item.title" link>

                <v-list-item-avatar>
                  <v-icon :class="[item.iconClass]">
                    {{ item.icon }}
                  </v-icon>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title>{{ item.home }} vs {{item.away}}</v-list-item-title>
                  <v-list-item-subtitle>
                    <v-chip v-for="(participant, idx) in item.participants" :key="idx" class="ma-2" x-small>
                      {{ participant }}
                    </v-chip>
                  </v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-action>
                  <v-btn icon>
                    <v-icon color="grey lighten-1">
                      mdi-information
                    </v-icon>
                  </v-btn>
                </v-list-item-action>


              </v-list-item>


              <v-list-item v-for="item in items2" :key="item.title" link>
                <v-list-item-avatar>
                  <v-icon :class="[item.iconClass]">
                    {{ item.icon }}
                  </v-icon>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title>{{ item.home }} vs {{item.away}}</v-list-item-title>
                  <v-list-item-subtitle>
                    <v-chip v-for="(participant, idx) in item.participants" :key="idx" class="ma-2" x-small>
                      {{ participant }}
                    </v-chip>
                  </v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-action>
                  <v-btn icon ripple>
                    <v-icon color="grey lighten-1">
                      mdi-information
                    </v-icon>
                  </v-btn>
                </v-list-item-action>
              </v-list-item>
            </v-list>

            <v-fab-transition>
              <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn color="indigo accent-2" dark absolute bottom right v-bind="attrs" v-on="on">
                    <v-icon>mdi-plus</v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-toolbar dark color="indigo accent-2">
                    <v-btn icon dark @click="dialog = false">
                      <v-icon>mdi-close</v-icon>
                    </v-btn>
                    <v-toolbar-title>채팅 만들기</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-toolbar-items>
                      <!--  -->
                      <v-btn dark text @click="dialog = false">
                        생성
                      </v-btn>
                      <!--  -->
                    </v-toolbar-items>
                  </v-toolbar>
                  <v-list three-line subheader>
                    <v-subheader>대상 경기</v-subheader>
                    <v-list-item>
                      <v-list-item-content >
                        <v-container v-for="(item, idx) in items3" :key="idx">
                        <v-card class="text-center align-center justify-center pa-4 mx-auto" link>
                          <v-card-title class="justify-center">{{item.home}} vs {{item.away}}</v-card-title>
                          <v-card-subtitle class="justify-center"> {{item.place}} </v-card-subtitle>
                        </v-card>
                        </v-container>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list>
                  <v-divider></v-divider>
                  <v-list three-line subheader>
                    <v-subheader>초대할 멤버</v-subheader>
                    <v-list-item>
                      <v-container v-for="(member, idx) in members" :key="idx"  >
                        <v-list-item-content>
                          <v-chip dark class="justify-center indigo accent-2">{{member}}</v-chip>
                      </v-list-item-content>
                      </v-container>
                    </v-list-item>
                  </v-list>
                </v-card>
              </v-dialog>
            </v-fab-transition>

          </v-card>
        </v-col>
      </v-row>
    </template>
  </div>
</template>

<script>
export default {
  name:"Chat",
  data () {
      return {
        dialog: false,
        notifications: false,
        sound: true,
        widgets: false,
        items: [
          { icon: 'mdi-clipboard-text', iconClass: 'amber white--text', home: '대구FC', away: 'T1', participants: ['노정', '규태', '경연'] },
          { icon: 'mdi-gesture-tap-button', iconClass: 'blue white--text', home: '삼성 라이온즈', away: 'GEN.G', participants: ['노정', '규태', '경연'] },
        ],
        items2: [
          { icon: 'mdi-clipboard-text', iconClass: 'blue white--text', home: '전북 현대', away: '두산 베어스', participants: ['노정', '규태', '경연'] },
          { icon: 'mdi-gesture-tap-button', iconClass: 'amber white--text', home: 'DRX', away: 'FC서울', participants: ['노정', '규태', '경연'] },
        ],
        // 여기는 채팅만드는 팀
        items3: [
          { icon: 'mdi-clipboard-text', iconClass: 'blue white--text', home: 'SK 와이번스', away: 'LG 트윈스', place: '런던, 영국'},
          { icon: 'mdi-gesture-tap-button', iconClass: 'amber white--text', home: '강원FC', away: '수원FC', place: '가나, 아프리카'},
        ],
        members: [ '경연', '규태', '은지', '지원']
      }
    },
}
</script>

<style>
.v-list-item {
  border-bottom: 1px;
  border-bottom-color: black

}
</style>