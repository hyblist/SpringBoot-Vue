<template>
  <div>
    <!--搜索position: relative ; top:-10px连这用的-->
    <div style="position: relative ; top:-10px">
      <!--width英 [wɪdθ]宽度；placeholder="请输入名称"提示语，suffix-icon="el-icon-search"嵌套图标-->
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" class="mr-10"
                v-model="username"></el-input>
      <el-input style="width: 200px" placeholder="邮箱" suffix-icon="el-icon-message" class="mr-10"
                v-model="email"></el-input>
      <el-input style="width: 200px" placeholder="地址" suffix-icon="el-icon-position" class="mr-10"
                v-model="address"></el-input>
      <!--@click="load"点击绑定load方法-->
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>
    <!--按钮 <i class="el-icon-circle-plus-outline" >嵌套按钮-->
    <div style=" position: relative; top:-6px ">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="danger" @click="delBatch">批量删除 <i class="el-icon-circle-close"></i></el-button>
      <el-button type="primary">导入 <i class="el-icon-top"></i></el-button>
      <el-button type="primary">导出 <i class="el-icon-bottom"></i></el-button>
    </div>

    <!--表格设置边框border stripe有线-->
    <!--:header-cell-class-name="headerBg"和下面的<script><script>有联系-->
    <!-- :data="tableData" 与下面的<script>中的created()做数据绑定-->
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="id" width="50"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="nickname" label="昵称"></el-table-column>
      <el-table-column prop="phone" label="电话"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <!--添加编辑操作-->
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.row)">编辑</el-button>
          <!--Popconfirm 气泡确认框-->
          <el-popconfirm
              class="ml-10"
              title="你要删除吗？"
              @confirm="delone(scope.row.id)"
          >
            <el-button type="danger" slot="reference" class="ml-5">删除</el-button><!--slot="reference"使按钮显示-->
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!--分页:style="padding: 20px(高度) 10px(margin-left[默认隐藏]:10px)[左距离] 设置位置-->
    <div style="padding: 20px 10px">
      <!--@size-change="handleSizeChange"、@current-change="handleCurrentChange"、:current-page="pageNum"
      :page-size="pageSize"、实现动态翻页-->
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-size="pageSize"
          :page-sizes="[10, 20, 50, 100]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <div>
      <!--visible.sync=""控制弹窗是否显示-->
      <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
        <el-form label-width="80px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="form.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.email" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="form.address" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveUser">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
export default {
  name: "User",
  data() {
    return {
      username: "",
      address: "",
      email: "",
      nickname: "",
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      headerBg: 'headerBg',
      form: {},
      multipleSelection: [],
      dialogFormVisible:false
    }
  },
  created() {
    //分页
    this.load()
  },
  methods: {
    reset() {
      this.username = ""
      this.address = ""
      this.email = ""
      this.load()
    },
    //分页
    load() {
      //请求分页查询数据
      //注意：res.json()和res.join()
      // fetch("http://localhost:9090/user/page?pageNum=1&pageSize=2").then(res =>res.json()).then(res =>{
      //   console.log(res)
      // 改成动态
      // fetch("http://localhost:9090/user/page?" +
      //     "pageNum="+this.pageNum +
      //     "&pageSize="+this.pageSize+
      //     "&username="+this.username+
      //     "&address="+this.address+
      //     "&email="+this.email
      // ).then(res =>res.json()).then(res =>{
      //   console.log(res)
      //   //绑定接口的数据
      //   this.tableData = res.data
      //   this.total = res.total
      // })
      //-----------------------------------------------------------------------------
      //修改成Axios,方式
      this.request.get("/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          address: this.address,
          email: this.email
        }
      }).then(res => {
        //绑定接口的数据
        this.tableData = res.records
        this.total = res.total
      })
    },
    //添加用户确认
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    //添加用户
    saveUser() {
      this.request.post("/user/", this.form).then(res => {
        if (res) {
          this.$message.success("新增成功")
          this.load()
        } else {
          this.$message.error("新增失败")
        }
      });
      this.dialogFormVisible = false
    },
    //编辑用户
    handleEdit(row) {
      console.log(row)
      this.form = row
      this.dialogFormVisible = true
    },
    //删除用户
    delone(id) {
      console.log(id)
      this.request.delete("/user/" + id, this.form).then(res => {
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      });
      this.dialogFormVisible = false
    },
    //批量删除
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    //批量删除按钮
    delBatch() {
      let ids = this.multipleSelection.map(v =>v.id) //[{},{},{}]=>[1,2,3]对象转换成数组
      console.log(ids)
      this.request.post("/user/del/batch", ids).then(res => {   //这里的","很关键，不能写成"+",会很刺激
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
        console.log(res)
      });
    },
    //分页
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    //分页
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    }
  }
}
</script>

<style>
.headerBg {
  background: rgba(204, 204, 204, 0.59) !important;
}
</style>