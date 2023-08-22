<template>
  <div class="container">
    <div class="content-mt">
      <div class="banner">
        <div class="navigate">
          <div class="navigate-title">航海导航</div>
          <div class="navigate-text">
            <span class="active">往期活动</span>
            <span class="active-text">更多主题，更多实战</span>
          </div>
        </div>
        <div class="image">
          <img src="/static/images/pic_activity_banner.jpg">
        </div>
        <div class="myjoin">
          <span>我参与的</span>
        </div>
      </div>
    <div class="head-title">
      <span>2023年6月续航计划</span>
    </div>
      <div class="head">
        <el-button v-for="button in buttons" :key="button.id" style="width: 200px;height:46px" :style="{ backgroundColor: button.color ,color: button.fontColor }" @click="changeColor(button.id)">
          {{button.text}}</el-button>
      </div>
      <div class="content">
        <div class="card" v-for="card in this.dataList" >
          <div class="content-top">
            <img  class="headImg" alt="" :src="card.articleTitlePic" onerror="this.onerror=null;this.src='/images/pic_empty_head.jpg';">
            <div class="title">
              <span>{{ card.articleTitleName }}</span>
            </div>
          </div>
          <div class="content-main">
            <span>{{ card.articleDescribe }}</span>
          </div>
          <div class="content-bottom">
            <div class="number">
              <span>376人报名</span>
            </div>
            <el-button size="small" :style="{ backgroundColor:'#36a590', color:'#FFF'}" @click="selectActiveInfo(card.id)">查看活动</el-button>
          </div>
      </div>


      </div>
      <section class="w mt30 clearfix" v-for="(item,i) in floors" :key="i">
        <y-shelf :title="item.title">
          <div slot="content" class="floors">
            <div class="imgbanner">
              <img v-lazy="floors[i].image.image" :alt="item.title">
            </div>
            <mall-goods :msg="tab" v-for="(tab,i) in item.tabs" :key="i"></mall-goods>
          </div>
        </y-shelf>
      </section>
    </div>
  </div>
</template>
<script>
import {productHome} from '/api'
import {getPage, getInfo} from '/api/hanghaimast'
import YShelf from '/components/shelf'
import product from '/components/product'
import mallGoods from '/components/mallGoods'

