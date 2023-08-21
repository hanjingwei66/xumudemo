module.exports = {
  NODE_ENV: '"production"',
  dev: {
    env: require('./dev.env'),
    port: 80,
    autoOpenBrowser: true,
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    // proxyTable: {
    //   '/api': {
    //     // target: 'http://127.0.0.1:3333',  // 请求本地 需要clone node-api项目
    //     target: 'http://47.97.204.53:48080', // 请求线上 可直接运行
    //     changeOrigin: true,
    //     pathRewrite: {
    //       '^/api': ''
    //     }
    //   }
    // }
  }
}
