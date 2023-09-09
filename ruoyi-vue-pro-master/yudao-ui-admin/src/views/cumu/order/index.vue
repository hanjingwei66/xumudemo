<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="买方卡号" prop="buyId">
        <el-input  v-model="queryParams.buyId" placeholder="请输入买方id" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="卖方卡号" prop="saleId">
        <el-input v-model="queryParams.saleId" placeholder="请输入卖方id" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="订单数量" prop="orderNum" >
        <el-input v-model="queryParams.orderNum" placeholder="请输入订单数量" clearable
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="凭证号" prop="tokenNum">
        <el-input v-model="queryParams.tokenNum" placeholder="请输入结算凭证号" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
<!--      <el-form-item label="订单单价" prop="orderPrice">-->
<!--        <el-input v-model="queryParams.orderPrice" placeholder="请输入订单单价" clearable-->
<!--                  @keyup.enter.native="handleQuery"/>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="订单金额" prop="orderCost">-->
<!--        <el-input v-model="queryParams.orderCost" placeholder="请输入订单金额" clearable-->
<!--                  @keyup.enter.native="handleQuery"/>-->
<!--      </el-form-item>-->
      <el-form-item label="订单时间" prop="orderTime">
        <el-date-picker v-model="queryParams.orderTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss"
                        type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"
                        :default-time="['00:00:00', '23:59:59']"/>
      </el-form-item>
<!--      <el-form-item label="状态" prop="orderStatus">-->
<!--        <el-select v-model="queryParams.orderStatus" placeholder="请选择订单状态" clearable-->
<!--                   size="small">-->
<!--          <el-option label="请选择字典生成" value=""/>-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="备注" prop="rember">-->
<!--        <el-input v-model="queryParams.rember" placeholder="请输入备注" clearable @keyup.enter.native="handleQuery"/>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="创建时间" prop="createTime">-->
<!--        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss"-->
<!--                        type="daterange"-->
<!--                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"-->
<!--                        :default-time="['00:00:00', '23:59:59']"/>-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['cumu:order:create']">录入订单
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   :loading="exportLoading"
                   v-hasPermi="['cumu:order:export']">导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="订单id" align="center" prop="id"/>
      <el-table-column label="买方姓名" align="center" prop="buyDO.customName"/>
      <el-table-column label="卖方姓名" align="center" prop="saleDO.customName"/>
      <el-table-column label="畜牧类别" align="center" prop="typeString"/>
      <el-table-column label="订单数量" align="center" prop="orderNum"/>
      <el-table-column label="结算凭证号" align="center" prop="tokenNum"/>
      <el-table-column label="订单单价" align="center" prop="orderPrice"/>
      <el-table-column label="订单金额" align="center" prop="orderCost"/>
      <el-table-column label="订单时间" align="center" prop="orderTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.orderTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="rember"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['cumu:order:update']">修改
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handlePrint(scope.row)"
          >打印订单
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['cumu:order:delete']">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" inline v-loading="formLoading" element-loading-text="录入中....">
        <el-tag style="margin-bottom: 20px">用户信息</el-tag><br>
<!--        <el-form-item label="买方卡号" prop="buyId">-->
<!--          <el-input ref="buyId" v-model="form.buyId" placeholder="请输入买方卡号" @input="readCard(1)"/>-->
<!--        </el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-delete" @click="onRead(1)"-->
<!--        >读卡-->
<!--        </el-button>-->
        <el-form-item label="买方电话" prop="customPhone">
          <el-input  v-model="form.customPhone" placeholder="请输入买方电话" />
        </el-form-item>
