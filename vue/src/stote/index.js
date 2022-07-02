//解决手动输入地址获取不到路由名称问题
import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex)

const store = new Vuex.Store({
    state:{
        currentPathName:''
    },
    mutations:{
        setPath (state){
            state.currentPathName = localStorage.getItem("currentPathName")
        }
    }
})



export default store   //导出