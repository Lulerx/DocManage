import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Home from '../components/Home'
import Edit from '../components/Edit'
import Test2 from '@/components/Test2'
import DocList from '../components/DocList'
import UserManage from '../components/UserManage'
import UserIndex from '../components/UserIndex'

Vue.use(Router)

export default new Router({
  routes: [
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
    },{
      path: '/home',
      component: Home,
      children: [
        {
          path: 'edit',
          iconCls: 'fa fa-pencil',
          name: '编辑文档',
          component: Edit,
          meta: {
            keepAlive: true
          }
        }
      ]
    },{
      path: '/home',
      component: Home,
      iconCls: 'fa fa-folder-open-o',
      name: '文档管理',
      children: [
        {
          path: 'docList',
          iconCls: 'fa fa-sitemap',
          name: '文档列表',
          component: DocList
        }
        ,{
          path: 'test2',
          iconCls: 'fa fa-bookmark-o',
          name: '文档分类',
          component: Test2
        }
      ]
    },{
      path: '/home',
      component: Home,
      children: [
        {
          path: 'user',
          iconCls: 'fa fa-user',
          name: '用户管理',
          component: UserManage,
        }
      ]
    },{
      path: '/home',
      component: Home,
      hidden: true,
      children: [
        {
          path: 'userIndex',
          iconCls: 'fa fa-user',
          name: '个人主页',
          component: UserIndex,
        }
      ]
    }

  ]
})