import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'
import BaiduMap from 'vue-baidu-map'

// echarts
// import ECharts from 'vue-echarts/components/ECharts'
import echarts from 'echarts' // 这个是Echarts配置模式而不是vue-echarts配置

// import ECharts from 'vue-echarts'
// axios
import axios from 'axios'
import qs from 'qs'

import '@/icons' // icon
import '@/permission' // permission control

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */
import { mockXHR } from '../mock'
if (process.env.NODE_ENV === 'production') {
  mockXHR()
}

// set ElementUI lang to EN
Vue.use(ElementUI, { locale })

Vue.config.productionTip = false

Vue.use(BaiduMap, {
  // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
  ak: '2KAyaDuGxQlnfX7lA5pD4fYpzzoH21zB'
})
// Vue.component('v-chart', ECharts)
Vue.prototype.$axios = axios // 全局注册，使用方法为:this.$axios
Vue.prototype.qs = qs // 全局注册，使用方法为:this.qs
Vue.prototype.$echarts = echarts

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