<!--        <el-button type="primary" icon="el-icon-delete" @click="onRead(1)"-->
        <el-button type="primary"  @click="getJoinerByVo(form.customPhone)"
        >确 认
        </el-button>
        <el-form-item label="卖方卡号" prop="saleId" style="padding-left: 20px">
          <el-input ref="saleId" v-model="form.saleId" placeholder="请输入卖方卡号" @input="readCard(2)"/>
        </el-form-item>
        <el-button type="primary"  @click="onRead(2)"
        >读 卡
        </el-button>

        <hr class="divider">

        <!--        <el-form-item label="买方姓名" prop="customName">-->
        <!--          <el-input v-model="form.customName" placeholder="请输入用户姓名" />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="买方电话" prop="customPhone">-->
        <!--          <el-input v-model="form.customPhone" placeholder="请输入电话" />-->
        <!--        </el-form-item>-->

        <!--        <el-form-item label="卖方姓名" prop="customName">-->
        <!--          <el-input v-model="form.customName" placeholder="请输入用户姓名" />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="卖方电话" prop="customPhone">-->
        <!--          <el-input v-model="form.customPhone" placeholder="请输入电话" />-->
        <!--        </el-form-item>-->

        <!--        <el-form-item label="订单状态0未支付1支付2退款" prop="orderStatus">-->
        <!--          <el-radio-group v-model="form.orderStatus">-->
        <!--            <el-radio label="1">请选择字典生成</el-radio>-->
        <!--          </el-radio-group>-->
        <!--        </el-form-item>-->


        <div class="cardContainer">
          <el-card class="box-card-left">
            <div>
              <el-row style=" margin-bottom: 10px;">
                <el-col :span="12">
                  <span class="itemSpan">买方姓名:</span>
                  {{ customForm.customName }}
                </el-col>
                <el-col :span="12">
                  <span class="itemSpan">买方电话：</span>
                  {{ customForm.customPhone }}
                </el-col>
              </el-row>
              <el-row style=" margin-bottom: 10px;">
                <el-col :span="24">
                  <span class="itemSpan">买方身份证：</span>
                  {{ customForm.customCardId }}
                </el-col>
              </el-row>
              <el-row style=" margin-bottom: 10px;">
                <el-col :span="12">
                  <span class="itemSpan">买方车牌号：</span>
                  {{ customForm.carId }}
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="4" v-for="(item, index) in this.getDictDatas(DICT_TYPE.CUMU_TYPE)" :key="index">
                  <div class="grid-content bg-purple">
                    <dict-tag :type="DICT_TYPE.CUMU_TYPE" :value="parseInt(item.value)"/>
                    :
                    <span>{{ joinInfoDOList[parseInt(item.value) - 1].num }}</span>
                  </div>
                </el-col>
              </el-row>
            </div>

          </el-card>
          <el-card class="box-card-right">
            <div>
              <el-row style=" margin-bottom: 10px;">
                <el-col :span="12">
                  <span class="itemSpan">卖方姓名:</span>
                  {{ saleCustomForm.customName }}
                </el-col>
                <el-col :span="12">
                  <span class="itemSpan">卖方电话：</span>
                  {{ saleCustomForm.customPhone }}
                </el-col>
              </el-row>
              <el-row style=" margin-bottom: 10px;">
                <el-col :span="24">
                  <span class="itemSpan">卖方身份证：</span>
                  {{ saleCustomForm.customCardId }}
                </el-col>

              </el-row>
              <el-row style=" margin-bottom: 10px;">
                <el-col :span="12">
                  <span class="itemSpan">卖方车牌号：</span>
                  {{ saleCustomForm.carId }}
                </el-col>
              </el-row>
              <el-row style=" margin-bottom: 10px;" v-if="form.region!=undefined">
                <el-col :span="12" >
                  <span class="itemSpan">卖方圈号：</span>
                  {{ form.region +"区" +form.regionNum+"号"}}
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="4" v-for="(item, index) in this.getDictDatas(DICT_TYPE.CUMU_TYPE)" :key="index">
                  <div class="grid-content bg-purple">
                    <dict-tag :type="DICT_TYPE.CUMU_TYPE" :value="parseInt(item.value)"/>
                    :
                    <span>{{ saleJoinInfoDOList[parseInt(item.value) - 1].num }}</span>
                  </div>
                </el-col>
              </el-row>

            </div>
          </el-card>
        </div>
        <el-tag>交易信息</el-tag>
        <hr class="divider">
        <el-form-item label="数量" prop="orderNum">
          <el-input v-model="orderNum" placeholder="请输入订单数量" disabled/>
        </el-form-item>
        <el-form-item label="凭证号" prop="tokenNum">
          <el-input v-model="form.tokenNum" placeholder="请输入结算凭证号"/>
        </el-form-item>
        <el-form-item label="单价" prop="orderPrice">
          <el-input v-model="form.orderPrice" placeholder="请输入订单单价"/>
        </el-form-item>
        <el-form-item label="金额" prop="orderCost">
          <el-input v-model="form.orderCost" placeholder="请输入订单金额"/>
        </el-form-item>
        <el-form-item label="时间" prop="orderTime">
          <el-date-picker clearable v-model="form.orderTime" type="datetime" value-format="timestamp"
                          placeholder="选择订单时间"/>
        </el-form-item>
        <el-form-item label="备注" prop="rember">
          <el-input v-model="form.rember" placeholder="请输入备注"/>
        </el-form-item>
        <el-form-item v-for="(item, index) in this.getDictDatas(DICT_TYPE.CUMU_TYPE)" :key="index">
          <dict-tag :type="DICT_TYPE.CUMU_TYPE" :value="parseInt(item.value)"/>
          :
          <el-input-number v-model="inputItems[index]" @change="handleChange" :min="0" :max="999" label="牛："
                           size="small"></el-input-number>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="printTitle" :visible.sync="printOpen" width="500px" v-dialogDrag append-to-body>
      <div class="canvasContainer">
        <canvas ref="myCanvas" width="180" height="420"></canvas>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="printCanvas">打 印</el-button>
        <el-button @click="printCancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {createOrder, updateOrder, deleteOrder, getOrder, getOrderPage, exportOrderExcel} from "@/api/cumu/order";
