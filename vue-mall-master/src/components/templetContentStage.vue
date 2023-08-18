<template>
  <div class="contentStage">
    <div class="contentUlStage">
      <div class="uls">
        <div class="liTop">
          <div class="circle"></div>
          <div class="titleStage"><span>{{text}}</span></div>
        </div>
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
    }
  }
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
.contentWord .div {
  margin-bottom: 16px;
}
.contentUl .uls {
  display: flex;
  flex-direction: column;
  text-align: left;
  margin-bottom: 16px;
}
.contentUl .uls .name {
  font-size: 14px;
  font-weight: 600;
  line-height: 20px;
  color: #36a590;
  margin-bottom: 6px;
}
.uls .liTop {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 8px;
}
.uls .liTop .circle {
  width: 4px;
  height: 4px;
  border-radius: 50%;
  background: #36a590;
  margin-right: 6px;
}
.uls .liTop .titleStage {
  text-align: left;
}
.contentStage .uls .liTop .titleStage span {
  font-size: 14px;
  font-weight: 600;
  line-height: 20px;
  color: #36a590;
}
</style>
