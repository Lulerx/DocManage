import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Home from '../components/Home'
import store from '../store'

Vue.use(Router)

const routes = [
  {
    path: '/',
    name: '登录',
    hidden: true,
    component: Login
  }, {
    path: '/home',
    name: '首页',
    component: Home,
    hidden: true
  },
]

let router =  new Router({
  // mode: 'history', /* history 怕F5刷新*/
  routes: routes,
  linkActiveClass: 'active'
})

let isFetchRemote = true

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
  const username = sessionStorage.getItem('username')
  /*if (!username && to.path !== '/'){
    next({path: '/'});
  }*/
  /*else if (isFetchRemote && to.path !== '/') {*/
  //获取路由菜单
  if (isFetchRemote) {
    store.dispatch('setMenu')
    next()
    // next({ path: to.path })
    isFetchRemote = false
    console.log("1111")
  }
  next()
})


export default router;
