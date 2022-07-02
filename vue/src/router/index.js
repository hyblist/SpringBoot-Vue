import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/stote";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () =>import('../views/Manage.vue'),
    redirect:"/home",//重定向
    children:[
      { path:'home',name:'首页',component:()=>import('../views/Home.vue'),},
      { path:'user',name:'用户管理',component:()=>import('../views/User.vue'),}
    ]
  },
  {
    path: '/login',
    component: () =>import('../views/Login.vue'),
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//路由守卫---可供实现面包屑命名
router.beforeEach((to,from,next)=>{
  localStorage.setItem("currentPathName",to.name) //从上面取名字，设置当前的路由名称
  store.commit("setPath")
  next()
})
export default router
