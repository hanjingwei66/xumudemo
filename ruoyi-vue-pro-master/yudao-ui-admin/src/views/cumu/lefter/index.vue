<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户卡号" prop="customId">
        <el-input v-model="queryParams.customId" placeholder="请输入用户id" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="车牌号" prop="carId">
        <el-input v-model="queryParams.carId" placeholder="请输入车牌号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>

      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss"
                        type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"
                        :default-time="['00:00:00', '23:59:59']"/>
      </el-form-item>
      <el-form-item label="离场时间" prop="leftTime">
        <el-date-picker v-model="queryParams.leftTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss"
                        type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"
                        :default-time="['00:00:00', '23:59:59']"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['cumu:lefter:create']">新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   :loading="exportLoading"
                   v-hasPermi="['cumu:lefter:export']">导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="离场登记id" align="center" prop="id"/>
      <el-table-column label="用户姓名" align="center" prop="customDO.customName"/>
      <el-table-column label="车牌号" align="center" prop="carId"/>
      <el-table-column label="畜牧类别" align="center" prop="typeString">
<!--        <template v-slot="scope">-->
<!--          <dict-tag :type="DICT_TYPE.CUMU_TYPE" :value="scope.row.type"/>-->
<!--        </template>-->
      </el-table-column>
      <el-table-column label="预计畜牧数量" align="center" prop="number"/>
      <el-table-column label="实际畜牧数量" align="center" prop="numberReal"/>
      <el-table-column label="状态" align="center" prop="remeber"
                       :filters="[{ text: '已入场', value: 1 }, { text: '已离场', value: 2 }]"
                       :filter-method="filterTag">
        <template v-slot ="scope">
          <el-tag
            :type="scope.row.joinleftFlag === 1 ? 'success' : 'danger'"
            disable-transitions>{{scope.row.joinleftFlag==1?'已入场':'已离场'}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remeber"/>
      <el-table-column label="登记者" align="center" prop="nikename"/>
      <el-table-column label="入场时间" align="center" prop="createTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="离场时间" align="center" prop="leftTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.leftTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['cumu:lefter:update']">出场登记
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handlePrint(scope.row)"
          >打印出场券
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['cumu:lefter:delete']">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" inline>
        <el-form-item label="用户卡号" prop="customId" v-loading="formLoading" element-loading-text="读卡中....">
          <el-input ref="formCardId" v-model="form.customId" @input="readCard" placeholder="请输入用户id"/>
        </el-form-item>
        <el-card class="box-card-left">
          <div>
            <el-row style=" margin-bottom: 10px;">
              <el-col :span="8">
                <span>用户姓名</span>
                {{ customForm.customName }}
              </el-col>
              <el-col :span="8">
                <span>用户电话：</span>
                {{ customForm.customPhone }}
              </el-col>
              <el-col :span="8">
                <span>用户身份证：</span>
                {{ customForm.customCardId }}
              </el-col>
            </el-row>
            <el-row style=" margin-bottom: 10px;">
              <el-col :span="12">
                <span class="itemSpan">车牌号：</span>
                {{ customForm.carId }}
              </el-col>
            </el-row>
            <el-row style=" margin-bottom: 10px;">
              <el-col :span="4" v-for="(item, index) in this.getDictDatas(DICT_TYPE.CUMU_TYPE)" :key="index">
                <div class="grid-content bg-purple">
                  <dict-tag :type="DICT_TYPE.CUMU_TYPE" :value="parseInt(item.value)"/>
                  :
                  <span>{{ leftInfoDOList[parseInt(item.value) - 1].num }}</span>
                </div>
              </el-col>
            </el-row>
          </div>
        </el-card>

        <el-form-item v-for="(item, index) in this.getDictDatas(DICT_TYPE.CUMU_TYPE)" :key="index">
          <dict-tag :type="DICT_TYPE.CUMU_TYPE" :value="parseInt(item.value)"/>
          :
          <el-input-number v-model="inputItems[index]" @change="handleChange" :min="0" :max="999" label="牛："
                           size="small"></el-input-number>
        </el-form-item>

        <!--        <el-form-item label="车牌号" prop="carId">-->
        <!--          <el-input v-model="form.carId" placeholder="请输入车牌号" />-->
        <!--        </el-form-item>-->
        <el-form-item label="预计数量" prop="numberReal" >
          <el-input v-model="form.number" placeholder="请输入实际畜牧数量" />
        </el-form-item>
        <el-form-item label="实际数量" prop="numberReal">
          <el-input v-model="form.numberReal" placeholder="请输入实际畜牧数量"/>
        </el-form-item>
        <el-form-item label="备注" prop="remeber">
          <el-input v-model="form.remeber" placeholder="请输入备注"/>
        </el-form-item>
        <el-form-item label="离场时间" prop="leftTime">
          <el-date-picker clearable v-model="form.leftTime" type="datetime" value-format="timestamp"
                          placeholder="选择入场时间" disabled/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="printTitle" :visible.sync="printOpen" width="500px" v-dialogDrag append-to-body>
      <div class="canvasContainer">
        <canvas ref="myCanvas" width="180" height="300"></canvas>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="printCanvas">打 印</el-button>
        <el-button @click="printCancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {createLefter, updateLefter, deleteLefter, getLefter, getLefterPage, exportLefterExcel, getLefterById} from "@/api/cumu/lefter";
import {formatDate} from "../../../utils";
import html2canvas from "html2canvas";

export default {
  name: "Lefter",
  components: {},
  data() {
    return {
      formLoading: false,
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 畜牧离场列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      printTitle: "",
      // 是否显示弹出层
      open: false,
      printOpen: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        customId: null,
        carId: null,
        type: null,
        number: null,
        numberReal: null,
        remeber: null,
        leftTime: [],
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        customId: [{required: true, message: "用户id不能为空", trigger: "blur"}],
        carId: [{required: true, message: "车牌号不能为空", trigger: "blur"}],
        type: [{required: true, message: "畜牧类别不能为空", trigger: "change"}],
        leftTime: [{required: true, message: "离场时间不能为空", trigger: "blur"}],
      },
      cardNum: 1,
      inputItems: ['', '', '', '', ''],
      leftInfoDOList: ['', '', '', '', ''],
      customForm: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handlePrint(row){
      this.printOpen = true
      this.printTitle = "出场券打印"
      this.$nextTick(() => {
        this.drawTable(row)
      })
    },
    drawTable(row) {
      var canvas = this.$refs.myCanvas;
      var ctx = canvas.getContext("2d");
      ctx.clearRect(0, 0, 180, 385)
      ctx.strokeRect(0, 0, 200, 30);
      ctx.strokeRect(0, 30, 200, 30);
      // 绘制表格线
      for (var i = 2; i <= 10; i++) {
        ctx.moveTo(0, i * 30);
        ctx.lineTo(200, i * 30);
        ctx.stroke();
      }
      for (var j = 0; j <= 2; j++) {
        ctx.moveTo(j * 90, 60);
        ctx.lineTo(j * 90, 300);
        ctx.stroke();
      }

      // 绘制表格内容
      // ctx.font = "16px Arial";
      ctx.font = "14px Microsoft YaHei";
      ctx.textAlign = "center";
      ctx.fillText("畜牧交易市场", 90, 18);
      ctx.fillText("出门证", 90, 48);
      ctx.textAlign = "left";
      ctx.fillText("姓名:", 8, 80);
      ctx.fillText("联系电话：", 8, 110);
      ctx.fillText("车牌号：", 8, 140);
      // ctx.fillText("卡内余额：", 8, 170);
      ctx.fillText("畜牧类型：", 8, 170);
      ctx.fillText("预计数量：", 8, 200);
      ctx.fillText("实际数量：", 8, 230);
      // ctx.fillText("到期日期：", 8, 260);
      // ctx.fillText("临时圈号：", 8, 320);
      ctx.fillText("出门日期：", 8, 260);
      ctx.fillText("操作员：", 8, 290);
      ctx.font = "12px Microsoft YaHei";
      ctx.fillText(row.customDO.customName, 94, 80);
      ctx.fillText(row.customDO.customPhone, 94, 110);
      ctx.fillText(row.carId, 94, 140);
      // ctx.fillText("2023/8/27 5:44:58", 94, 170);
      ctx.fillText(row.typeString, 94, 170);
      ctx.fillText( row.number,94, 200);
      ctx.fillText( row.numberReal==null?"":row.numberReal,94, 230);
      ctx.fillText(formatDate(row.leftTime) ,94, 260);
      ctx.fillText(row.nikename ,94, 290);

      ctx.stroke();

    },
    async printCanvas() {
      const canvas = this.$refs.myCanvas;
      // 使用html2canvas将Canvas转换为图像
      const canvasImage = await html2canvas(canvas);

      // 创建一个新窗口以显示打印预览
      const printWindow = window.open("", "_blank");
      printWindow.document.open();
      await printWindow.document.write(`<img src="${canvasImage.toDataURL()}" />`);
      printWindow.document.close();

      // 调用打印操作
      printWindow.print();
    },
    printCancel() {
      this.printOpen = false;
    },
    readCard() {
      this.cardNum = this.cardNum + 1
      if (this.cardNum > 10) {
        this.formLoading = true
        getLefter(this.form.customId).then(response => {
          this.customForm = response.data.customDO;
          this.form.carId = response.data.customDO.carId
          this.leftInfoDOList = response.data.leftInfoDOList
          this.buyCardNum = 1
          // this.form.number =sum;

        }).finally(this.formLoading = false)
      }
    },
    filterTag(value, row) {
      return row.joinleftFlag === value;
    },

    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getLefterPage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        customId: undefined,
        carId: undefined,
        type: undefined,
        number: undefined,
        numberReal: undefined,
        remeber: undefined,
        leftTime: Date.now(),
      };
      this.cardNum = 1;
      this.leftInfoDOList = ['', '', '', '', ''];
      this.customForm = {};
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加畜牧离场";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      this.formLoading = true
      getLefterById(id).then(response => {
        this.form = response.data;
        this.customForm = response.data.customDO;
        this.leftInfoDOList = response.data.leftInfoDOList
        this.buyCardNum = 1
        // this.form.number =sum;
        this.formLoading = false
        this.open = true;
        this.form.leftTime= Date.now()
        this.title = "修改畜牧离场";
      }).finally( this.formLoading = false);
    },
    /** 提交按钮 */
    submitForm() {

      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        this.formLoading = true
        if (this.form.id != null) {
          updateLefter(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          }).finally(this.formLoading = false);
          return;
        }
        // 添加的提交
        createLefter(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        }).finally(this.formLoading = false);
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除畜牧离场编号为"' + id + '"的数据项?').then(function () {
        return deleteLefter(id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有畜牧离场数据项?').then(() => {
        this.exportLoading = true;
        return exportLefterExcel(params);
      }).then(response => {
        this.$download.excel(response, '畜牧离场.xls');
        this.exportLoading = false;
      }).catch(() => {
      });
    },
    handleChange(value) {
      console.log(value);
    },
  }
};
</script>
<style scoped>
canvas {
  border: 1px solid black;
}

.canvasContainer {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
}

.box-card-left {
  width: 900px;
  margin-right: 20px;
  margin-bottom: 15px;
}

.box-card-left span {
  font-weight: 700;
  font-size: 14px;
  color: #606266;
  padding-right: 15px;
}
</style>
