<template>
  <div id="login">
    <el-form ref="user" :model="user" label-width="80px">
      <h3 style="text-align: center">用户登录</h3><hr style="opacity: 0.5"/>
      <el-form-item prop="username" label="用户名:" :rules="[{required: true, message: '用户名不能为空'}]">
        <el-input v-model="user.username" placeholder="请输入用户名"></el-input>
      </el-form-item>

      <el-form-item prop="password" label="密码:" :rules="[{required: true, message: '密码不能为空', trigger:'blur'}]">
        <el-input v-model="user.password" placeholder="请输入密码" show-password></el-input>
      </el-form-item>

      <!--<el-form-item prop="email" label="邮箱"
        :rules="[
      { required: true, message: '请输入邮箱地址', trigger: 'blur' },  //触发方式：blur失去焦点  change数据发生改变
      { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
    ]"
      >
        <el-input v-model="user.email"></el-input>
      </el-form-item>-->

      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="resetForm('user')">重置</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>

import {postRequest} from '../utils/api'

export default {
  data () {
    return {
      user: {
        username: '',
        password: '',
        email: '',
      }
    }
  },

  methods: {
    onSubmit() {
      postRequest("/login",{
        username: this.user.username,
        password: this.user.password
      }).then((resp) =>{
        if (resp.data.code === 200){
          this.$message({type:'success',message:resp.data.message})
          this.$router.push({path: '/home/user'})
        }else if (resp.data.code === 401){
          this.$message({type:'error',message:resp.data.message})
        }
      }).catch((error)=>{
        if (error.response.status === 401){
          this.$message({type:'error',message:'用户名或密码不正确哦'})
        }else {
          this.$message({type:'error',message:'服务器出错啦~'})
        }

      })
    },
    resetForm(formName){
      this.$refs[formName].resetFields();
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

#login{
  display: block;
  width: 25rem;
  margin: 3rem auto 0;
  padding: 0 2.5rem;
  border: 1px solid #bccbcc;
  border-radius: 2rem;
  box-shadow: 8px 8px 3px #888888;
}

</style>
