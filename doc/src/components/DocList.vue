<template>
  <el-container class="article_list">
    <el-main class="main">
      <el-tabs v-model="activeName" @tab-click="handleClick" type="card">
        <el-tab-pane label="全部文章" name="all">
          <doc_table state="-1" :showEdit="false" :showDelete="false" :showRestore="false" :activeName="activeName"></doc_table>
        </el-tab-pane>
        <el-tab-pane label="已发表" name="post">
          <doc_table state="1" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></doc_table>
        </el-tab-pane>
        <el-tab-pane label="草稿箱" name="draft">
          <doc_table state="0" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></doc_table>
        </el-tab-pane>
        <el-tab-pane label="回收站" name="dustbin">
          <doc_table state="2" :showEdit="false" :showDelete="true" :showRestore="true" :activeName="activeName"></doc_table>
        </el-tab-pane>
        <el-tab-pane label="博客管理" name="blogManage" v-if="isAdmin">
          <doc_table state="-2" :showEdit="false" :showDelete="true" :showRestore="false" :activeName="activeName"></doc_table>
        </el-tab-pane>
        <el-tab-pane label="博客配置" name="blogConfig">
<!--          <blog_cfg></blog_cfg>-->
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>

<script>
import DocTable from './DocTable'
export default {
name: "DocList",
  /*mounted: function () {
    var _this = this;
    getRequest("/isAdmin").then(resp=> {
      if (resp.status == 200) {
        _this.isAdmin = resp.data;
      }
    })
  },*/
  data() {
    return {
      activeName: 'post',
      isAdmin: false
    };
  },
  methods: {
    handleClick(tab, event) {
//        console.log(tab, event);
    }
  },
  components: {
    'doc_table': DocTable,
    // 'blog_cfg': BlogCfg
  }
}
</script>

<style scoped>
.article_list > .header {
  background-color: #ececec;
  margin-top: 10px;
  padding-left: 5px;
  display: flex;
  justify-content: flex-start;
}

.article_list > .main {
  /*justify-content: flex-start;*/
  display: flex;
  flex-direction: column;
  padding-left: 0;
  background-color: #fff;
  padding-top: 0;
  margin-top: 8px;
}
</style>
