import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'
import Balance from "./views/Balance";

Vue.use(Router)
const router = new Router({
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
router.beforeEach((to, from, next) => {
    if (to.name !== 'Login' && document.cookie ==="") next({ name: 'Login' })
    else next()
})
export default router;
