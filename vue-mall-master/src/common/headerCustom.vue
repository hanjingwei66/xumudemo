<template>
  <div class="topnav">
    <div class="container title-main"><a href="/home" class="logo"><img
      src="data:image/png;base64,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"></a><a
      href="/home" class="item">看精华</a><a href="/project" class="item">找项目</a>
      <a href="/rank" class="item">查榜单</a>
      <a aria-current="page" href="/activity" class="router-link-active router-link-exact-active item"> 实战活动
<!--        <img class="new" src="/images/icon_nav_new.png">-->
      </a>
      <div class="v-spacer"></div>
      <div><a href="/login" class="item">登录</a></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'headerCustom.vue'
}
</script>

<style scoped>
.topnav {
  height: 48px;
  background-color: #006659;
  position: fixed;
  left: 0;
  top: 0;
  width: 100vw;
  z-index: 300;
}

.topnav .title-main {
  height: 100%;
  display: flex;
  align-items: center;
}

.container {
  max-width: 1050px;
  margin: 0 auto;
}

.topnav .logo {
  display: flex;
  align-items: center;
}

a {
  text-decoration: none;
}

a {
  background-color: transparent;
}

.topnav .title-main img {
  width: 93.86px;
  height: 24px;
  margin-right: 34px;
}

img {
  border-style: none;
}

.topnav .title-main .item {
  position: relative;
  font-weight: 600;
  font-size: 14px;
  height: 100%;
  padding: 0 16px;
  color: rgba(254, 249, 237, .65);
  margin-right: 24px;
  display: flex;
  align-items: center;
}
.v-spacer {
  flex: 1;
}


</style>
