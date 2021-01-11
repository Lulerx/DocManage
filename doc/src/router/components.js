// components.js  所以的页面资源

const docTable = ()=> import('../components/DocTable.vue')
const edit = ()=> import('../components/Edit.vue')
const home = ()=> import('../components/Home.vue')
const login = ()=> import('../components/Login.vue')
const tags = ()=> import('../components/Tags.vue')
const userIndex = ()=> import('../components/UserIndex.vue')
const userManage = ()=> import('../components/UserManage.vue')
const docDetail = ()=> import('../components/DocDetail.vue')
const docList = ()=> import('../components/DocList.vue')

export default {
  docTable,
  edit,
  home,
  login,
  tags,
  userIndex,
  userManage,
  docDetail,
  docList,
}
