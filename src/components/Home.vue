<template>
  <el-container class="home_container">
    <el-header class="home-el-header">
      <div class="home_title">文档管理系统</div>
      <div class="home_userinfoContainer">
        <span class="el-dropdown-link home_userinfo" style="margin-right: 10px">
            <el-avatar :src="currentUserFace"></el-avatar>
          </span>
        <el-dropdown @command="handleCommand">
          <span class="el-dropdown-link home_userinfo">
            {{ currentUserName }}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="sysMsg">系统消息</el-dropdown-item>
            <el-dropdown-item command="MyArticle">我的文章</el-dropdown-item>
            <el-dropdown-item command="MyHome">个人主页</el-dropdown-item>
            <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <el-aside class="home-el-aside" width="200px">
        <el-menu
          default-active="0"
          class="el-menu-vertical-demo"
          background-color="#ECECEC"
          style="" router>
          <template v-for="(item,index) in this.$router.options.routes" v-if="!item.hidden">
            <el-submenu :index="index+''" v-if="item.children.length>1" :key="index">
              <template slot="title">
                <i :class="item.iconCls"></i>
                <span>{{ item.name }}</span>
              </template>
              <el-menu-item v-for="child in item.children" v-if="!child.hidden" :index="child.path" :key="child.path">
                <i :class="child.iconCls"></i>
                <span slot="title">{{ child.name }}</span>
              </el-menu-item>
            </el-submenu>
            <template v-else>
              <el-menu-item :index="item.children[0].path">
                <i :class="item.children[0].iconCls"></i>
                <span slot="title">{{ item.children[0].name }}</span>
              </el-menu-item>
            </template>
          </template>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main class="home-el-main">
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item id="toPage" v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
          </el-breadcrumb>
<!--          <router-view></router-view>-->
          <keep-alive>
            <router-view v-if="this.$route.meta.keepAlive"></router-view>
          </keep-alive>
          <router-view v-if="!this.$route.meta.keepAlive"></router-view>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>
<script>

import {getRequest} from '../utils/api'

export default{

  mounted : function (){
    let _this = this;
    getRequest('/user/getUserMessage').then((resp)=>{
      _this.currentUserName = resp.data.data.nickname;
      _this.currentUserFace = resp.data.data.userface;
    })
  },

  methods: {
    handleCommand(command) {
      var _this = this;
      if (command == 'logout') {
        this.$confirm('真的要注销登录吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          getRequest("/logout")
          _this.currentUserName = '游客';
          _this.$router.replace({path: '/'});
        }, function () {
          //取消
        })
      }else if (command == 'MyHome') {
        this.$router.push('/home/userIndex')
      }
    }
  },

  data(){
    return {
      currentUserName: '游民',
      currentUserFace:''
    }
  }
}
</script>
<style>
.home_container {
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
}

.home-el-header {
  background-color: #3985ef;
  color: #333;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.home-el-aside {
  background-color: #ECECEC;
}

.home-el-main {
  background-color: #fff;
  color: #000;
}

.home_title {
  color: #fff;
  font-size: 22px;
  display: inline;
}

.home_userinfo {
  color: #fff;
  cursor: pointer;
}

.home_userinfoContainer {
  display: flex;
  margin-right: 20px;
  align-items: center;
}
</style>
