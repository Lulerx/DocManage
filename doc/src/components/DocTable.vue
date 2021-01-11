<template>
  <div>
    <el-table
      :data="tableData"
      stripe
      style="width: 100%" v-loading="loading">
      <el-table-column
        type="index"
        label="序号"
        :index="indexMethod">
      </el-table-column>
      <el-table-column label="日期" width="180">
        <template v-slot:default="scope">{{ scope.row.editTime | formatDateTime }}</template>
        <!--插槽，scope是取到当前单元格，即tableData数组里当前的元素-->
        <!--scope.row是直接取到该单元格对象，就是数组里的元素对象，即是tableData[scope.$index]-->
      </el-table-column>

      <el-table-column prop="nickname" label="作者" width="180">
      </el-table-column>

      <el-table-column label="文档名称" width="300">
        <template slot-scope="scope">
          <span style="color: #409eff;cursor: pointer" @click="itemClick(scope.row)">{{ scope.row.title }}</span>
        </template>
      </el-table-column>

      <el-table-column prop="tagName" label="文档分类" width="220">
      </el-table-column>

      <el-table-column label="操作" align="left" v-if="showEdit || showDelete">
        <template v-slot:default="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)" v-show="showEdit">编辑</el-button>
          <!--scope.$index是取到当前数组tableData的索引-->
          <el-button size="mini" @click="handleRestore(scope.$index, scope.row)" v-show="showRestore">还原</el-button>

          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)" v-show="showDelete">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="sizes, prev, pager, next"
      :pager-count= pagerCount
      :page-sizes= pageSizes
      :page-size= pageSize
      :total= total
      @current-change = "handleCurrentChange"
      @size-change = "handleSizeChange"
      style="margin-top: 10px" v-show="tableData.length > 0">
    </el-pagination>
  </div>
</template>

<script>

import {getRequest} from '../utils/api'
import {postRequest} from '../utils/api'

export default {
  name: 'DocList',
  props: ['state', 'activeName', 'showEdit', 'showDelete',  'showRestore'],

  created () {
    this.loading = true
    let state = this.state
    if (state == -1) {
      getRequest('/doc/getAllDocs').then((resp) => {
        this.total = resp.data.data;
      })
    }else{
      getRequest('/doc/getAllDocsByState/'+ state).then((resp) => {
        this.total = resp.data.data;
      })
    }
    this.loadTable(this.currentPage, this.pageSize);
  }
  , methods: {
    indexMethod (index) {
      return index + 1
    },
    handleEdit (index, row) {
      this.$router.push({path: '/home/edit', query: {from: 'edit', id: row.id}})
    },
    itemClick (row) {
      this.$router.push({path: 'docDetail', query: {id: row.id}})
    },
    handleSizeChange(size){ //每页显示条数变化时触发
      this.pageSize = size;
      this.loadTable(this.currentPage, this.pageSize);
    },
    handleCurrentChange(currentPage){ //当前页变化时触发
      this.currentPage = currentPage; //点击第几页
      this.loadTable(this.currentPage,this.pageSize);
    },
    loadTable(currentPage, pageSize){
      this.loading = true
      let state = this.state
      if (state == -1) {
        postRequest('/doc/getPageDocs', {
          currentPage: currentPage,
          pageSize: pageSize
        }).then((resp) => {
          this.tableData = resp.data.data
          this.loading = false
        })
      }else{
        postRequest('/doc/getPageDocsByState',{
          state: this.state,
          currentPage: currentPage,
          pageSize: pageSize
        }).then((resp) => {
          this.tableData = resp.data.data
          this.loading = false
        })
      }
    }
  }

  , data () {
    return {
      loading: false,
      tableData: [],
      pagerCount: 5,  //页码按钮的数量，当总页数超过该值时会折叠
      pageSizes: [5,10,20,50],
      pageSize: 5,   //每页显示数量
      total: 100,     //总条数
      currentPage: 1 //当前页码数
    }
  }
}
</script>

<style scoped>

</style>
