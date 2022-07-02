<template>
  <div style="line-height: 60px;display: flex">
    <div style="flex: 1">
      <!--绑定点击事件-->
      <span v-bind:class="collapseBtnClass" style="cursor:pointer; font-size: 18px"  @click="collapse"></span>
      <!--添加面包屑-->
        <el-breadcrumb separator="/" style="display: inline-block;margin-left: 10px">
          <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
          <el-breadcrumb-item>{{currentPathName}}</el-breadcrumb-item>
        </el-breadcrumb>
    </div>
    <!--el-dropdown下展图标设置，可以通过f12知道其位置-->
    <el-dropdown style="width: 70px; cursor:pointer">
      <!--class="el-icon-caret-bottom"图标样式；style="margin-left: 5px"可以设置与文字距离-->
      <span>黄业斌</span><i class="el-icon-caret-bottom" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>个人信息</el-dropdown-item>
        <el-dropdown-item >
          <router-link to="/login">退出</router-link>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapse: {},
    collapseBtnClass: String
  },
  //计算属性computed,调用时不需要加():currentPathName,如果改用methods需要加():currentPathName()

  computed:{
    //methods实际写法currentPathName: function()
    currentPathName(){
      console.log(this.$store.state.currentPathName)
      return this.$store.state.currentPathName;   //具体可以百度:VUEX的store用法
    }
  },
  // //watch作用 可以监控一个值的变换,并调用因为变化需要执行的方法。 可以通过watch动态改变关联的状态。
  // 好像没什么用
  // watch:{
  //    currentPathName (newVal){
  //      console.log(newVal+"2")
  //
  //   }
  // }

}
</script>

<style scoped>

</style>