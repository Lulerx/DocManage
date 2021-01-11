<template>
  <el-container v-loading="loading">
    <el-header class="header">
      <div class="doc-attr"><span style="color: red">*</span>文档名：
        <el-input v-model="document.title" placeholder="请输入文档名称" style="width: 400px" clearable></el-input>
        <el-select v-model="document.cid" :placeholder=tagValue style="margin-left: 10px">
          <el-option v-for="item in categories" :key="item.id"
            :label="item.tagName"
            :value="item.id">
          </el-option>
        </el-select>
      </div>
    </el-header>
    <el-main class="main">
      <div id="editor"> <!--Markdown编译器-->
        <mavon-editor style="height: 100%;width: 100%;" ref=md v-model="document.mdContent"></mavon-editor>
      </div>
      <div style="display: flex;align-items: center;margin-top: 15px">
        <el-button @click="saveBlog(0)">保存到草稿箱</el-button>
        <el-button type="primary" @click="saveBlog(1)">发表文章</el-button>
      </div>
    </el-main>
  </el-container>
</template>

<script>

import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import {isNotNullORBlank} from '../utils/utils'

import {postRequest} from '../utils/api'
import {getRequest} from '../utils/api'

export default {
  name: 'Test'

  , components: {
    mavonEditor
  },
  mounted: function () {
    let _this = this;
    _this.getTags();
    let id = this.$route.query.id
    let from = this.$route.query.from;
    if (from) {
      _this.loading = true;
      getRequest('/doc/getDocById/' + id).then((resp) => {
        if (resp.status === 200) {
          _this.loading = false;
          this.document = resp.data.data;
          let tag = _this.categories.find((item)=> item.id===_this.document.tid)
          _this.tagValue = tag.tagName;
        }
      })
    }
  },

  methods: {
    saveBlog (state) {
      if (!(isNotNullORBlank(this.document.title))) {
        this.$message({type: 'error', message: '文档名不能为空呀！'})
        return;
      }
      let _this = this
      postRequest('/doc/saveDoc', {
        title: _this.document.title,
        mdContent: _this.document.mdContent,
        htmlContent: _this.$refs.md.d_render,
        cid: _this.document.cid,
        state: '1'
      }).then(resp => {
        if (resp.status === 200 && resp.data.status > 0) {
          _this.$message({type: 'success', message: resp.data.msg})
        }
      }).catch((error) => {
        _this.$message({type: 'error', message: error})
      })

    },
    getTags(){
      let _this = this;
      getRequest('/doc/getAllTags').then((resp) => {
        _this.categories = resp.data.data;
      })
    }
  }
  , data () {
    return {
      categories: [],
      tagInputVisible: false,
      tagValue: '请选择分类',
      loading: false,
      from: '',
      document: {
        id: '-1',
        dynamicTags: [],
        title: '',
        mdContent: '',
        tid: ''
      }
    }
  }

}
</script>

<style scoped>
.doc-attr {
  display: flex;
  padding-top: 20px;
  align-items: center;
  color: #585f5f;
}

</style>
