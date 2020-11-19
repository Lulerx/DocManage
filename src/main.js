import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import 'font-awesome/css/font-awesome.min.css'
import './utils/filter_utils.js'


Vue.config.productionTip = false
Vue.use(ElementUI);
// Vue.use(Element, { size: 'small', zIndex: 3000 });
/* eslint-disable no-new */

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
