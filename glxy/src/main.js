import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueQuillEditor from 'vue-quill-editor'
import VueCookies from 'vue-cookies'
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.config.productionTip = false

 axios.defaults.baseURL = '/api'
//axios.defaults.baseURL = '/'

Vue.use(ElementUI);
Vue.use(VueQuillEditor)
Vue.use(VueCookies)
Vue.use(VueAxios,axios);
Vue.prototype.$axios = axios;



new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

