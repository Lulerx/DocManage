<template>
<div class="main">
  <div style="margin: 10px 0;display: flex;justify-content: center">
    <el-input
      placeholder="默认展示部分用户，可以通过用户名搜索用户..."
      prefix-icon="el-icon-search"
      v-model="keywords" style="width: 400px" size="small">
    </el-input>
    <el-button type="primary" icon="el-icon-search" size="small" style="margin-left: 3px" @click="searchClick">搜索
    </el-button>
  </div>

  <div style="display: flex;justify-content: space-around;flex-wrap: wrap">
    <el-card style="width:330px;margin-top: 10px;" v-for="index in 8" :key="index">
      <div slot="header" style="text-align: left">
        <span>{{user.nickname}}</span>
        <el-button style="float: right; padding: 3px 0;color: #ff0509" type="text" icon="el-icon-delete"
                   @click="deleteUser(user.id)">删除
        </el-button>
      </div>
      <div>
        <div><img :src="user.userface" :alt="user.nickname" style="width: 70px;height: 70px"></div>
        <div style="text-align: left;color:#20a0ff;font-size: 12px;margin-top: 13px">
          <span>用户名:</span><span>{{user.username}}</span>
        </div>
        <div style="text-align: left;color:#20a0ff;font-size: 12px;margin-top: 13px">
          <span>电子邮箱:</span><span>{{user.email}}</span>
        </div>
        <div style="text-align: left;color:#20a0ff;font-size: 12px;margin-top: 13px">
          <span>注册时间:</span><span>{{user.regTime }}</span>
        </div>
        <div
          style="text-align: left;color:#20a0ff;font-size: 12px;margin-top: 13px;display: flex;align-items: center">
          <span>用户状态:</span>
          <el-switch
            v-model="user.enabled"
            active-text="启用"
            active-color="#13ce66"
            @change="enabledChange(user.enabled,user.id,index)"
            inactive-text="禁用" style="font-size: 12px">
          </el-switch>
        </div>
        <div style="text-align: left;color:#20a0ff;font-size: 12px;margin-top: 13px">
          <span>用户角色:</span>
          <el-tag
            v-for="role in user.roles"
            :key="role.id"
            size="mini"
            style="margin-right: 8px"
            type="success">
            {{role.name}}
          </el-tag>
          <el-popover
            placement="right"
            title="角色列表"
            width="200"
            :key="index+''+user.id"
            @hide="saveRoles(user.id,index)"
            trigger="click" >
            <el-select v-model="roles" :key="user.id" multiple placeholder="请选择" size="mini">
              <el-option value="">超级管理员</el-option>
              <el-option value="2">测试用户</el-option>
            </el-select>
            <el-button type="text" icon="el-icon-more" style="padding-top: 0px" slot="reference"
                       @click="showRole(user.roles,user.id,index)"></el-button>
          </el-popover>
        </div>
      </div>
    </el-card>
  </div>


</div>
</template>

<script>
export default {
  methods: {
  },

  data() {
    return {
      dialogFormVisible: false,
      formLabelWidth: '120px',
      keywords: '',
      roles: '12',
      user: {
        id: '001',
        username: 'zhangsan',
        password: '',
        nickname: '张三',
        enabled: true,
        email: 'zhangsan@163.com',
        userface: 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3782128483,794367969&fm=26&gp=0.jpg',
        regTime:'123'
      },

    };
    }
}
</script>

<style scoped>
.main{
}

.left{
}
.el-input{
  width: 400px;
}

</style>
