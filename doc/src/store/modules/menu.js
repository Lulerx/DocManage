/**
 * 左侧导航相关的vuex操作
 */
import router from '../../router'
import {SET_TOKEN, GET_MENUDATA} from '../mutation_type'
import {getRequest} from '../../utils/api'
import routeMap from '../../router/components'

const state = {
  token: localStorage.getItem('token')
}

const getters = {
  getMenu(state){
    return JSON.parse(JSON.stringify(state.token))
  }
}

const mutations = {
  [SET_TOKEN](state,payload){
    state.token = payload;
  },

  [GET_MENUDATA](state){
    return state.token;
  }
}

const actions = {
  async setMenu ({commit}){
    let data = [];
    await getRequest('/nav/getAllNavs').then(resp =>{
      const menu = resp.data.data;
      menu.forEach(route => {
        if (!route.children){
          let item = {
            path: '/home',
            component: routeMap['home'],
            hidden:!route.enabled,
            children: []
          }
          //向数组中添加新元素
          item.children.push({
            path: route.path,
            name: route.title,
            component: routeMap[route.component],
            meta: {
              title: route.title,
            }
          })
          data.push(item)
        }
        if (route.children && route.children.length) {
          let item = {
            name: route.title,
            path: '/home',
            component: routeMap['home'],
            children: []
          }
          route.children.forEach(route => {
            item.children.push({
              path: route.path,
              name: route.title,
              hidden:!route.enabled,
              component: routeMap[route.component],
              meta: {
                title: route.title,
              }
            })
          })
          data.push(item)
        }
      })
    })
    router.options.routes = router.options.routes.concat(data);
    router.addRoutes(data);
    commit(SET_TOKEN, true);
  },
}

export default {
  //namespace: true,
  state,
  getters,
  mutations,
  actions
}
