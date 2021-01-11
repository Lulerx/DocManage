<template>
  <div class="main">
    <h1>用户管理页面</h1>
  </div>
</template>

<script>
export default {
  methods: {
  },

  data () {
    return {
      menuList: [
        {
          'id': 39,
          'title': '管理中心',
          'path': 'edit',
          'parent_id': null
        },
        {
          'id': 150,
          'title': '博客管理',
          'path': 'home',
          'parent_id': 39
        },
        {
          'id': 151,
          'title': '标题列表',
          'path': 'tags',
          'parent_id': 39
        },
        {
          'id': 152,
          'title': '用户管理',
          'path': 'userManage',
          'parent_id': 39
        },
      ]

    }
  }
}
</script>

<style scoped>
.main {
}

.left {
}

.el-input {
  width: 400px;
}

</style>
