<template>
<div>
  <div class="from-class">
    <el-form :model="user" label-width="100px" style="margin-top: 50px">
      <el-form-item label="用户名" :label-width="formLabelWidth" >
        <el-input v-model="user.username" :disabled="true" ></el-input>
      </el-form-item>
      <el-form-item label="昵称" :label-width="formLabelWidth">
        <el-input v-model="user.nickname"></el-input>
      </el-form-item>
      <el-form-item prop="email" label="邮箱" :label-width="formLabelWidth"
                    :rules="[
      { required: true, message: '请输入邮箱地址', trigger: 'blur' },  //触发方式：blur失去焦点  change数据发生改变
      { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }]">
        <el-input v-model="user.email"></el-input>
      </el-form-item>
    </el-form>
  </div>

  <el-button type="primary" @click="dialogFormVisible = true" class="left">添加管理员</el-button>

  <el-dialog title="添加管理员" :visible.sync="dialogFormVisible">
    <el-form :model="form" label-width="100px">
      <el-form-item label="用户名" :label-width="formLabelWidth" prop="username" :rules="[{required: true, message: '用户名不能为空'}]">
        <el-input v-model="form.username" placeholder="用户名是登录的唯一凭证" ></el-input>
      </el-form-item>
      <el-form-item prop="password" label="密码" :label-width="formLabelWidth" :rules="[{required: true, message: '密码不能为空', trigger:'blur'}]">
        <el-input v-model="form.password" placeholder="请输入密码" show-password></el-input>
      </el-form-item>
      <el-form-item label="昵称" :label-width="formLabelWidth">
        <el-input v-model="form.nickname"></el-input>
      </el-form-item>
      <el-form-item label="用户状态" :label-width="formLabelWidth">
        <el-switch
          v-model="form.enabled"
          active-text="启用"
          active-color="#13ce66"
          inactive-text="禁用" style="font-size: 12px">
        </el-switch>
      </el-form-item>
      <el-form-item prop="email" label="邮箱" :label-width="formLabelWidth"
                    :rules="[
      { required: true, message: '请输入邮箱地址', trigger: 'blur' },  //触发方式：blur失去焦点  change数据发生改变
      { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }]">
        <el-input v-model="form.email"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="clickTrue">确 定</el-button>
    </div>
  </el-dialog>

</div>
</template>

<script>
export default {

  methods: {
    clickTrue(){
      this.$message({type:'success', message:"点击了确定按钮"});
      this.dialogFormVisible = false;
    }
  },

  data() {
    return {
      dialogFormVisible: false,
      formLabelWidth: '120px',
      user: {
        username: 'lisi',
        password: '',
        nickname: 'Tom',
        enabled: true,
        email: 'lisi@163.com',
        userface: '',
      },
      form: {
        username: '',
        password: '',
        nickname: 'Tom',
        enabled: true,
        email: '',
        userface: '',
      },

    };
  }
}
</script>

<style scoped>
.el-input{
  width: 400px;
}
.from-class{
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  margin: 50px 0;
  padding: 50px 0;
  width: 600px;
}
</style>
