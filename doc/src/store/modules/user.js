/**
 * user相关的vuex操作
 */
/*const getDefaultState = () => {
  return {
    userSession:{}
  }
}*/
import {SET_AUTHENTICATION} from '../mutation_type'

const state = {
  userSession: {}
}

const getters = {}

const mutations = {
  [SET_AUTHENTICATION] (state, payload) {
    state.userSession = payload
  }
}

const actions = {
  set_userSession ({commit}, payload) {
    commit(SET_AUTHENTICATION, payload)
  }
}

export default {
  //namespace: true,
  state,
  getters,
  mutations,
  actions
}
