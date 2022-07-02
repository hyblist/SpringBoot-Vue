<!--https://element.eleme.cn/#/zh-CN/component/container#container-bu-ju-rong-qi  搜布局容器，取其中代码，并做修改-->
<template>
  <!--菜单下面的横线border: 1px solid #eee"，去掉-->
  <el-container style="height: 100vh">
    <!--页面架构-->
    <!--el-aside侧边栏-->
    <!--el-container有el-header主体 设置颜色：border-bottom: 1px solid #ccc;居中line-height: 60px-->
    <!--el-container-->
    <!--:width="sideWidth+'px'",绑定，并使其自动更换宽度-->
    <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246);  box-shadow: 2px 0 20px  #888888;">
      <!--引用<Aside/>组件需要传参-->
      <Aside :is-collapse="isCollapse" :logo-text-show="logoTextShow"/>
    </el-aside>

    <el-container>
      <!--菜单顶部调整,style="border-bottom: 1px solid #ccc"控制<el-header>大小和颜色-->
      <el-header style="border-bottom: 1px solid #ccc">
        <Header :collapse="collapse" :collapseBtnClass="collapseBtnClass"/>
      </el-header>

      <el-main>
        <!--当前子目录的路由在这里显示-->
       <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Aside from "@/components/Aside";
import Header from "@/components/Header";
export default {
  data() {
    return {
      isCollapse: false,
      sideWidth: 200,
      //logo处理
      logoTextShow: true,
      collapseBtnClass: 'el-icon-s-fold'
    }
  },
  //引用组件，使其生效
  components:{
    Aside,
    Header
  },
  methods: {
    collapse() { //点击按钮触发修改
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) { //收缩
        this.sideWidth = 64
        //并更换图标
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {//展开
        this.sideWidth = 200
        //并更换图标
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    }
  }
}
</script>


