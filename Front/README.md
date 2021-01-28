# front (중요)

## Project setup (프로젝트 세팅)
```
npm install
```

### Compiles and hot-reloads for development (서버돌리기)
```
npm run serve
```

### Compiles and minifies for production (모름)
```
npm run build
```

### Lints and fixes files (모름)
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).



-----



# 기본적인 것들

> 계절학기 vue 강의 참고하여 만들었으니 다시보기로 공부할 수도 있습니당
>
> 개발하면서 서로서로 물어보고 알려주면서 해요 ^^



- URL (route)

  아직 로그인 기능이 없어 기본적으로 다 갈 수 있게 해놓음

| path     | component | 설명                      |
| -------- | --------- | ------------------------- |
| /        | Login     | 로그인 페이지             |
| /signup  | Signup    | 회원가입 페이지           |
| /sports  | Sports    | 스포츠 탭 (명세서와 다름) |
| /feed    | Feed      | 피드 탭                   |
| /search  | Search    | 검색 탭                   |
| /chat    | Chat      | 채팅 탭                   |
| /profile | Profile   | 프로필 페이지             |

- `store 폴더`

  `index.js`와 같은 위치에 `actions.js`, `getters.js`, `mutations.js`를 두고

  `index.js`에서 import 하여 사용

  향후 vuex 이용시 참고

  

- `router 폴더`

  각 탭 별로 `routes`안에 경로를 설정해놨고

  `children` 이라는 변수를 두어 탭 안의 탭같이 그 페이지에서 더 들어가야하면 children 안에 경로를 설정하면 됩니다.

  이것도 계절학기 참고

  

- `views 폴더`

  `Tabs`와 `Users` 폴더 안에 .vue 파일이 있음

  `Tabs`에는 각종 탭들, `Users`에는 로그인, 회원가입, 프로필이 있음

  

- `components 폴더`

  각종 탭들과 로그인, 프로필의 컴포넌트를 따로 관리할 수 있도록 폴더를 두었음.

  탭에 새로운 페이지가 필요하면 거기에서 작성(탭 안의 탭 등)

  `common` 폴더 안에 `Navbar.vue`를 두고 각 탭에 넣어놨음.

  

- `config/index.js`

  API_BASE_URL = "http://localhost:8000" 으로 설정해놓음

  

- `api 폴더`

  `index.js`에 `axios` 요청을 할 수 있게 인스턴스를 생성하는 함수를 만들어놨고

  각 탭별로 폴더를 만들어 놨으니 향후 `axios` 요청을 할 때 이 함수를 이용하여 각자 따로 관리할 수 있게 해둠

  

- `Modal`(팝업)

  명세서에서 말한 `팝업` 기능은 vue bootstrap의 `Modals` 기능을 이용하면 편하니 참고하세용

  단, 동시에 두 개 이상의 모달을 띄우는건 아마 안될겁니다





---

### 스타일은 차근차근 맞춰갑시당..

### 공통부분에서 제가 빠트린게 있을수도 있으니 언제든지 말씀해주세욥

