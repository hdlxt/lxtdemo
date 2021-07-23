import Vue from 'vue'
import Router from 'vue-router'
import Ueditor from '@/components/Ueditor'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Ueditor',
      component: Ueditor
    }
  ]
})
