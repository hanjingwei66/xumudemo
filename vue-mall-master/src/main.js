import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/'
import VueLazyload from 'vue-lazyload'
import infiniteScroll from 'vue-infinite-scroll'
import VueCookie from 'vue-cookie'
// import { userInfo } from './api'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(infiniteScroll)
Vue.use(VueCookie)
Vue.use(VueLazyload, {
  // preLoad: 1.3,
  // error: 'dist/error.png',
  loading: '/static/images/load.gif'
  // attempt: 1
})
Vue.config.productionTip = false

// 不需要登陆的页面 => 白名单
const whiteList = ['/home', '/goods', '/login', '/goodsDetails', 'index', 'sailingInfo', '/sailingInfo']
router.beforeEach(function (to, from, next) {
  // userInfo().then(res => {
  let res = {status: 1}
    // 没登录
  // eslint-disable-next-line no-constant-condition
  if (true) {
      // 白名单
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next('/login')
    }
  } else {
    store.commit('RECORD_USERINFO', {info: res.result})
    //  跳转到
    if (to.path === '/login') {
      next({path: '/'})
    }
    next()
  }
  // })
})
// Vue.prototype.$ELEMENT = { size: 'small', zIndex: 3000 }
Vue.use(ElementUI)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})