export default {
  data () {
    return {
      banner: {},
      bgOpt: {
        offsetLeft: 0,
        offsetTop: 0,
        offsetWidth: 0,
        offsetHeight: 0
      },
      color: '#FFF',
      floors: [],
      hot: [],
      dataList: [],
      buttons: [
        { id: 1, color: 'primary', fontColor: 'black', text: '全部(9)' },
        { id: 2, color: 'primary', fontColor: 'black', text: '从零到一(6)' },
        { id: 3, color: 'primary', fontColor: 'black', text: '深耕赛道(3)' }
      ]
    }
  },
  created () {
    this.buttons[0].color = '#006659'
    this.buttons[0].fontColor = '#FFF'
    this.getPage()
  },
  methods: {
    // 鼠标移入
    bgOver (event) {
      // 获取移入时的位置
      const {offsetLeft, offsetTop, offsetWidth, offsetHeight} = event
      this.bgOpt.offsetLeft = offsetLeft
      this.bgOpt.offsetTop = offsetTop
      this.bgOpt.offsetWidth = offsetWidth
      this.bgOpt.offsetHeight = offsetHeight
    },
    // 鼠标移动
    bgMove (dom, eve) {
      const {offsetLeft, offsetTop, offsetWidth, offsetHeight} = this.bgOpt
      let X, Y
      let mouseX = eve.pageX - offsetLeft
      let mouseY = eve.pageY - offsetTop
      if (mouseX > offsetWidth / 2) {
        X = mouseX - (offsetWidth / 2)
      } else {
        X = mouseX - (offsetWidth / 2)
      }
      if (mouseY > offsetHeight / 2) {
        Y = offsetHeight / 2 - mouseY
      } else {
        Y = offsetHeight / 2 - mouseY
      }
      dom.style['-webkit-transform'] = `rotateY(${X / 50}deg) rotateX(${Y / 50}deg)`
      dom.style.transform = `rotateY(${X / 50}deg) rotateX(${Y / 50}deg)`
    },
    // 鼠标移除
    bgOut (dom) {
      dom.style.transform = 'rotateY(0deg) rotateX(0deg)'
      dom.style['-webkit-transform'] = 'rotateY(0deg) rotateX(0deg)'
    },
    changeColor (id) {
      this.buttons.forEach(button => {
        if (button.id === id) {
          button.color = '#006659' // 设置点击的按钮颜色为success
          button.fontColor = '#FFF'
        } else {
          button.color = '#FFF' // 其他按钮颜色恢复为primary
          button.fontColor = 'black'
        }
      })
    },
    /* 初始化航海活动 */
    getPage () {
      getPage().then(res => {
        this.dataList = res.data.list
      })
    },
    /* 查看活动 */
    selectActiveInfo (id) {
      let query = {
        'id': id
      }
      getInfo(query).then(res => {
        // eslint-disable-next-line standard/object-curly-even-spacing
        let data = res.data
        localStorage.setItem('sailingInfoData', JSON.stringify(data))
        this.$router.push({ name: 'sailingInfo', params: data })
      })
    }
  },
  mounted () {
    productHome().then(res => {
      const {home_floors, home_hot} = res.result
      this.floors = home_floors
      this.hot = home_hot
    })
  },
  components: {
    YShelf,
    product,
    mallGoods
  }
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
.banner, .banner span, .banner div {
  //font-family: "Microsoft YaHei";
  //transition: all .3s;
  //-webkit-transition: all .3s;
  //transition-timing-function: linear;
  //-webkit-transition-timing-function: linear;
}
.container {
  max-width: 1050px;
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  margin-top: 60px;
  //display: flex;
  //flex-direction: column;
  //align-items: flex-start;
  //position: relative;
  //margin-top: 16px;
  //margin-bottom: 60px;
  //overflow-x: hidden;
  //max-width: 1050px;

}
.content-mt {
  //max-width: 2524px;
  display: flex;
  flex-direction: column;
}

.content-mt .head-title {
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-top: 30px;
  margin-bottom: 20px;
}
.head-title span {
  font-weight: 600;
  font-size: 28px;
  line-height: 36px;
  color: rgba(0,0,0,.85);
}
.title  {
  text-align: left;
  margin-right: 10px;
}
.title span {
  font-weight: 500;
  font-size: 20px;
  line-height: 28px;
  color: #000;
}
.banner {
  display: flex;
  width: 1500px;
  width: 100%;
  justify-direction: row;
  justify-content: center;
  margin-bottom: 20px;
}
.image {
  //width: 60%;
}
.image img {
  width: 546px;
  height: 274px;
}

.navigate {
  width: 25%;
  height: 274px;
  background: #ffffff;
  border-radius: 4px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 12px 0;
  margin-right: 12px;
}

.navigate-title {
  text-align: left;
  margin-bottom: 8px;
  padding: 0 12px;
  color: rgba(0, 0, 0, .85);
  font-size: 16px;
  font-weight: 600;
}

.navigate-text {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  text-align: left;
  padding: 8px 12px;
  width: calc(100% - 24px);
  cursor: pointer;

}

.active {
  color: #333;
  font-size: 14px;
  font-weight: 600;
  line-height: 20px;
}

.active-text {
  color: #999;
  font-weight: 400;
  line-height: 16px;
}

.myjoin {
  width: 25%;
  height: 274px;
  background: #ffffff;
  border-radius: 4px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-left: 12px;
}

.myjoin span {
  text-align: left;
  margin-bottom: 8px;
  padding: 0 12px;
  color: rgba(0, 0, 0, .85);
  font-size: 16px;
  font-weight: 600;
  padding-top: 12px;

}
.content {
  display: flex;
  flex-wrap: wrap;
}
.content-top {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding-bottom: 10px;
}
.card {
  width: 32%;
  height: 178px;
  margin-right: 20px;
  margin-bottom: 20px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  position: relative;
  background: linear-gradient(180deg,#f6fffd 0%,#fcfeff 46.66%);
  box-shadow: 0 3px 19px rgba(91,130,122,.03);
  border-radius: 4px;
  cursor: pointer;
  transition: all .25s cubic-bezier(.02,.01,.47,1);

}
.card:nth-child(3n) {
  margin-right: 0 !important;
}
.head {
  margin-bottom: 20px;
}
.headImg {
  width: 40px;
  height: 40px;
  border-radius: 4px;
  margin-right: 10px;
}
.content-main {
  text-align: left;
  height: 44px;
  padding-bottom: 10px;
}
.content-bottom {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}
.content-bottom .number {
  font-size: 14px;
  line-height: 24px;
  color: rgba(0,0,0,.45);
}
.a {
  -webkit-transform: translateZ(40px);
}


.c {
  -webkit-transform: translateZ(0px);
}

.sk_item {
  width: 170px;
  height: 225px;
  padding: 0 14px 0 15px;

  > div {
    width: 100%;
  }

  a {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    transition: all .3s;

    &:hover {
      transform: translateY(-5px);
    }
  }

  .sk_item_name {
    color: #999;
    display: block;
    max-width: 100%;
    _width: 100%;
    overflow: hidden;
    font-size: 12px;
    text-align: left;
    height: 32px;
    line-height: 16px;
    word-wrap: break-word;
    word-break: break-all;
  }

  .sk_item_price {
    padding: 3px 0;
    height: 25px;
  }

  .price_new {
    font-size: 18px;
    font-weight: 700;
    margin-right: 8px;
    color: #f10214;
  }

  .price_origin {
    color: #999;
    font-size: 12px;
  }
}

.box {
  overflow: hidden;
  position: relative;
  z-index: 0;
  margin-top: 29px;
  box-sizing: border-box;
  border: 1px solid rgba(0, 0, 0, .14);
  border-radius: 8px;
  background: #fff;
  box-shadow: 0 3px 8px -6px rgba(0, 0, 0, .1);

}

ul.box {
  display: flex;

  li {
    flex: 1;

    img {
      display: block;
      width: 305px;
      height: 200px;
    }
  }
}

.mt30 {
  margin-top: 30px;
}

.hot {
  display: flex;

  > div {
    flex: 1;
    width: 25%;
  }
}

.floors {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  align-items: center;

  .imgbanner {
    width: 50%;
    height: 430px;
  }

  img {
    display: block;
    width: 100%;
    height: 100%;
  }
}

</style>
