
<template>
  <div class="item" id="product.spu.id">
    <img :src="qrVal" alt="QR Code" style="width: 250px; height: 250px"/>
    <use-qr-code></use-qr-code>
  </div>
</template>
import { useQRCode } from '@vueuse/integrations/useQRCode'
<script>
export default {
  props: [
    'qrVal'
  ],
  data () {
    return {
      qrcode: ''
    }
  },
  watch: {
    qrVal: function (newVal, oldQuestion) {
      console.log(newVal)
      console.log(oldQuestion)
    }
  }
}
</script>
<style scoped lang="scss">
@import "../assets/style/mixin";

</style>
