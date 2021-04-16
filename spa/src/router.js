import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/login',
            name: 'Login',
            component: Login,   // `/login`路径访问Login组件
        },{
            path: '/Balance',
            name: 'Balance',
            component: Balance,   // `/login`路径访问Login组件
        },{
            path: '*',
            redirect: '/login'  // 所有路径都重定向到`/login`
        }
    ]
});