import {getCustom,} from "@/api/cumu/custom";
import {getJoiner} from "@/api/cumu/joiner";
import {getLefter,getJoinerByVo} from "@/api/cumu/lefter";
import {formatDate} from "../../../utils";
import html2canvas from "html2canvas";

export default {
  name: "Order",
  components: {},
  data() {
    return {
      formLoading:false,
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 畜牧订单交易列表
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
        buyId: null,
        saleId: null,
        orderNum: null,
        tokenNum: null,
        orderPrice: null,
        orderCost: null,
        orderTime: [],
        orderStatus: null,
        rember: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // buyId: [{required: true, message: "买方id不能为空", trigger: "blur"}],
        customPhone: [{required: true, message: "买方电话不能为空", trigger: "blur"}],
        saleId: [{required: true, message: "卖方id不能为空", trigger: "blur"}],
        tokenNum: [{required: true, message: "结算凭证号不能为空", trigger: "blur"}],
        orderCost: [{required: true, message: "订单金额不能为空", trigger: "blur"}],
        orderTime: [{required: true, message: "订单时间不能为空", trigger: "blur"}],
      },
      buyCardNum: 1,
      saleCardNum: 1,
      inputItems: ['', '', '', '', ''],
      joinInfoDOList: ['', '', '', '', ''],
      saleJoinInfoDOList: ['', '', '', '', ''],
      customForm: {},
      saleCustomForm: {},
    };
  },
  computed: {
    orderNum: function (){
      return this.inputItems.reduce((accumulator, currentValue) => {
        return  parseInt( accumulator ) + parseInt(currentValue);
      }, 0);
    }
  },
  created() {
    this.getList();
  },
  methods: {
    /** 打印按钮操作 */
    handlePrint(row) {
      this.printOpen = true
      this.printTitle = "入场券打印"
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
      for (var i = 2; i <= 14; i++) {
        ctx.moveTo(0, i * 30);
        ctx.lineTo(200, i * 30);
        ctx.stroke();
      }
      for (var j = 0; j <= 1; j++) {
        ctx.moveTo(j * 90, 60);
        ctx.lineTo(j * 90, 420);
        ctx.stroke();
      }

      // 绘制表格内容
      // ctx.font = "16px Arial";
      ctx.font = "14px Microsoft YaHei";
      ctx.textAlign = "center";
      ctx.fillText("畜牧交易市场", 90, 18);
      ctx.fillText("进门证", 90, 48);
      ctx.textAlign = "left";
      ctx.fillText("订单id:", 8, 80);
      ctx.fillText("凭证号:", 8, 110);
      ctx.fillText("买方姓名:", 8, 140);
      ctx.fillText("买方电话：", 8, 170);
      ctx.fillText("卖方姓名:", 8, 200);
      ctx.fillText("卖方电话：", 8, 230);
      ctx.fillText("买方车牌号：", 8, 260);
      ctx.fillText("卖方车牌号：", 8, 290);
      // ctx.fillText("卡内余额：", 8, 170);
      ctx.fillText("畜牧类型：", 8, 320);
      ctx.fillText("交易数量：", 8, 350);
      ctx.fillText("卖方圈号：", 8, 380);
      ctx.fillText("订单日期：", 8, 410);
      // ctx.fillText("临时圈号：", 8, 320);
      // ctx.fillText("进门日期：", 8, 350);
      ctx.fillText("操作员：", 8, 440);
      ctx.font = "12px Microsoft YaHei";
      ctx.fillText(row.id, 94, 80);
      ctx.fillText(row.tokenNum, 94, 110);
      ctx.fillText(row.buyDO.customName, 94, 140);
      ctx.fillText(row.buyDO.customPhone, 94, 170);
      ctx.fillText(row.saleDO.customName, 94, 200);
      ctx.fillText(row.saleDO.customPhone, 94, 230);
      ctx.fillText(row.buyDO.carId, 94, 260);
      ctx.fillText(row.saleDO.carId, 94, 290);
      ctx.fillText(row.typeString, 94, 320);
      ctx.fillText(row.orderNum, 94, 350);
      ctx.fillText(row.region+"区"+row.regionNum+"号" ,94, 380);
      ctx.fillText(formatDate(row.createTime), 94, 410);
      ctx.fillText(row.nikename ,94, 440);
      // ctx.fillText(row.carId, 94, 140);
      // ctx.fillText("2023/8/27 5:44:58", 94, 170);
      // ctx.fillText(row.typeString, 94, 200);
      // ctx.fillText( row.number,94, 230);
      // ctx.fillText(row.region+"区"+row.regionNum+"号" ,94, 260);
      // ctx.fillText(formatDate(row.joinTime) ,94, 350);
      // ctx.fillText(row.nikname ,94, 380);

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
    onRead(val) {
      if (val == 1) {
        this.$nextTick(() => {
          this.$refs.buyId.focus()
        })
      }
      if (val == 2) {
        this.$nextTick(() => {
          this.$refs.saleId.focus()
        })
      }
    },
    getJoinerByVo(customPhone){
      let vo ={
        customPhone: customPhone
      }
      getJoinerByVo(vo).then(response =>{
        this.customForm = response.data.customDO;
        this.form.buyId = response.data.customDO.id
        this.form.buyJoinId = response.data.joinId
        this.joinInfoDOList = response.data.leftInfoDOList
        this.buyCardNum =1

      }).finally(
        this.formLoading = false
      )
    },
    //val 1 买方 2 卖方
    readCard(val) {
      if (1 == val) {
        this.buyCardNum = this.buyCardNum + 1
        if (this.buyCardNum > 10) {
          this.formLoading = true
          getLefter(this.form.buyId).then(response => {
            this.customForm = response.data.customDO;
            this.form.buyId = response.data.customDO.id
            this.form.buyJoinId = response.data.joinId
            this.joinInfoDOList = response.data.leftInfoDOList
            this.buyCardNum =1

          }).finally( this.formLoading = false);
        }
      }
      if (2 == val) {
        this.saleCardNum = this.saleCardNum + 1
        if (this.saleCardNum > 10) {
          this.formLoading = true
          getLefter(this.form.saleId).then(response => {
            if(response.data.region!=null){
              this.form.region = response.data.region
              this.form.regionNum = response.data.regionNum
            }
            if(response.data.tempRegion!=null){
              this.form.region = response.data.tempRegion
              this.form.regionNum = response.data.tempRegionNum
            }
            this.saleCustomForm = response.data.customDO;
            this.form.saleId = response.data.customDO.id
            this.form.saleJoinId = response.data.joinId
            this.saleJoinInfoDOList = response.data.leftInfoDOList
            this.saleCardNum =0

          }).finally( this.formLoading = false);
        }
      }
    },
    handleChange(value) {
      console.log(value);
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getOrderPage(this.queryParams).then(response => {
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
        buyId: undefined,
        saleId: undefined,
        orderNum: undefined,
        tokenNum: undefined,
        orderPrice: undefined,
        orderCost: undefined,
        orderTime: Date.now(),
        orderStatus: undefined,
        rember: undefined,
        region: undefined,
      };
      this.buyCardNum = 1;
      this.saleCardNum = 1;
      this.inputItems = ['', '', '', '', ''];
      this.joinInfoDOList = ['', '', '', '', ''];
      this.saleJoinInfoDOList = ['', '', '', '', ''];
      this.customForm = {
        customName:''
      };
      this.saleCustomForm = {};
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
      this.title = "录入畜牧订单交易";
      this.$nextTick(() => {
        this.$refs.buyId.focus()
      })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改畜牧订单交易";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.formLoading = true
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        // if (this.form.id != null) {
        //   updateOrder(this.form).then(response => {
        //     this.$modal.msgSuccess("修改成功");
        //     this.open = false;
        //     this.getList();
        //     this.formLoading = false;
        //   });
        //   return;
        // }
        const objectArray = this.inputItems.map((item, index) => {
          return {num: parseInt(item), type: index + 1};
        });
        this.form.orderInfoDOS = objectArray
        this.form.orderNum = this.orderNum
        // 添加的提交
        createOrder(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();

        }).finally(() => {
          this.formLoading = false;
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除畜牧订单交易编号为"' + id + '"的数据项?').then(function () {
        return deleteOrder(id);
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
      this.$modal.confirm('是否确认导出所有畜牧订单交易数据项?').then(() => {
        this.exportLoading = true;
        return exportOrderExcel(params);
      }).then(response => {
        this.$download.excel(response, '畜牧订单交易.xls');
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  }
};
</script>
<style>
canvas {
  border: 1px solid black;
}

.canvasContainer {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
}
.cardContainer {
  display: flex;
}

.text {
  font-size: 14px;
}

.item {
  padding: 10px 0;
}

.box-card-left {
  width: 480px;
  margin-right: 20px;
  margin-bottom: 15px;
}

.itemSpan {
  font-weight: 700;
  font-size: 14px;
  color: #606266;
  padding-right: 15px;

}

.box-card-right {
  width: 480px;
  margin-bottom: 15px;
}

.luserInfo {
  width: 480px;
  display: flex;
}

.luserInfo .item {
  width: 30%;
}

.divider {
  display: block;
  height: 1px;
  width: 100%;
  background-color: #DCDFE6;
  color: #8c939d;
  margin-bottom: 24px !important;
  border-top: 1px solid #DCDFE6; /* 将颜色值修改为您想要的颜色 */
}
</style>
