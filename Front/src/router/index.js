import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/Users/Login.vue";
import Signup from "../views/Users/Signup.vue";
import Profile from "../views/Users/Profile.vue";
import ProfileEdit from "../views/Users/ProfileEdit.vue";
import Tabs from "../views/Tabs/Tabs.vue";
import ChatRoom from '@/components/chat/chatRoom.vue';

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
    children: [

    ]
  },
  {
    path: "/signup",
    name: "Signup",
    component: Signup,
    children: [
      {
        path: "/signup",
        name: "Signup1",
        component: () => import("../components/Signup/Signup.vue")
      },
      {
        path: "/signup/selectTeam",
        name: "SelectTeam",
        component: () => import("../components/Signup/MyTeam.vue")
      },
      {
        path: "/signup/welcome",
        name: "Welcome",
        component: () => import("../components/Signup/Welcome.vue")
      },
    ]
  },
  {
    path: "/profile",
    name: "Profile",
    component: Profile,
    children: [
      {
        path: "/profile",
        name: "Article",
        component: () => import("@/components/profile/Article.vue")
      },
      {
        path: "/profile/myteam",
        name: "MyTeam",
        component: () => import("@/components/profile/MyTeam.vue"),
        children: [

          {
            path: "/profile/mymyteam",
            name: "MyMyTeam",
            component: () => import("@/components/profile/MyMyTeam.vue")            
          },
          {
            path: "/profile/newteam",
            name: "NewTeam",
            component: () => import("@//components/profile/NewTeam.vue"),
          }
        ]
      },
    ]
  },
  {
    path: "/tabs",
    name: "Tabs",
    component: Tabs,
    children: [
      {
        path: "/tabs",
        name: "Sports",
        component: () => import("../views/Tabs/Sports.vue"),
        children: [
          {
            path: "/tabs",
            name: "SportsSchedule",
            component: () => import("../components/Sports/Schedule.vue")
          },
          {
            path: "/tabs/ranking",
            name: "SportsRanking",
            component: () => import("../components/Sports/Ranking.vue")
          },
          {
            path: "/tabs/news",
            name: "SportsNews",
            component: () => import("../components/Sports/News.vue")
          },
          {
            path: "/tabs/chat",
            name: "SportsChat",
            component: () => import("../components/Sports/Chat.vue"),
            children: [
        
            ]
          },
        ]
      },
      {
        path: "/tabs/feed",
        name: "Feed",
        component: () => import("../views/Tabs/Feed.vue"),
        children: [
    
        ]
      },
      {
        path: "/tabs/search",
        name: "Search",
        component: () => import("../views/Tabs/Search.vue"),
        children: [
          {
            path: "/tabs/search",
            name: "realSearch",
            component: () => import("@/components/Search/realSearch.vue")
          },
          {
            path: "/tabs/search/friendsteam",
            name: "FriendsTeam",
            component: () => import("@/components/Search/FriendsTeam.vue")
          },
          {
            path: "/tabs/search/recommend",
            name: "Recommend",
            component: () => import("@/components/Search/Recommend.vue"),
            children: [
              {
                path: "/tabs/search/recommend",
                name: "Question1",
                component: () => import("@/components/Search/Question1.vue")
              },
              {
                path: "/tabs/search/recommend/question2",
                name: "Question2",
                component: () => import("@/components/Search/Question2.vue")
              },
              {
                path: "/tabs/search/recommend/question3",
                name: "Question3",
                component: () => import("@/components/Search/Question3.vue")
              },
              {
                path: "/tabs/search/recommend/question4",
                name: "Question4",
                component: () => import("@/components/Search/Question4.vue")
              },                        
            ]
          },
        ]
      },

    ]
  },
  {
    path: "/profile/edit",
    name: "ProfileEdit",
    component: ProfileEdit,
    children: [

    ]
  },
  {
    path: '/tabs/chat-room/:id',
    name: 'ChatRoom',
    component: ChatRoom,
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
