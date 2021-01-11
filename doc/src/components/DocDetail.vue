<template>
  <el-row v-loading="loading">
    <el-col :span="24">
      <div style="text-align: left;margin-bottom: 10px">
        <el-button type="text" icon="el-icon-back" @click="goBack" style="padding-bottom: 0px;">返回</el-button>
      </div>
    </el-col>
    <el-col :span="24">
      <div>
        <div><h3 style="margin-top: 0;margin-bottom: 0">{{ article.title }}</h3></div>
        <div style="width: 100%;margin-top: 5px;display: flex;justify-content: flex-end;align-items: center">
          <div style="display: inline; color: #20a0ff;margin-left: 50px;margin-right:20px;font-size: 12px;">
            {{ article.nickname }}
          </div>
          <span
            style="color: #20a0ff;margin-right:20px;font-size: 12px;">浏览 {{
              article.pageView == null ? 0 : article.pageView
            }}</span>
          <span
            style="color: #20a0ff;margin-right:20px;font-size: 12px;"> {{ article.editTime | formatDateTime }}</span>
          <el-tag type="success" v-for="(item,index) in article.tags" :key="index" size="small"
                  style="margin-left: 8px">{{ item.tagName }}
          </el-tag>
          <span style="margin:0px 50px 0px 0px"></span>
        </div>
      </div>
    </el-col>
    <el-col class="markdown-body">  <!--加上 class="markdown-body" 才会正确显示样式-->
      <div style="text-align: left"  v-html="article.htmlContent">
      </div>
    </el-col>
  </el-row>
</template>

<script>
import {getRequest} from '../utils/api'
import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

export default {
  name: 'DocDetail',
  components:{
    mavonEditor
  },
  data () {
    return {
      article: {},
      loading: false,
      activeName: ''
    }
  },
  methods: {
    goBack () {
      this.$router.go(-1)
    }
  },
  mounted () {
    let id = this.$route.query.id
    this.activeName = this.$route.query.an
    let _this = this
    this.loading = true
    getRequest('/doc/getDocById/' + id).then(resp => {
      if (resp.status === 200) {
        _this.article = resp.data.data
      }
      _this.loading = false
    }, resp => {
      _this.loading = false
      _this.$message({type: 'error', message: '页面加载失败!'})
    })
  }
}
</script>

