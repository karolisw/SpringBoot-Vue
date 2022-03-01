import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
const routes = [
  {
    path: '/calculator',
    name: 'home',
    component: HomeView
  },
  {
    path: '/calculator/contact',
    name: 'contact',
    // component: FormView
    component: () => import(/* webpackChunkName: "contact" */ '../views/FormView.vue')
  },
  {
    path: '/calculator/expressions',
    name: 'calculations',
    // component: CalculationsTableView
    component: () => import(/* webpackChunkName: "calculations" */ '../views/CalculationsTableView.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
