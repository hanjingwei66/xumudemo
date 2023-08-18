<template>
  <div class="contentWord">
    <div class="childrenUl">
      <div class="circle"></div>
      <div class="lis"><span
      >{{text}}</span></div>
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
.contentWord {
  text-align: justify;
}
.childrenUl {
  display: flex;
  margin-bottom: 16px;
}
.childrenUl .circle {
  width: 4px;
  height: 4px;
  border-radius: 50%;
  background: #36a590;
  margin-top: 8px;
  margin-right: 6px;
}
.childrenUl .lis {
  width: calc(100% - 20px);
  height: 100%;
  text-align: left;
}
</style>
