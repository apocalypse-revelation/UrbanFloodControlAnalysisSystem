import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes SurveyStation mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/IndexPage',
    children: [{
      path: 'IndexPage',
      name: 'Dashboard',
      component: () => import('@/views/IndexPage/index'),
      meta: { title: '首页', icon: '水涝' }
    }]
  },
  {
    path: '/Search',
    component: Layout,
    redirect: '/example/RiverSearch',
    name: 'Search',
    meta: { title: '综合信息查询', icon: 'search' },
    children: [
      {
        path: 'RiverSearch',
        name: 'RiverSearch',
        component: () => import('@/views/RiverSearch/index'),
        meta: { title: '河道信息查询', icon: '河流' }
      },
      {
        path: 'PrpSearch',
        name: 'PrpSearch',
        component: () => import('@/views/PrpSearch/index'),
        meta: { title: '雨水信息查询', icon: '暴雨' }
      }
    ]
  },
  {
    path: '/SurveyStation',
    component: Layout,
    redirect: '/SurveyStation/SurveyMap',
    name: 'Nested',
    meta: {
      title: '监控点信息',
      icon: '系统监控'
    },
    children: [
      {
        path: 'SurveyStationPosition',
        component: () => import('@/views/SurveyStation/SurveyStationPosition/index'),
        meta: { title: '监控点地图分布', icon: '地图' }
      },
      {
        path: 'SurveyMap',
        component: () => import('@/views/SurveyStation/SurveyMap/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: '监控点分布查询', icon: '地图查询' }
        // children: [
        //   {
        //     path: 'SurveyMap-1',
        //     component: () => import('@/views/SurveyStation/SurveyMap/SurveyMap-1'),
        //     name: 'Menu1-1',
        //     meta: { title: '监控点分布图' }
        //   },
        //   // {
        //   //   path: 'SurveyMap-2',
        //   //   component: () => import('@/views/SurveyStation/SurveyMap/SurveyMap-2'),
        //   //   name: 'Menu1-2',
        //   //   meta: { title: '其他' },
        //   //   children: [
        //   //     {
        //   //       path: 'SurveyMap-2-1',
        //   //       component: () => import('@/views/SurveyStation/SurveyMap/SurveyMap-2/SurveyMap-2-1'),
        //   //       name: 'Menu1-2-1',
        //   //       meta: { title: '1' }
        //   //     },
        //   //     {
        //   //       path: 'SurveyMap-2-2',
        //   //       component: () => import('@/views/SurveyStation/SurveyMap/SurveyMap-2/SurveyMap-2-2'),
        //   //       name: 'Menu1-2-2',
        //   //       meta: { title: '2' }
        //   //     }
        //   //   ]
        //   // },
        //   {
        //     path: 'SurveyMap-3',
        //     component: () => import('@/views/SurveyStation/SurveyMap/SurveyMap-3'),
        //     name: 'Menu1-3',
        //     meta: { title: '监控点查询' }
        //   }
        // ]
      },
      {
        path: 'SurveyStationSearch',
        name: 'surveyss',
        component: () => import('@/views/SurveyStation/SurveyStationSearch/index'),
        meta: { title: '监控点信息查询', icon: '监测范围' }
      }
    ]
  },
  {
    path: '/AvgStatistics',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/AvgStatistics/index'),
        meta: { title: '水库河道统计信息', icon: '统计表' }
      }
    ]
  },
  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
  //       meta: { title: '历史统计', icon: 'link' }
  //     }
  //   ]
  // },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
