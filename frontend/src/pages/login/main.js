import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import "element-plus/dist/index.css";

// Handling alert messages
import ElementPlus from "element-plus";


import Axios from "axios";
const app = createApp(App);

Axios.defaults.baseURL = "/api";
Axios.interceptors.request.use(function (config) {
  const token = window.sessionStorage.getItem("token") || "";
  if (token) {
    config.headers.token = token;
  }
  return config;
});

// Response Interceptor
Axios.interceptors.response.use(function (response) {
  const { token, code } = response.data;
  if (code === 200) {
    if (token) window.sessionStorage.setItem("token", token);
  }
  return response;
});
// vue2
// Vue.prototype.$ajax = Axios
// Vue3
app.config.globalProperties.$ajax = Axios;

app.use(ElementPlus).use(store).use(router).mount("#app");
