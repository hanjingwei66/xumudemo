<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="卡号" prop="id">
        <el-input v-model="queryParams.id" placeholder="请输入卡号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="用户姓名" prop="customName">
        <el-input v-model="queryParams.customName" placeholder="请输入用户姓名" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="电话" prop="customPhone">
        <el-input v-model="queryParams.customPhone" placeholder="请输入电话" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="身份证" prop="customCardId">
        <el-input v-model="queryParams.customCardId" placeholder="请输入身份证" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="车牌号" prop="carId">
        <el-input v-model="queryParams.carId" placeholder="请输入车牌号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="凭证开始号" prop="tokenStartNo">
        <el-input v-model="queryParams.tokenStartNo" placeholder="请输入凭证开始号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="凭证结束号" prop="tokenEndNo">
        <el-input v-model="queryParams.tokenEndNo" placeholder="请输入凭证结束号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"-->
<!--                   v-hasPermi="['cumu:custom:create']">新增</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   >读卡</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['cumu:custom:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="卡号" align="center" prop="id" />
      <el-table-column label="用户姓名" align="center" prop="customName" />
      <el-table-column label="电话" align="center" prop="customPhone" />
      <el-table-column label="身份证" align="center" prop="customCardId" />
      <el-table-column label="车牌号" align="center" prop="carId" />
      <el-table-column label="凭证开始号" align="center" prop="tokenStartNo" />
      <el-table-column label="凭证结束号" align="center" prop="tokenEndNo" />
      <el-table-column label="创建者" align="center" prop="creator" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['cumu:custom:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['cumu:custom:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body  >
      <el-form ref="form" :model="form" :rules="rules" label-width="100px" v-loading="formLoading" element-loading-text="读卡中....">
        <el-form-item label="卡号" prop="id" >
          <el-input :disabled="isShowCard" ref="formCardId" v-model="form.id" placeholder="请输入卡号" @input="readCard"/>
        </el-form-item>
        <el-form-item label="用户姓名" prop="customName">
          <el-input v-model="form.customName" placeholder="请输入用户姓名" />
        </el-form-item>
        <el-form-item label="电话" prop="customPhone">
          <el-input v-model="form.customPhone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="身份证" prop="customCardId">
          <el-input v-model="form.customCardId" placeholder="请输入身份证" />
        </el-form-item>
        <el-form-item label="车牌号" prop="carId">
          <el-input v-model="form.carId" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="凭证开始号" prop="tokenStartNo">
          <el-input v-model="form.tokenStartNo" placeholder="请输入凭证开始号" />
        </el-form-item>
        <el-form-item label="凭证结束号" prop="tokenEndNo">
          <el-input v-model="form.tokenEndNo" placeholder="请输入凭证结束号" />
        </el-form-item>
        <el-form-item label="圈区" prop="region">
          <el-select v-model="form.region" placeholder="请选择圈区" clearable size="small">
<!--            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.CUMU_REGION)"-->
<!--                       :key="dict.value" :label="dict.label" :value="dict.value"/>-->
            <el-option
              v-for="item in cumuRegion"
              :key="parseInt(item.value)"
              :label="item.label"
              :value="parseInt(item.value)"
            ></el-option>
          </el-select>

        </el-form-item>
        <el-form-item label="圈号" prop="regionNum">
          <el-input v-model="form.regionNum" placeholder="请输入圈号" />
        </el-form-item>
        <el-form-item label="圈到期时间" prop="regionEndTime">
          <el-date-picker clearable v-model="form.regionEndTime" type="datetime" value-format="timestamp"
                          placeholder="圈到期时间" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createCustom, updateCustom, deleteCustom, getCustom, getCustomPage, exportCustomExcel } from "@/api/cumu/custom";
import {DICT_TYPE, getDictDatas} from "@/utils/dict";
export default {
  name: "Custom",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      formLoading: false,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 畜牧用户列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        id: null,
        customName: null,
        customPhone: null,
        customCardId: null,
        carId: null,
        tokenStartNo: null,
        tokenEndNo: null,
        createTime: [],
      },
      cardNum: 1,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [{ required: true, message: "卡号不能为空", trigger: "blur" }],
        customName: [{ required: true, message: "用户姓名不能为空", trigger: "blur" }],
        customPhone: [{ required: true, message: "电话不能为空", trigger: "blur" }],
        customCardId: [{ required: true, message: "身份证不能为空", trigger: "blur" }],
      },
      updateChanle: false,//是否走修改接口
      isShowCard: false,
      cumuRegion:getDictDatas(DICT_TYPE.CUMU_REGION)
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getCustomPage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    readCard() {
      this.cardNum=this.cardNum+1
        if(this.cardNum>10){
          this.formLoading =true
          getCustom(this.form.id).then(response => {
            if(null != response.data){
              this.form = response.data;
            }
          }).finally(this.formLoading =false)

        }
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
        customName: undefined,
        customPhone: undefined,
        customCardId: undefined,
        carId: undefined,
        tokenStartNo: undefined,
        tokenEndNo: undefined,
      };
      this.isShowCard = false;
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
      this.title = "添加畜牧用户";
      this.$nextTick(() => {
        this.$refs.formCardId.focus()
      })

    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.isShowCard = true;
      const id = row.id;
      getCustom(id).then(response => {
        this.form = response.data;
        this.form.region =response.data.region;
        this.open = true;
        this.title = "修改畜牧用户";
        this.updateChanle = true;
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.updateChanle) {
          this.formLoading =true
          updateCustom(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          }).finally(this.formLoading =false);
          return;
        }
        // 添加的提交
        this.formLoading =true
        createCustom(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        }).finally(this.formLoading =false);
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除畜牧用户编号为"' + id + '"的数据项?').then(function() {
          return deleteCustom(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有畜牧用户数据项?').then(() => {
          this.exportLoading = true;
          return exportCustomExcel(params);
        }).then(response => {
          this.$download.excel(response, '畜牧用户.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
