<template>
  <div class="tag-main">

    <div style="margin: 20px 0;display: flex">
      <el-button type="primary" @click="showInput">添加新分类</el-button>
      <el-input v-if="inputVisible" v-model="inputValue"
                placeholder="按下Enter 键进行操作"
                maxlength="15"
                ref="saveTagInput"
                @keyup.enter.native="handleInputConfirm"
                style="width: 200px;margin-left: 20px">
      </el-input>
    </div>

    <div style="max-width: 800px">
      <!--filter()方法会创建一个新数组，原数组的每个元素传入回调函数中，
      回调函数中有return返回值，若返回值为true，这个元素保存到新数组中；
      若返回值为false，则该元素不保存到新数组中；原数组不发生改变。-->
      <el-table
        border
        :data="tableData.filter(data => !search || data.tagName.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
        <el-table-column
          type="index"
          label="序号"
          :index="indexMethod">
        </el-table-column>
        <el-table-column
          align="center"
          label="分类名"
          prop="tagName">
        </el-table-column>
        <el-table-column
          align="right">
          <template slot="header" slot-scope="scope">
            <el-input
              v-model="search"
              size="mini"
              placeholder="输入分类名的关键字进行搜索"/>
          </template>
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

  </div>
</template>

<script>
import {getRequest} from '../utils/api'
import {postRequest} from '../utils/api'

export default {

  mounted: function () {
    getRequest('/admin/tags/getAllTags').then((resp) => {
      this.tableData = resp.data.data
    }, resp => {
      if (resp.response.status === 403){
        let data = resp.response.data;
        this.$message({type: 'error', message: data.message});
      }
    })
  },

  data () {
    return {
      tableData: [],
      search: '',
      inputVisible: false,
      inputValue: '',
    }
  },
  methods: {

    showInput () {
      this.inputVisible = true
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
      })
    },

    /**
     * 增加新标签
     * */
    handleInputConfirm () {
      let inputValue = this.inputValue;
      let reg = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/g; //匹配字母数字下划线 与 中文
      let result = reg.test(inputValue)
      if (!result) {
        this.$message({type: 'warning', message: '不能输入特殊字符哦'})
      } else {
        postRequest('/admin/tags/createTag',{
          tagName: inputValue
        }).then((resp) => {
          if (resp.data.status === 200){
            this.$message({type: 'success', message:'添加成功'})
            location.reload();
          }else {
            this.$message({type: 'error', message:'添加失败，请重试'})
          }
        })

      }
      this.inputVisible = false
      this.inputValue = ''
    },

    /**
     * 序号
     * @param index
     * @returns {*}
     */
    indexMethod (index) {
      return index + 1
    },

    /**
     * 修改操作
     * @param index
     * @param row
     */
    handleEdit (index, row) {
      this.$prompt('请输入新名称', '编辑', {
        confirmButtonText: '更新',
        cancelButtonText: '取消',
        inputValue: row.tagName,
      }).then(({value}) => {
        let reg = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/g; //匹配字母数字下划线 与 中文
        let result = reg.test(value)
        if (!result) {
          this.$message({type: 'warning', message: '不能输入特殊字符哦'});
        } else {
          postRequest('/admin/tags/updateTag',{
            tagName: value,
            id: row.id
          }).then((resp) => {
            if (resp.data.status === 200){
              this.$message({type: 'success', message: '修改成功'});
              location.reload();
            }else {
              this.$message({type: 'error', message:'添加失败，请重试'});
            }
          })
        }
      }).catch(() => {
        this.$message({type: 'info', message: '取消修改'});
      });
    },

    /**
     * 删除操作
     * @param index
     * @param row
     */
    handleDelete (index, row) {
      this.$confirm('确定要删除这条记录吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        postRequest('/admin/tags/deleteTag',{
          id: row.id
        }).then((resp) => {
          if (resp.data.status === 200){
            this.$message({type: 'success', message: '删除成功'});
            location.reload();
          }else {
            this.$message({type: 'error', message:'删除失败，请重试'});
          }
        })
      }).catch(() => {
        this.$message({type: 'info', message: '已取消删除'});
      })
    }


  }
}
</script>

<style scoped>

.tag-main {
  margin: 30px 10px;
}

.el-tag + .el-tag {
  margin-left: 10px;
}

.button-new-tag {
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}

.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>
