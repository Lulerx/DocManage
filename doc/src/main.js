import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import store from './store'
import 'element-ui/lib/theme-chalk/index.css'

import 'font-awesome/css/font-awesome.min.css'
import './utils/filter_utils.js'


/*
let isFetchRemote = true;

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
  const username = sessionStorage.getItem('username');
  /!*if (!username && to.path !== '/'){
    next({path: '/'});
  }*!/
  /!*else if (isFetchRemote && to.path !== '/') {*!/
    //获取路由菜单
  console.log(to)
  console.log(from)
  console.log(next)
  if (isFetchRemote || to.path === from.path){
    store.dispatch('setMenu')
    next()
    // next({ path: to.path })
    isFetchRemote = false;
  }
  next()
})
*/

Vue.config.productionTip = false
Vue.use(ElementUI);
// Vue.use(Element, { size: 'small', zIndex: 3000 });
/* eslint-disable no-new */



new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
