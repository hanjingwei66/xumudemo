<template>
  <div class="container">
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane v-for="(item, index) in this.getDictDatas(DICT_TYPE.CUMU_REGION)" :key="index" :label="item.label" :name="item.value.toString()">

        <div class="el-table">
          <!-- 表格头部 -->
<!--          <div class="content">-->
            <!--          <div class="el-table-header">-->
            <!--            <div class="el-table-row">-->
            <!--              <div class="el-table-cell">列1</div>-->
            <!--              <div class="el-table-cell">列2</div>-->
            <!--              <div class="el-table-cell">列3</div>-->
            <!--              <div class="el-table-cell">列4</div>-->
            <!--              <div class="el-table-cell">列5</div>-->
            <!--            </div>-->
            <!--          </div>-->
            <!-- 表格主体 -->
            <div class="el-table-body">
<!--              <div  class="el-table-row">-->
                <div v-for="(cell, cellIndex) in tableData" :key="cellIndex" class="el-table-cell" :class="cell.status==1?'used':'noused'">
                  {{ cell.regionNum }}号
                </div>
<!--              </div>-->
            </div>
<!--          </div>-->
        </div>
      </el-tab-pane>

    </el-tabs>
  </div>
</template>

<script>
import { createJuan, updateJuan, deleteJuan, getJuan, getJuanPage, exportJuanExcel, getByRegionId } from "@/api/cumu/juan";
export default {
  data() {
    return {
      tableData:
        ['圈1', '圈2', '圈3', '圈4', '圈5', '圈1', '圈2', '圈3', '圈4', '圈5','圈1', '圈2', '圈3', '圈4', '圈5', '圈1', '圈2', '圈3', '圈4', '圈5'
        ,'圈1', '圈2', '圈3', '圈4', '圈5', '圈1', '圈2', '圈3', '圈4', '圈5']
        // 更多数据行
      ,
      activeName: '1'
    };
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      getByRegionId(parseInt(this.activeName)).then(res =>{
        this.tableData = res.data
      })
    },
    handleClick(tab, event) {
      this.getData();
      console.log(tab, event);
    }
  }
};
</script>

<style scoped>

/*.container{*/
/*  display: flex;*/
/*  width: 100%;*/
/*}*/
.content {
  display: flex;
  width: 100%;
}
.used {
  background-color: red;
}
.noused {
  /*background-color:  #f5f7fa;*/
}
/* 基本表格样式 */
.el-table {
  display: flex;
  flex-wrap: wrap;
  border: 1px solid #e0e0e0;
  border-radius: 4px;
  overflow: hidden;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  width: 100%; /* 表格宽度 */
  padding: 20px;
}

.el-table-header {
  background-color: #f5f7fa;
  font-weight: bold;
}
.el-table-body{
  display: flex;
  flex-wrap: wrap;
  width: 100%;
}
/*.el-table-row {*/
/*  display: flex;*/
/*  flex-wrap: wrap;*/
/*  width: 100%;*/
/*  border-bottom: 1px solid #e0e0e0;*/
/*}*/

.el-table-cell {
  /*flex: 1;*/
  flex-wrap: wrap;
  padding: 10px;
  text-align: center;
  border: 1px solid #e0e0e0;
  width: 10%;
  height: 150px;
  text-align: center;
  line-height: 150px;
}

.el-table-cell:last-child {
  /*border-right: none;*/
}

/* 鼠标悬停样式 */
.el-table-cell:hover {
  background-color: #f0f9eb;
  cursor: pointer;
}

/* 选中行样式 */
.el-table-row.selected-row {
  /*background-color: #e6e6e6;*/
  background-color: #00ffff;
}
</style>
