import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

import mavonEditor from "mavon-editor";
import "mavon-editor/dist/css/index.css";
// use
Vue.use(mavonEditor);

Vue.config.productionTip = false;

import "./network/index";

import "./permission";

Vue.use(ElementUI);
Vue.prototype.$message = ElementUI.Message;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
