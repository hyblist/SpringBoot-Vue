const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // devServer: {
  //   proxy: {//配置跨域
  //     '/api': {
  //       target: 'http://localhost:9091/',//这里后台的地址模拟的;应该填写你们真实的后台接口
  //       changOrigin: true,//允许跨域
  //       pathRewrite: {
  //         '^/user': ''//请求的时候使用这个api就可以
  //       }
  //     }
  //
  //   }
  // }
})