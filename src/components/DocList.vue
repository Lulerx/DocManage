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
      <template slot-scope="scope">{{ scope.row.editTime | formatDateTime}}</template>
    </el-table-column>
    <el-table-column
      prop="uid"
      label="作者"
      width="180">
    </el-table-column>
    <el-table-column
      prop="title"
      label="文档名称"
      width="360">
    </el-table-column>
    <el-table-column label="操作" align="left">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">编辑
        </el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</div>
</template>

<script>

import {getRequest} from '../utils/api'

export default {
  name: 'DocList'
  ,methods: {
    indexMethod(index) {
      return index + 1;
    }
  }
  ,mounted: function () {
    getRequest('/doc/getAllDocsByState/1').then((resp) => {
      this.tableData = resp.data.data;
    })
  }
  , data() {
    return {
      tableData: []
    }
  }
}
</script>

<style scoped>

</style>
