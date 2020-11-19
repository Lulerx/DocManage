<template>
  <el-container v-loading="loading">
    <el-header class="header">
      <div class="doc-attr"> <span style="color: red">*</span>文档名：
        <el-input v-model="article.title" placeholder="请输入文档名称" style="width: 400px" clearable></el-input>
      </div>
    </el-header>
    <el-main class="main">
      <div id="editor"> <!--Markdown编译器-->
        <mavon-editor style="height: 100%;width: 100%;" ref=md v-model="article.mdContent"></mavon-editor>
      </div>
      <div style="display: flex;align-items: center;margin-top: 15px">
        <el-button @click="">保存到草稿箱</el-button>
        <el-button type="primary" @click="saveBlog">发表文章</el-button>
      </div>
    </el-main>
  </el-container>
</template>

<script>

import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import {isNotNullORBlank} from '../utils/utils'

import {postRequest} from '../utils/api'

export default {
  name: 'Test'

  , components: {
    mavonEditor
  },
  methods: {
    saveBlog(state){
      if (!(isNotNullORBlank(this.article.title))){
        this.$message({type: 'error', message: '文档名不能为空呀！'});
        return;
      }
      let _this = this;
      postRequest('/doc/saveDoc',{
        title: _this.article.title,
        mdContent: _this.article.mdContent,
        htmlContent: _this.$refs.md.d_render,
        cid: '01',
        state: '1'
      }).then(resp => {
        if (resp.status === 200 && resp.data.status === 'success') {
          _this.$message({type: 'success', message: resp.data.msg})
        }
      }).catch((error) => {
        _this.$message({type: 'error', message: error});
      })

    }
  }
  ,data () {
    return {
      categories: [],
      tagInputVisible: false,
      tagValue: '',
      loading: false,
      from: '',
      article: {
        id: '-1',
        dynamicTags: [],
        title: '',
        mdContent: '',
        cid: ''
      }
    }
  }

}
</script>

<style scoped>
.doc-attr{
  display: flex;
  padding-top: 20px;
  align-items: center;
  color: #585f5f;
}

</style>
