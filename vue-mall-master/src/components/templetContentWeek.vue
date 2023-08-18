<template>
  <div class="contentWeek">
    <div class="content-title"><span>Week1</span></div>
    <div class="content-card">
      <div v-for="weekText in week" >
        {{weekText}}
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    text: {
      type: [String, Number],
      default: '传统的商业模式是通过商业资产的成功，来成就个人的成功。但是，这样的模式投入成本大，门槛高，成功率也较低'
    },
    week: []
  }
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
.contentWeek {
  display: flex;
  flex-direction: column;
  white-space: pre-line;
  font-weight: 400;
  font-size: 14px;
  line-height: 20px;
  text-align: left;
  position: relative;
  overflow-y: auto;
  margin-bottom: 12px;
}
.contentWeek .content-title {
  width: 74px;
  background: #2cccad;
  display: flex;
  align-items: center;
  justify-content: center;
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
}
.contentWeek .content-title span {
  padding: 5px 10px;
  color: #fff;
  font-size: 14px;
  font-family: Noto Sans SC;
  font-weight: 900;
  line-height: 18px;
}
.contentWeek .content-card {
  width: calc(100% - 32px);
  background: #eefbf8;
  word-break: break-all;
  padding: 12px 16px;
  border-bottom-left-radius: 4px;
  border-bottom-right-radius: 4px;
}
.content-card div {
  color: #333;
  font-size: 14px;
  line-height: 24px;
}
//.contentWeek .content-card div:not(:last-of-type) {
//  margin-bottom: 4px;
//}
</style>
