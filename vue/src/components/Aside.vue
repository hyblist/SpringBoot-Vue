<!--页面外面的框架-->
<template>
  <!--overflow: hidden 多层子菜单菜单超出，设置样式；background-color：颜色；text-color字体颜色-->
  <!--active-text-color="#ffdo4b" 选中菜单的颜色；绑定  :collapse-transition="false"菜单的动漫:
  collapse="isCollapse"菜单收缩根据这个变量来变化  -->
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow: hidden"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           active-text-color="#ffdo4b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <!--设置log,新增一个div;style="height: 60px;line-height: 60px;设置高度text-align: center"位置居中，左（默认），右-->
    <!--style="width: 20px"设置图片大小;position: relative;位置相对 top: 5px 下移动；-->
    <div style="height: 60px;line-height: 60px;text-align: center">
      <img src="../assets/logo.png" alt="" style="width: 20px;position: relative;top: 5px;margin-right: 5px">
      <!--是否展示绑定一个变量v-show="logoTextShow"-->
      <b style="color: white" v-show="logoTextShow">后台管理系统</b>
    </div>
    <el-menu-item index="/home">
        <template slot="title">
          <i class="el-icon-house"></i>
          <span slot="title">主页</span>
      </template>
        </el-menu-item>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span slot="title">系统管理</span>
          </template>
          <el-menu-item index="/user">
              <i class="el-icon-s-custom"></i>
              <span slot="title">用户管理</span>
            </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  //定义传值的类型
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean
  }
}
</script>

<style scoped>

</style>