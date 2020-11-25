/*
  vuex最核心的管理对象store
 */
import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'

Vue.use(Vuex)
// 把modules文件下的 js文件返回出来
// https://webpack.js.org/guides/dependency-management/#requirecontext
/*const modulesFiles = require.context('./modules', true, /\.js$/)

// you do not need `import app from './modules/app'`
// it will auto require all vuex module from modules file
const modules = modulesFiles.keys().reduce((modules, modulePath) => {
  // set './app.js' => 'app'
  const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, '$1')
  const value = modulesFiles(modulePath)
  modules[moduleName] = value.default
  return modules
}, {})*/

export default new Vuex.Store({
  modules: {
    user
  }
})

