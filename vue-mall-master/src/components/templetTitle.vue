<template>
  <div class="contentWord">
    <div class="div">
      <span>{{text}}</span>
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
</style>
