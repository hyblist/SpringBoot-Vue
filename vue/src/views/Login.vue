<template>
  <!--2022-6-30自己搞页面，惨痛失败sad-->
  <div class="wrapper">
    <!--padding-top: 20px 调整高度-->
    <div style="text-align: center ;padding-top: 20px ;font-size: 50px"><span @click="loginShow">欢迎来到hyblist的后台😜</span></div>
    <!--style="margin: 100px 调整高度-->
      <div style="margin: 150px auto;  background-color: #fff;width:350px;height:300px;padding:20px;border-radius: 10px" v-show="login_show">
        <div style="margin: 20px 0;text-align: center;font-size: 24px;color: rgba(0,0,0,0.86)"><b>登录<i class="el-icon-milk-tea"></i></b></div>
        <!--:model="user"很关键，ref="userForm"校验数据，如果符合就执行-->
        <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"  ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        </el-form>
        <div style="margin: 10px 0;text-align: center">
          <el-button type="success" plain size="small" autocomplete="off" @click="touristLogin">游客</el-button>
          <el-button type="primary" size="mini" autocomplete="off" @click="login"><i></i>登录</el-button>
          <el-button type="info"  size="small" autocomplete="off" :plain="true" @click="openCenter">注册</el-button>
        </div>
      </div>
  </div>

</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      user:{},
      login_show:true,
      tourist:{username: "tourist", password: "123456"},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods:{
    loginShow() {
      this.login_show = this.login_show === false;
    },
    openCenter() {
      this.$message({
        message: '暂时未开放注册，可以尝试破解或游客登录！',
        center: true
      });
    },
    // 自己写的。。。。
    // nullLogin(){
    //   console.log(this.user.username)
    //   if (this.user.username===undefined){
    //     this.$message.info("请填写用户名")
    //   }else if (this.user.password===undefined){
    //     this.$message.info("请填写密码")
    //   } else {
    //     this.login()
    //   }
    // },
    login(){
      this.$refs["userForm"].validate((valid) => {
        if (valid) { //表单合法校验
          this.request.post("/user/login",this.user).then(res=>{
            if(!res){
              this.$message.error("用户或密码错误")
            }else {
              this.$message.success("登录成功！")
              this.$router.push("/")
            }
          })
        } else {
          return false;
        }
      });
    },
    touristLogin(){
      this.request.post("/user/login",this.tourist).then(res=>{
        if(!res){
          this.$message.error("用户或密码错误")
        }else {
          this.$router.push("/")
          this.$message.success("欢迎你！")
        }
      })
    },
    clear(){
      this.username = ''
      this.password = ''
    }
  }
}
</script>

<style>
.wrapper{
  height: 100vh;
  background: linear-gradient(to bottom right,#42b983,#9999);
  overflow: hidden;
  /*自定义背景*/
  /*background: url('') center center fixed no-repeat;*/
  /*background-size: cover;*/
}

</style>