<template>
  <div>
    <el-table
      :data="tableData"
      stripe
      style="width: 100%">
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

      <el-table-column prop="title" label="文档名称" width="360">
      </el-table-column>

      <el-table-column label="操作" align="left">
        <template v-slot:default="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <!--scope.$index是取到当前数组tableData的索引-->
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>

import {getRequest} from '../utils/api'

export default {
  name: 'DocList'
  , methods: {
    indexMethod (index) {
      return index + 1
    }
  }
  , mounted: function () {
    getRequest('/doc/getAllDocsByState/1').then((resp) => {
      this.tableData = resp.data.data
    })
  }
  , data () {
    return {
      tableData: []
    }
  }
}
</script>

<style scoped>

</style>
